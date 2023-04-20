package com.example.integradorSpring.Service;


import com.example.integradorSpring.Model.EnvioModel;
import com.example.integradorSpring.dto.*;
import com.example.integradorSpring.entity.Cliente;
import com.example.integradorSpring.entity.Empleado;
import com.example.integradorSpring.entity.Envio;

import com.example.integradorSpring.entity.Paquete;
import com.example.integradorSpring.Model.PaqueteModel;
import com.example.integradorSpring.exception.ApiRequestException;
import com.example.integradorSpring.repository.ClienteRepository;
import com.example.integradorSpring.repository.EmpleadoRepesitory;
import com.example.integradorSpring.repository.EnvioRepository;
import com.example.integradorSpring.repository.PaqueteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnvioService {
    private List<Envio> envios;
    private EnvioRepository envioRepository;
    private PaqueteRepository paqueteRepository;
    private ClienteRepository clienteRepository;
    private EmpleadoRepesitory empleadoRepesitory;
    private  PaqueteService paqueteService;



    private EnvioEstadoDTO envioEstadoDTO = new EnvioEstadoDTO();



    @Autowired
    public EnvioService(  PaqueteService paqueteService,EmpleadoRepesitory empleadoRepesitory,  EnvioRepository envioRepository, PaqueteRepository paqueteRepository, ClienteRepository clienteRepository) {

        this.empleadoRepesitory = empleadoRepesitory;
        this.paqueteService = paqueteService;
        this.envioRepository = envioRepository;
        this.paqueteRepository = paqueteRepository;
        this.clienteRepository = clienteRepository;
        this.envios = new ArrayList<>();
    }

    public EnvioCreadoDTO crear(EnvioDTO envioDTO){


        String estado =  envioEstadoDTO.getEstadoInicial();

        if(envioDTO.getCedulaCliente() == null
                || envioDTO.getCiudadOrigen() == null
                || envioDTO.getCiudadDestino() == null
                || envioDTO.getDirDestino() == null
                || envioDTO.getNombreRecibe() == null
                || envioDTO.getCelularRecibe() == null
                || envioDTO.getPeso() == 0
                || envioDTO.getValorDeclarado() == 0
        ){
           throw new ApiRequestException("Todos los campos deben ser diligenciados y diferente de nulo o 0");
        }

        Optional<Cliente> cliente = this.clienteRepository.findById(envioDTO.getCedulaCliente());

        if(!cliente.isPresent()){
            throw new ApiRequestException("El cliente debe haberse creado previamente");
        }

        PaqueteModel paquete = new PaqueteModel(
                envioDTO.getPeso(),
                envioDTO.getValorDeclarado()
        );

        Paquete paqueteEntity = new Paquete(
                paquete.getTipo(),
                paquete.getPeso(),
                paquete.getValorDeclarado()
        );

        paqueteRepository.save(paqueteEntity);

        EnvioModel envioModel = new EnvioModel();
        double valor = envioModel.calcularValorEnvio(paqueteEntity.getTipo());

        Envio envio = new Envio(
                envioDTO.getCiudadOrigen(),
                envioDTO.getCiudadDestino(),
                envioDTO.getDirDestino(),
                envioDTO.getNombreRecibe(),
                envioDTO.getCelularRecibe(),
                calcularHoraEntrega(),
                estado,
                valor,
                cliente.get(),
                paqueteEntity

        );
        envioRepository.save(envio);

        EnvioCreadoDTO respuestaDTO = new EnvioCreadoDTO(
                envio.getNumGuia(),
                envio.getEstado()
        );


      return respuestaDTO;
    }

    public String calcularHoraEntrega(){

        LocalDateTime horaLocal = LocalDateTime.now();
        LocalDateTime horaEntrega =  horaLocal.plusHours(72);
        DateTimeFormatter horaFormateada = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");

        String hora = horaFormateada.toString();
        return hora;
    }

    public EnvioCreadoDTO cambiarEstado(EnvioCambiarEstadoDTO envioCambiarEstadoDTO){

        Optional<Envio> envioPorId = this.envioRepository.findById(envioCambiarEstadoDTO.getNumGuia());
        Optional<Empleado> empleadoPorId = this.empleadoRepesitory.findById(envioCambiarEstadoDTO.getCedulaEmpleado());

        if(!empleadoPorId.isPresent()  || !envioPorId.isPresent()){
            throw new ApiRequestException("el empleado con  cedula "+ envioCambiarEstadoDTO.getCedulaEmpleado() +" o el numero de guia del envio "+ envioCambiarEstadoDTO.getNumGuia() + " no son validos");
        }

        if(empleadoPorId.get().getTipo().equals("REPARTIDOR") || empleadoPorId.get().getTipo().equals("COORDINADOR")){
           String estadoActual = envioCambiarEstadoDTO.getEstado().toUpperCase();


           if(estadoActual.equals("RECIBIDO") || estadoActual.equals("EN_RUTA") || estadoActual.equals("ENTREGADO")) {
               Envio envio = envioPorId.get();

               envioPorId.get().setEstado(envioEstadoDTO.cambiarEstado());

               envioRepository.save(envio);

               EnvioCreadoDTO respuestaDTO = new EnvioCreadoDTO(
                       envioPorId.get().getNumGuia(),
                       envioPorId.get().getEstado()
               );

               return respuestaDTO;
           }else{
               throw new ApiRequestException("El estado del envio no corresponde a una de las opciones habilitadas que son :(RECIBIDO, EN_RUTA, ENTREGADO) ");
           }

        }else {
            throw  new ApiRequestException("El empleado con cedula: "+empleadoPorId+ " no esta autorizado para cambiar el estado del envio");
        }

    }

  public  EnvioDetalleDTO buscar(Integer numGuia){

     if(numGuia == null){
       throw new ApiRequestException("El cliente debe haberse creado previamente");}

    Optional<Envio> envio = envioRepository.findById(numGuia);

        if(envio.isPresent()) {

            EnvioDetalleDTO envioDetalleDTO = new EnvioDetalleDTO(
                    envio.get().getNumGuia(),
                    envio.get().getCiudadOrigen(),
                    envio.get().getCiudadDestino(),
                    envio.get().getDirDestino(),
                    envio.get().getNombreRecibe(),
                    envio.get().getCelularRecibe(),
                    envio.get().getValorEnvio(),
                    envio.get().getPaquete().getValorDeclarado(),
                    envio.get().getPaquete().getPeso(),
                    envio.get().getCliente().getCedula(),
                    envio.get().getCliente().getNombre()
            );
            return  envioDetalleDTO;
        }else{
            throw new ApiRequestException("La guia con el ID: "+ numGuia + " no es valido");
        }

    }

    public List<Envio> filtar(String estado){

        String estadoToUpperCase = estado.toUpperCase();


       if(!estadoToUpperCase.equals("RECIBIDO") && !estadoToUpperCase.equals("EN_RUTA") && !estadoToUpperCase.equals("ENTREGADO")){
            throw new ApiRequestException("el estado que esta consultando no existe, asegurese de haber colocado alguna de las siguientes opciones(RECIBIDO - EN_RUTA, ENTREGADO)");
        }
       List<Envio> resultado= envioRepository.filtrarPorEstado(estadoToUpperCase).stream().collect(Collectors.toList());

        if(resultado.isEmpty()){
            throw  new ApiRequestException("No se encontraron envios con estado : "+ estadoToUpperCase);
        }

    return resultado;
    }
}

