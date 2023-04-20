package com.example.integradorSpring;

import com.example.integradorSpring.Model.PaqueteModel;
import com.example.integradorSpring.Service.*;
import com.example.integradorSpring.Service.patronState.Estado;
import com.example.integradorSpring.dto.*;
import com.example.integradorSpring.entity.Cliente;
import com.example.integradorSpring.entity.Empleado;
import com.example.integradorSpring.entity.Envio;
import com.example.integradorSpring.entity.Paquete;
import com.example.integradorSpring.exception.ApiRequestException;
import com.example.integradorSpring.repository.ClienteRepository;
import com.example.integradorSpring.repository.EmpleadoRepesitory;
import com.example.integradorSpring.repository.EnvioRepository;
import com.example.integradorSpring.repository.PaqueteRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class EnvioServiceTest {

    EnvioService envioService;
    PaqueteService paqueteService;

    ClienteService clienteService;
    EmpleadoService empleadoService;


    ClienteRepository clienteRepository;
    EnvioRepository envioRepository;
    EmpleadoRepesitory empleadoRepesitory;

    PaqueteRepository paqueteRepository;

    @Before
    public void setUp(){
        this.clienteRepository = mock(ClienteRepository.class);
        this.paqueteRepository = mock(PaqueteRepository.class);
        this.envioRepository = mock(EnvioRepository.class);
        this.empleadoRepesitory = mock(EmpleadoRepesitory.class);

        this.clienteService =new ClienteService(clienteRepository);
        this.empleadoService = new EmpleadoService(empleadoRepesitory);

        this.paqueteService = new PaqueteService(paqueteRepository);
        this.envioService = new EnvioService(paqueteService, empleadoRepesitory, envioRepository, paqueteRepository, clienteRepository);
    }

    @Test(expected = ApiRequestException.class)
    public void testCrearEnvioClienteNoExiste() {
        EnvioDTO envioDTO = new EnvioDTO();
        envioDTO.setCedulaCliente(12345);

        when(clienteRepository.findById(12345)).thenReturn(Optional.empty());

        EnvioCreadoDTO envioCreadoDTO = envioService.crear(envioDTO);
    }

    @Test
    public void crearEnvioTest() {
        // Arrange
        EnvioDTO envioDTO = new EnvioDTO();
        envioDTO.setCedulaCliente(123456789);
        envioDTO.setCiudadOrigen("Bogotá");
        envioDTO.setCiudadDestino("Medellín");
        envioDTO.setDirDestino("Carrera 5 # 10 - 50");
        envioDTO.setNombreRecibe("Juan Perez");
        envioDTO.setCelularRecibe("3111111111");
        envioDTO.setPeso(2);
        envioDTO.setValorDeclarado(50000);

        Cliente cliente = new Cliente();
        cliente.setCedula(123456789);
        cliente.setNombre("Juan");
        cliente.setApellido("Perez");
        cliente.setCelular("3222222");
        cliente.setDirResidencia("Calle 10 # 5-50");
        cliente.setEmail("juan@mail.com");
        cliente.setCiudad("cali");

        when(clienteRepository.findById(envioDTO.getCedulaCliente())).thenReturn(Optional.of(cliente));

        Paquete paquete = new Paquete();
        paquete.setTipo("LIVIANO");
        paquete.setPeso(2);
        paquete.setValorDeclarado(30000);

        //paqueteService.identificarTipoPaquete(envioDTO.getPeso());
        when(paqueteRepository.save(any(Paquete.class))).thenReturn(paquete);

        // Act
        EnvioCreadoDTO respuestaDTO = envioService.crear(envioDTO);

        // Assert
        assertNotNull(respuestaDTO);

    }

    @Test(expected = ApiRequestException.class)
    public void buscarClienteNoExiste(){
        //Arrange
        EnvioDTO envioDTO = new EnvioDTO();
        envioDTO.setCedulaCliente(123456789);
        envioDTO.setCiudadOrigen("Bogotá");
        envioDTO.setCiudadDestino("Medellín");
        envioDTO.setDirDestino("Carrera 5 # 10 - 50");
        envioDTO.setNombreRecibe("Juan Perez");
        envioDTO.setCelularRecibe("3111111111");
        envioDTO.setPeso(2);
        envioDTO.setValorDeclarado(50000);

        when(clienteRepository.findById(envioDTO.getCedulaCliente())).thenReturn(Optional.empty());
        // Act
        EnvioCreadoDTO respuestaDTO = envioService.crear(envioDTO);
        // Assert
        assertNotNull(respuestaDTO);
    }
    @Test
    public void buscarEnvioExistente(){
        //Arrange
        Integer numGuia = 12345;

        Cliente cliente = new Cliente();
        cliente.setCedula(123456789);
        cliente.setNombre("Juan");
        cliente.setApellido("Perez");
        cliente.setCelular("3222222");
        cliente.setDirResidencia("Calle 10 # 5-50");
        cliente.setEmail("juan@mail.com");
        cliente.setCiudad("cali");

        Paquete paquete = new Paquete();
        paquete.setTipo("LIVIANO");
        paquete.setPeso(2);
        paquete.setValorDeclarado(50000);

        Envio envio = new Envio();
        envio.setCiudadOrigen("Bogotá");
        envio.setCiudadDestino("Medellín");
        envio.setDirDestino("Carrera 5 # 10 - 50");
        envio.setNombreRecibe("Juan Perez");
        envio.setCelularRecibe("3111111111");
        envio.setHoraEntrega("18:00");
        envio.setEstado("RECIBIDO");
        envio.setCliente(cliente);
        envio.setPaquete(paquete);

        //Act
        when(envioRepository.findById(any())).thenReturn(Optional.of(envio));

        // Assert
        EnvioDetalleDTO respuestaDTO = envioService.buscar(numGuia);
        assertNotNull(respuestaDTO);
    }

    @Test(expected = ApiRequestException.class)
    public void buscarEnvioNull(){
        //Arrange
        Integer numGuia = null;

        Cliente cliente = new Cliente();
        cliente.setCedula(123456789);
        cliente.setNombre("Juan");
        cliente.setApellido("Perez");
        cliente.setCelular("3222222");
        cliente.setDirResidencia("Calle 10 # 5-50");
        cliente.setEmail("juan@mail.com");
        cliente.setCiudad("cali");

        Paquete paquete = new Paquete();
        paquete.setTipo("LIVIANO");
        paquete.setPeso(2);
        paquete.setValorDeclarado(50000);

        Envio envio = new Envio();
        envio.setCiudadOrigen("Bogotá");
        envio.setCiudadDestino("Medellín");
        envio.setDirDestino("Carrera 5 # 10 - 50");
        envio.setNombreRecibe("Juan Perez");
        envio.setCelularRecibe("3111111111");
        envio.setHoraEntrega("18:00");
        envio.setEstado("RECIBIDO");
        envio.setCliente(cliente);
        envio.setPaquete(paquete);

        //Act
        when(envioRepository.findById(any())).thenReturn(Optional.of(envio));

        // Assert
        EnvioDetalleDTO respuestaDTO = envioService.buscar(numGuia);

    }

    @Test(expected = ApiRequestException.class)
    public void buscarEnvioNoExistente(){
        //Arrange
        Integer numGuia = 1234;

        Cliente cliente = new Cliente();
        cliente.setCedula(123456789);
        cliente.setNombre("Juan");
        cliente.setApellido("Perez");
        cliente.setCelular("3222222");
        cliente.setDirResidencia("Calle 10 # 5-50");
        cliente.setEmail("juan@mail.com");
        cliente.setCiudad("cali");

        Paquete paquete = new Paquete();
        paquete.setTipo("LIVIANO");
        paquete.setPeso(2);
        paquete.setValorDeclarado(50000);

        Envio envio = new Envio();
        envio.setCiudadOrigen("Bogotá");
        envio.setCiudadDestino("Medellín");
        envio.setDirDestino("Carrera 5 # 10 - 50");
        envio.setNombreRecibe("Juan Perez");
        envio.setCelularRecibe("3111111111");
        envio.setHoraEntrega("18:00");
        envio.setEstado("RECIBIDO");
        envio.setCliente(cliente);
        envio.setPaquete(paquete);
        //Act
        when(envioRepository.findById(any())).thenReturn(Optional.empty());
        // Assert
        EnvioDetalleDTO respuestaDTO = envioService.buscar(numGuia);

    }
    @Test()
    public void filtarPorEstado(){
        //Arrange
        String estado = "RECIBIDO";

        Cliente cliente = new Cliente();
        cliente.setCedula(123456789);
        cliente.setNombre("Juan");
        cliente.setApellido("Perez");
        cliente.setCelular("3222222");
        cliente.setDirResidencia("Calle 10 # 5-50");
        cliente.setEmail("juan@mail.com");
        cliente.setCiudad("cali");

        Paquete paquete = new Paquete();
        paquete.setTipo("LIVIANO");
        paquete.setPeso(2);
        paquete.setValorDeclarado(50000);

        Envio envio = new Envio();
        envio.setCiudadOrigen("Bogotá");
        envio.setCiudadDestino("Medellín");
        envio.setDirDestino("Carrera 5 # 10 - 50");
        envio.setNombreRecibe("Juan Perez");
        envio.setCelularRecibe("3111111111");
        envio.setHoraEntrega("18:00");
        envio.setEstado("RECIBIDO");
        envio.setCliente(cliente);
        envio.setPaquete(paquete);

        //Act

        List<Envio> envios = new ArrayList<>();
        envios.add(envio);

        when(envioRepository.filtrarPorEstado(any())).thenReturn(envios);
        List<Envio> estados = envioService.filtar(estado);

        // Assert
        assertNotNull(estados);
    }

    @Test
    public void filtrarPorEstadoNoEnviosEncontrados() {
        // Arrange
        String estado = "ENTREGADO";
        List<Envio> envios = new ArrayList<>();

        when(envioRepository.filtrarPorEstado(any())).thenReturn(envios);

        // Act && Assert
        ApiRequestException exception = assertThrows(ApiRequestException.class, () -> envioService.filtar(estado));
        assertEquals("No se encontraron envios con estado : ENTREGADO", exception.getMessage());
    }


    @Test
    public void filtrarPorEstadoNoExistente() {
        // Arrange
        String estado = "INVALIDO";

        // Act && Assert
        ApiRequestException exception = assertThrows(ApiRequestException.class, () -> envioService.filtar(estado));
        assertEquals("el estado que esta consultando no existe, asegurese de haber colocado alguna de las siguientes opciones(RECIBIDO - EN_RUTA, ENTREGADO)", exception.getMessage());
    }

    @Test
     public void cambiarEstadoEnvioNoValido() {
        // Arrange
        EnvioCambiarEstadoDTO envioCambiarEstadoDTO = new EnvioCambiarEstadoDTO();
        envioCambiarEstadoDTO.setNumGuia(1234);
        envioCambiarEstadoDTO.setCedulaEmpleado(12345);
        envioCambiarEstadoDTO.setEstado("ENTREGADO");

        // Act && Assert
        assertThrows(ApiRequestException.class, () -> envioService.cambiarEstado(envioCambiarEstadoDTO),
                "el empleado con  cedula " + envioCambiarEstadoDTO.getCedulaEmpleado() + " o el numero de guia del envio " +
                        envioCambiarEstadoDTO.getNumGuia() + " no son validos");
    }

    @Test
    public void cambiarEstadoEmpleadoNoValido() {
        // Arrange
        EnvioCambiarEstadoDTO envioCambiarEstadoDTO = new EnvioCambiarEstadoDTO();
        envioCambiarEstadoDTO.setNumGuia(1234);
        envioCambiarEstadoDTO.setCedulaEmpleado(12345);
        envioCambiarEstadoDTO.setEstado("ENTREGADO");

        // Act && Assert
        assertThrows(ApiRequestException.class, () -> envioService.cambiarEstado(envioCambiarEstadoDTO),
                "el empleado con  cedula " + envioCambiarEstadoDTO.getCedulaEmpleado() + " o el numero de guia del envio " +
                        envioCambiarEstadoDTO.getNumGuia() + " no son validos");
    }


    @Test
    public void cambiarEstado() {
        // Arrange

        Empleado empleado = new Empleado();
        empleado.setCedula(12345);
        empleado.setNombre("Juan");
        empleado.setApellido("Perez");
        empleado.setCelular("3222222");
        empleado.setDirResidencia("Calle 10 # 5-50");
        empleado.setEmail("juan@mail.com");
        empleado.setCiudad("cali");
        empleado.setTipo("REPARTIDOR");


        Cliente cliente = new Cliente();
        cliente.setCedula(123456789);
        cliente.setNombre("Juan");
        cliente.setApellido("Perez");
        cliente.setCelular("3222222");
        cliente.setDirResidencia("Calle 10 # 5-50");
        cliente.setEmail("juan@mail.com");
        cliente.setCiudad("cali");

        Paquete paquete = new Paquete();
        paquete.setTipo("LIVIANO");
        paquete.setPeso(2);
        paquete.setValorDeclarado(50000);

        Envio envio = new Envio();
        envio.setNumGuia(1234);
        envio.setCiudadOrigen("Bogotá");
        envio.setCiudadDestino("Medellín");
        envio.setDirDestino("Carrera 5 # 10 - 50");
        envio.setNombreRecibe("Juan Perez");
        envio.setCelularRecibe("3111111111");
        envio.setHoraEntrega("18:00");
        envio.setEstado("RECIBIDO");
        envio.setCliente(cliente);
        envio.setPaquete(paquete);


        EnvioCambiarEstadoDTO envioCambiarEstadoDTO = new EnvioCambiarEstadoDTO();
        envioCambiarEstadoDTO.setNumGuia(1234);
        envioCambiarEstadoDTO.setCedulaEmpleado(12345);
        envioCambiarEstadoDTO.setEstado("Entregado");

        // Act && Assert


        when(envioRepository.findById(any())).thenReturn(Optional.of(envio));
        when(empleadoRepesitory.findById(any())).thenReturn(Optional.of(empleado));

        envioService.cambiarEstado(envioCambiarEstadoDTO);

       assertEquals(envio.getNumGuia(), envioCambiarEstadoDTO.getNumGuia());
       assertEquals(empleado.getCedula(), envioCambiarEstadoDTO.getCedulaEmpleado());
       assertNotEquals(envio.getEstado(), envioCambiarEstadoDTO.getEstado());


    }

    @Test(expected = ApiRequestException.class)
    public void cambiarEstadoInvalido() {
        // Arrange

        Empleado empleado = new Empleado();
        empleado.setCedula(12345);
        empleado.setNombre("Juan");
        empleado.setApellido("Perez");
        empleado.setCelular("3222222");
        empleado.setDirResidencia("Calle 10 # 5-50");
        empleado.setEmail("juan@mail.com");
        empleado.setCiudad("cali");
        empleado.setTipo("REPARTIDOR");


        Cliente cliente = new Cliente();
        cliente.setCedula(123456789);
        cliente.setNombre("Juan");
        cliente.setApellido("Perez");
        cliente.setCelular("3222222");
        cliente.setDirResidencia("Calle 10 # 5-50");
        cliente.setEmail("juan@mail.com");
        cliente.setCiudad("cali");

        Paquete paquete = new Paquete();
        paquete.setTipo("LIVIANO");
        paquete.setPeso(2);
        paquete.setValorDeclarado(50000);

        Envio envio = new Envio();
        envio.setNumGuia(1234);
        envio.setCiudadOrigen("Bogotá");
        envio.setCiudadDestino("Medellín");
        envio.setDirDestino("Carrera 5 # 10 - 50");
        envio.setNombreRecibe("Juan Perez");
        envio.setCelularRecibe("3111111111");
        envio.setHoraEntrega("18:00");
        envio.setEstado("RECIBIDO");
        envio.setCliente(cliente);
        envio.setPaquete(paquete);


        EnvioCambiarEstadoDTO envioCambiarEstadoDTO = new EnvioCambiarEstadoDTO();
        envioCambiarEstadoDTO.setNumGuia(1234);
        envioCambiarEstadoDTO.setCedulaEmpleado(12345);
        envioCambiarEstadoDTO.setEstado("nulo");

        // Act && Assert


        when(envioRepository.findById(any())).thenReturn(Optional.of(envio));
        when(empleadoRepesitory.findById(any())).thenReturn(Optional.of(empleado));

        envioService.cambiarEstado(envioCambiarEstadoDTO);

    }


    @Test(expected = ApiRequestException.class)
    public void cambiarEstadoEmpleadoNoAutorizado() {
        // Arrange

        Empleado empleado = new Empleado();
        empleado.setCedula(12345);
        empleado.setNombre("Juan");
        empleado.setApellido("Perez");
        empleado.setCelular("3222222");
        empleado.setDirResidencia("Calle 10 # 5-50");
        empleado.setEmail("juan@mail.com");
        empleado.setCiudad("cali");
        empleado.setTipo("BODEGUERO");


        Cliente cliente = new Cliente();
        cliente.setCedula(123456789);
        cliente.setNombre("Juan");
        cliente.setApellido("Perez");
        cliente.setCelular("3222222");
        cliente.setDirResidencia("Calle 10 # 5-50");
        cliente.setEmail("juan@mail.com");
        cliente.setCiudad("cali");

        Paquete paquete = new Paquete();
        paquete.setTipo("LIVIANO");
        paquete.setPeso(2);
        paquete.setValorDeclarado(50000);

        Envio envio = new Envio();
        envio.setNumGuia(1234);
        envio.setCiudadOrigen("Bogotá");
        envio.setCiudadDestino("Medellín");
        envio.setDirDestino("Carrera 5 # 10 - 50");
        envio.setNombreRecibe("Juan Perez");
        envio.setCelularRecibe("3111111111");
        envio.setHoraEntrega("18:00");
        envio.setEstado("RECIBIDO");
        envio.setCliente(cliente);
        envio.setPaquete(paquete);


        EnvioCambiarEstadoDTO envioCambiarEstadoDTO = new EnvioCambiarEstadoDTO();
        envioCambiarEstadoDTO.setNumGuia(1234);
        envioCambiarEstadoDTO.setCedulaEmpleado(12345);
        envioCambiarEstadoDTO.setEstado("ENTREGADO");

        // Act && Assert


        when(envioRepository.findById(any())).thenReturn(Optional.of(envio));
        when(empleadoRepesitory.findById(any())).thenReturn(Optional.of(empleado));

        envioService.cambiarEstado(envioCambiarEstadoDTO);

    }
}


