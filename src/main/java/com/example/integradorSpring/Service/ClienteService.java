package com.example.integradorSpring.Service;


import com.example.integradorSpring.dto.ClienteDTO;
import com.example.integradorSpring.entity.Cliente;
import com.example.integradorSpring.exception.ApiRequestException;
import com.example.integradorSpring.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private List<Cliente> clientes;

    private ClienteRepository clienteRepository;
    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
        this.clientes = new ArrayList<>();
    }


    public ClienteDTO crear(ClienteDTO clienteDTO){

        if(clienteDTO == null){
            throw new ApiRequestException("El cliente no puede ser nulo ");
        }
        if(clienteDTO.getCedula() == null || clienteDTO.getApellido() == null || clienteDTO.getNombre() == null){
            throw new ApiRequestException("la cedula, el apellido o el nombre son invalidos");
        }

        Cliente cliente = new Cliente(
                clienteDTO.getCedula(),
                clienteDTO.getNombre(),
                clienteDTO.getApellido(),
                clienteDTO.getCelular(),
                clienteDTO.getEmail(),
                clienteDTO.getDirResidencia(),
                clienteDTO.getCiudad()

        );

        clienteRepository.save(cliente);
        return clienteDTO;
    }
    public ClienteDTO buscar(Integer cedula) {

        if(cedula == null){
        throw new ApiRequestException("El cliente debe haberse creado previamente");}

        Optional<Cliente> cliente =  clienteRepository.findById(cedula);

        if(!cliente.isPresent()){
            throw new ApiRequestException("El cliente con cedula" + cedula +"no existe en la base de datos");
        }

        ClienteDTO clienteDTO = new ClienteDTO(
                cliente.get().getCedula(),
                cliente.get().getNombre(),
                cliente.get().getApellido(),
                cliente.get().getCelular(),
                cliente.get().getEmail(),
                cliente.get().getDirResidencia(),
                cliente.get().getCiudad()
        );
        return clienteDTO;
    }

    public ClienteDTO actualizar(Integer cedula, ClienteDTO clienteActualizado) {

        if(cedula == null){
            throw new ApiRequestException("El cliente debe haberse creado previamente");}

        Optional<Cliente> clienteActual =  clienteRepository.findById(cedula);

        if(clienteActual.isPresent()) {
            Cliente cliente = clienteActual.get();
            clienteActual.get().setNombre(clienteActualizado.getNombre());
            clienteActual.get().setApellido(clienteActualizado.getApellido());
            clienteActual.get().setCelular(clienteActualizado.getCelular());
            clienteActual.get().setEmail(clienteActualizado.getEmail());
            clienteActual.get().setDirResidencia(clienteActualizado.getDirResidencia());
            clienteActual.get().setCiudad(clienteActualizado.getCiudad());

            clienteRepository.save(cliente);

            ClienteDTO clienteDTO = new ClienteDTO(
                    clienteActual.get().getCedula(),
                    clienteActual.get().getNombre(),
                    clienteActual.get().getApellido(),
                    clienteActual.get().getCelular(),
                    clienteActual.get().getEmail(),
                    clienteActual.get().getDirResidencia(),
                    clienteActual.get().getCiudad());

            return clienteDTO;
        }else{
            throw new ApiRequestException("El cliente con cedula "+cedula+" no existe");
        }
    }

    public String eliminar(Integer cedula) {

        if(cedula == null) {
            throw new ApiRequestException("la cedula no debe ser nula");
        }
        Optional<Cliente> cliente =  this.clienteRepository.findById(cedula);

        if(!cliente.isPresent()){
            throw new ApiRequestException("El cliente con la cedula" +cedula +" no existe");
        }
            Cliente clienteEncontrado = cliente.get();
            clienteRepository.delete(cliente.get());
              return "El cliente con cedula  fue eliminado con exito";

    }
}

