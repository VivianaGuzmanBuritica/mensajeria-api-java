package com.example.integradorSpring;

import com.example.integradorSpring.Service.ClienteService;
import com.example.integradorSpring.dto.ClienteDTO;
import com.example.integradorSpring.entity.Cliente;
import com.example.integradorSpring.exception.ApiRequestException;
import com.example.integradorSpring.repository.ClienteRepository;
import io.swagger.models.auth.In;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ClienteServiceTest {
    ClienteService clienteService;
    ClienteRepository clienteRepository;



    @Before
    public void setUp(){
        this.clienteRepository = mock(ClienteRepository.class);
        this.clienteService = new ClienteService(clienteRepository);
    }


    @Test(expected = ApiRequestException.class)
    public void crearClienteNulo(){
        //Arrange
        ClienteDTO cliente =null;
        //Act&Assert
        clienteService.crear(null);
    }

    @Test(expected = ApiRequestException.class)
    public void crearClienteCedulaNula(){
        //Arrange
        ClienteDTO cliente = new ClienteDTO(null, "Vivi", "Guzman", "12345", "vivi@mail.com", "calle123", "Cali");
        //Act&Assert
        clienteService.crear(cliente);
    }


    @Test(expected = ApiRequestException.class)
    public void crarClienteNombreNulo(){
        //Arrange
        ClienteDTO cliente = new ClienteDTO(1234, null, "Guzman", "12345", "vivi@mail.com", "calle123", "Cali");
        //Act&Assert
        clienteService.crear(cliente);
    }



    @Test
    public void crearCliente(){
        //Arrange
        ClienteDTO cliente = new ClienteDTO(1234, "Vivi", "Guzman", "12345", "vivi@mail.com", "calle123", "Cali");
        //Act
        clienteService.crear(cliente);
        //Assert
        verify(clienteRepository, times(1)).save(any());
        assertTrue(cliente.getCedula().equals(1234));
        assertTrue(cliente.getNombre().equals("Vivi"));
        assertTrue(cliente.getApellido().equals("Guzman"));
        assertTrue(cliente.getCelular().equals("12345"));
        assertTrue(cliente.getEmail().equals("vivi@mail.com"));
        assertTrue(cliente.getDirResidencia().equals("calle123"));
        assertTrue(cliente.getCiudad().equals("Cali"));


    }

    @Test(expected = ApiRequestException.class)
    public void buscarClienteCedulaNula(){
        //Arrange
        Integer cedula = null;
        Cliente cliente = new Cliente(1234, "Vivi", "Guzman", "12345", "vivi@mail.com", "calle123", "Cali");
        //Act
       when(clienteRepository.findById(any())).thenReturn(Optional.of(cliente));
       ClienteDTO clienteDTO= this.clienteService.buscar(cedula);
        //Assert
        assertNotNull(clienteDTO);
    }

    @Test(expected = ApiRequestException.class)
    public void buscarClienteNoExiste(){
        //Arrange
        Integer cedula = 1234;
        //Act
        when(clienteRepository.findById(any())).thenReturn(Optional.empty());
        ClienteDTO clienteDTO= this.clienteService.buscar(cedula);
        // Assert
        assertNull(clienteDTO.getNombre());
        assertNull(clienteDTO.getApellido());
        assertNull(clienteDTO.getEmail());
        assertNull(clienteDTO.getDirResidencia());
        assertNull(clienteDTO.getCiudad());
        fail("Se esperaba una excepción de tipo ApiRequestException");
    }

    @Test()
    public void buscarCliente(){
        //Arrange
        Integer cedula = 1234;
        Cliente cliente = new Cliente(1234, "Vivi", "Guzman", "12345", "vivi@mail.com", "calle123", "Cali");
        when(clienteRepository.findById(any())).thenReturn(Optional.of(cliente));

        //Act
        ClienteDTO clienteDTO = this.clienteService.buscar(cedula);

        //Assert
        assertEquals(clienteDTO.getCedula(), cedula);
        assertEquals(clienteDTO.getNombre(), "Vivi");
        assertEquals(clienteDTO.getApellido(), "Guzman");
        assertEquals(clienteDTO.getCelular(), "12345");
        assertEquals(clienteDTO.getEmail(), "vivi@mail.com");
        assertEquals(clienteDTO.getDirResidencia(), "calle123");
        assertEquals(clienteDTO.getCiudad(), "Cali");

        verify(clienteRepository, times(1)).findById(cedula);

    }
    @Test(expected = ApiRequestException.class)
    public void actualizarClienteCedulaNula(){
        //Arrange
        Integer cedula = null;
        Cliente cliente = new Cliente(1234, "Vivi", "Guzman", "12345", "vivi@mail.com", "calle123", "Cali");
        ClienteDTO clienteDTO = new ClienteDTO(1234, "Vivi", "Buritica", "12345", "vivi@mail.com", "calle123", "Cali");

        //Act
        when(clienteRepository.findById(any())).thenReturn(Optional.of(cliente));
        ClienteDTO clienteActualizado= this.clienteService.actualizar(cedula, clienteDTO);
        //Assert
        assertNotNull(clienteActualizado);
    }

    @Test(expected = ApiRequestException.class)
    public void actualizarClienteNoExiste(){
        //Arrange
        Integer cedula = 1234;
        ClienteDTO clienteDTO = new ClienteDTO(1234, "Vivi", "Buritica", "12345", "vivi@mail.com", "calle123", "Cali");

        //Act
        when(clienteRepository.findById(any())).thenReturn(Optional.empty());
        ClienteDTO clienteActualizado= this.clienteService.actualizar(cedula, clienteDTO);
        // Assert
        assertNull(clienteActualizado.getNombre());
        assertNull(clienteActualizado.getApellido());
        assertNull(clienteActualizado.getEmail());
        assertNull(clienteActualizado.getDirResidencia());
        assertNull(clienteActualizado.getCiudad());
        fail("Se esperaba una excepción de tipo ApiRequestException");
    }

    private ClienteDTO convertirClienteAClienteDTO(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setCedula(cliente.getCedula());
        clienteDTO.setNombre(cliente.getNombre());
        clienteDTO.setApellido(cliente.getApellido());
        clienteDTO.setCelular(cliente.getCelular());
        clienteDTO.setEmail(cliente.getEmail());
        clienteDTO.setDirResidencia(cliente.getDirResidencia());
        clienteDTO.setCiudad(cliente.getCiudad());
        return clienteDTO;
    }

    @Test()
    public void actualizarCliente(){
        // Arrange
        Integer cedula = 1234;
        Cliente cliente = new Cliente(cedula, "Vivi", "Guzman", "12345", "vivi@mail.com", "calle123", "Cali");
        ClienteDTO clienteDTOEsperado = convertirClienteAClienteDTO(cliente);
        when(clienteRepository.findById(any())).thenReturn(Optional.of(cliente));
        when(clienteRepository.save(any())).thenReturn(cliente);

        // Modificar el apellido del cliente esperado
        clienteDTOEsperado.setApellido("Guzman Lopez");

        // Act
        ClienteDTO clienteDTOActual = clienteService.actualizar(cedula, clienteDTOEsperado);

        // Assert
        assertTrue(clienteDTOActual.getCedula().equals(clienteDTOEsperado.getCedula()));
        assertTrue(clienteDTOActual.getNombre().equals(clienteDTOEsperado.getNombre()));
        assertTrue(clienteDTOActual.getApellido().equals(clienteDTOEsperado.getApellido()));
        assertTrue(clienteDTOActual.getCelular().equals(clienteDTOEsperado.getCelular()));
        assertTrue(clienteDTOActual.getEmail().equals(clienteDTOEsperado.getEmail()));
        assertTrue(clienteDTOActual.getDirResidencia().equals(clienteDTOEsperado.getDirResidencia()));
        assertTrue(clienteDTOActual.getCiudad().equals(clienteDTOEsperado.getCiudad()));
    }

    @Test(expected = ApiRequestException.class)
    public void eliminarClienteCedulaNula(){
        //Arrange
        Integer cedula = null;
        Cliente cliente = new Cliente(1234, "Vivi", "Guzman", "12345", "vivi@mail.com", "calle123", "Cali");

        //Act
        when(clienteRepository.findById(any())).thenReturn(Optional.of(cliente));
        String clienteActualizado= this.clienteService.eliminar(cedula);
        //Assert
        assertNotNull(clienteActualizado);
    }
    @Test(expected = ApiRequestException.class)
    public void eliminarClienteNoExiste(){
        //Arrange
        Integer cedula = 1234;

        //Act
        when(clienteRepository.findById(any())).thenReturn(Optional.empty());
     String clienteActualizado= this.clienteService.eliminar(cedula);
        // Assert
        assertNull(clienteActualizado);
         fail("Se esperaba una excepción de tipo ApiRequestException");
    }

    @Test
    public void eliminarCliente(){
        //Arrange
        Integer cedula = 1234;
        Cliente cliente = new Cliente(1234, "Vivi", "Buritica", "12345", "vivi@mail.com", "calle123", "Cali");

        //Act
        when(clienteRepository.findById(any())).thenReturn(Optional.of(cliente));
        String clienteActualizado= this.clienteService.eliminar(cedula);
        // Assert
        assertTrue(clienteActualizado.equals("El cliente con cedula "+cliente.getCedula()+ " fue eliminado con exito"));

    }

}
