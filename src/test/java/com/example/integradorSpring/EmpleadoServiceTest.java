package com.example.integradorSpring;

import com.example.integradorSpring.Service.EmpleadoService;
import com.example.integradorSpring.dto.ClienteDTO;
import com.example.integradorSpring.dto.EmpleadoDTO;
import com.example.integradorSpring.dto.EmpleadoDTO;
import com.example.integradorSpring.entity.Cliente;
import com.example.integradorSpring.entity.Empleado;
import com.example.integradorSpring.entity.Empleado;
import com.example.integradorSpring.exception.ApiRequestException;
import com.example.integradorSpring.repository.EmpleadoRepesitory;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EmpleadoServiceTest {

    EmpleadoRepesitory empleadoRepesitory;
    EmpleadoService empleadoService;

    @Before
    public void setUp() {
        this.empleadoRepesitory = mock(EmpleadoRepesitory.class);
        this.empleadoService = new EmpleadoService(empleadoRepesitory);
    }

    @Test(expected = ApiRequestException.class)
    public void crearEmpleadoCedulaNula(){
        //Arrange
        EmpleadoDTO empleadoDTO = new EmpleadoDTO(null, "Vivi", "Guzman", "12345", "vivi@mail.com", "calle123", "Cali",  3.0, "O-",  "REPARTIDOR");
        //Act&Assert
        empleadoService.crear(empleadoDTO);
    }
    @Test(expected = ApiRequestException.class)
    public void crarEmpleadoNombreNulo(){
        //Arrange
        EmpleadoDTO empleadoDTO = new EmpleadoDTO(1234, null, "Guzman", "12345", "vivi@mail.com", "calle123", "Cali",  3.0, "O-",  "REPARTIDOR");
        //Act&Assert
        empleadoService.crear(empleadoDTO);
    }

    @Test(expected = ApiRequestException.class)
    public void crarEmpleadoApellidoNulo(){
        //Arrange
        EmpleadoDTO empleadoDTO = new EmpleadoDTO(1234, "Vivi", null, "12345", "vivi@mail.com", "calle123", "Cali",  3.0, "O-",  "REPARTIDOR");
        //Act&Assert
        empleadoService.crear(empleadoDTO);
    }

    @Test
    public void crearEmpleado(){
        //Arrange
        EmpleadoDTO empleadoDTO = new EmpleadoDTO(1234, "Vivi", "Guzman", "12345", "vivi@mail.com", "calle123", "Cali",  3.0, "O-",  "REPARTIDOR");
        //Act
        empleadoService.crear(empleadoDTO);
        //Assert
        verify(empleadoRepesitory, times(1)).save(any());
        assertTrue(empleadoDTO.getCedula().equals(1234));
        assertTrue(empleadoDTO.getNombre().equals("Vivi"));
        assertTrue(empleadoDTO.getApellido().equals("Guzman"));
        assertTrue(empleadoDTO.getCelular().equals("12345"));
        assertTrue(empleadoDTO.getEmail().equals("vivi@mail.com"));
        assertTrue(empleadoDTO.getDirResidencia().equals("calle123"));
        assertTrue(empleadoDTO.getCiudad().equals("Cali"));

    }

    @Test(expected = ApiRequestException.class)
    public void buscarEmpleadoCedulaNula(){
        //Arrange
        Integer cedula = null;
        Empleado empleado = new Empleado(1234, "Vivi", "Guzman", "12345", "vivi@mail.com", "calle123", "Cali",  3.0, "O-",  "REPARTIDOR");
        //Act
        when(empleadoRepesitory.findById(any())).thenReturn(Optional.of(empleado));
        EmpleadoDTO empleadoDTO= this.empleadoService.buscar(cedula);
        //Assert
        assertNotNull(empleadoDTO);
    }

    @Test(expected = ApiRequestException.class)
    public void buscarEmpleadoNoExiste(){
        //Arrange
        Integer cedula = 1234;
        //Act
        when(empleadoRepesitory.findById(any())).thenReturn(Optional.empty());
        EmpleadoDTO empleadoDTO= this.empleadoService.buscar(cedula);
        // Assert
        assertNull(empleadoDTO.getNombre());
        assertNull(empleadoDTO.getApellido());
        assertNull(empleadoDTO.getEmail());
        assertNull(empleadoDTO.getDirResidencia());
        assertNull(empleadoDTO.getCiudad());
        fail("Se esperaba una excepción de tipo ApiRequestException");
    }

    @Test()
    public void buscarEmpleado(){
        //Arrange
        Integer cedula = 1234;
        Empleado empleado = new Empleado(1234, "Vivi", "Guzman", "12345", "vivi@mail.com", "calle123", "Cali",  3.0, "O-",  "REPARTIDOR");
        when(empleadoRepesitory.findById(any())).thenReturn(Optional.of(empleado));

        //Act
        EmpleadoDTO empleadoDTO = this.empleadoService.buscar(cedula);

        //Assert
        assertEquals(empleadoDTO.getCedula(), cedula);
        assertEquals(empleadoDTO.getNombre(), "Vivi");
        assertEquals(empleadoDTO.getApellido(), "Guzman");
        assertEquals(empleadoDTO.getCelular(), "12345");
        assertEquals(empleadoDTO.getEmail(), "vivi@mail.com");
        assertEquals(empleadoDTO.getDirResidencia(), "calle123");
        assertEquals(empleadoDTO.getCiudad(), "Cali");

        verify(empleadoRepesitory, times(1)).findById(cedula);

    }

    @Test(expected = ApiRequestException.class)
    public void actualizarEmpleadoCedulaNula(){
        //Arrange
        Integer cedula = null;
        Empleado empleado = new Empleado(1234, "Vivi", "Guzman", "12345", "vivi@mail.com", "calle123", "Cali",  3.0, "O-",  "REPARTIDOR");
        EmpleadoDTO empleadoDTO = new EmpleadoDTO(1234, "Vivi", "Guzman", "12345", "vivi@mail.com", "calle123", "Cali",  3.0, "O-",  "REPARTIDOR");

        //Act
        when(empleadoRepesitory.findById(any())).thenReturn(Optional.of(empleado));
        EmpleadoDTO actualizar= this.empleadoService.actualizar(cedula, empleadoDTO);
        //Assert
        assertNotNull(actualizar);
    }

    @Test(expected = ApiRequestException.class)
    public void actualizarEmpleadoNoExiste(){
        //Arrange
        Integer cedula = 1234;
        EmpleadoDTO empleadoDTO = new EmpleadoDTO(1234, "Vivi", "Guzman", "12345", "vivi@mail.com", "calle123", "Cali",  3.0, "O-",  "REPARTIDOR");

        //Act

        when(empleadoRepesitory.findById(any())).thenReturn(Optional.empty());
        EmpleadoDTO actualizado= this.empleadoService.actualizar(cedula, empleadoDTO);
        // Assert
        assertNull(actualizado.getNombre());
        assertNull(actualizado.getApellido());
        assertNull(actualizado.getEmail());
        assertNull(actualizado.getDirResidencia());
        assertNull(actualizado.getCiudad());
        fail("Se esperaba una excepción de tipo ApiRequestException");
    }

    private EmpleadoDTO convertirEmpleadoAEmpleadoDTO(Empleado empleado) {
        EmpleadoDTO empleadoDTO = new EmpleadoDTO();
        empleadoDTO.setCedula(empleado.getCedula());
        empleadoDTO.setNombre(empleado.getNombre());
        empleadoDTO.setApellido(empleado.getApellido());
        empleadoDTO.setCelular(empleado.getCelular());
        empleadoDTO.setEmail(empleado.getEmail());
        empleadoDTO.setDirResidencia(empleado.getDirResidencia());
        empleadoDTO.setCiudad(empleado.getCiudad());
        empleadoDTO.setAntiguedad(empleado.getAntigueadad());
        empleadoDTO.setRhSangre(empleado.getRhSangre());
        empleadoDTO.setTipo(empleado.getTipo());

        return empleadoDTO;
    }


    @Test()
    public void actualizarEmpleado(){
        // Arrange
        Integer cedula = 1234;
        Empleado empleado = new Empleado(1234, "Vivi", "Guzman", "12345", "vivi@mail.com", "calle123", "Cali",  3.0, "O-",  "REPARTIDOR");
        EmpleadoDTO empleadoDTOEsperado = new EmpleadoDTO(1234, "Vivi", "Guzman", "12345", "vivi@mail.com", "calle123", "Cali",  3.0, "O-",  "REPARTIDOR");
        when(empleadoRepesitory.findById(any())).thenReturn(Optional.of(empleado));
        when(empleadoRepesitory.save(any())).thenReturn(empleado);

        // Modificar el apellido del cliente esperado
        empleadoDTOEsperado.setApellido("Guzman Lopez");

        // Act
        EmpleadoDTO empleadoDTOActual = empleadoService.actualizar(cedula, empleadoDTOEsperado);

        // Assert
        assertTrue(empleadoDTOActual.getCedula().equals(empleadoDTOEsperado.getCedula()));
        assertTrue(empleadoDTOActual.getNombre().equals(empleadoDTOEsperado.getNombre()));
        assertTrue(empleadoDTOActual.getApellido().equals(empleadoDTOEsperado.getApellido()));
        assertTrue(empleadoDTOActual.getCelular().equals(empleadoDTOEsperado.getCelular()));
        assertTrue(empleadoDTOActual.getEmail().equals(empleadoDTOEsperado.getEmail()));
        assertTrue(empleadoDTOActual.getDirResidencia().equals(empleadoDTOEsperado.getDirResidencia()));
        assertTrue(empleadoDTOActual.getCiudad().equals(empleadoDTOEsperado.getCiudad()));
    }

    @Test(expected = ApiRequestException.class)
    public void eliminarEmpleadoCedulaNula(){
        //Arrange
        Integer cedula = null;
        Empleado empleado = new Empleado(1234, "Vivi", "Guzman", "12345", "vivi@mail.com", "calle123", "Cali",  3.0, "O-",  "REPARTIDOR");

        //Act
        when(empleadoRepesitory.findById(any())).thenReturn(Optional.of(empleado));
        String actualizado= this.empleadoService.eliminar(cedula);
        //Assert
        assertNotNull(actualizado);
    }


    @Test(expected = ApiRequestException.class)
    public void eliminarEmpleadoNoExiste(){
        //Arrange
        Integer cedula = 1234;

        //Act
        when(empleadoRepesitory.findById(any())).thenReturn(Optional.empty());
        String actualizado= this.empleadoService.eliminar(cedula);
        // Assert
        assertNull(actualizado);
        fail("Se esperaba una excepción de tipo ApiRequestException");
    }

    @Test
    public void eliminarEmpleado(){
        //Arrange
        Integer cedula = 1234;
        Empleado empleado = new Empleado(1234, "Vivi", "Guzman", "12345", "vivi@mail.com", "calle123", "Cali",  3.0, "O-",  "REPARTIDOR");

        //Act
        when(empleadoRepesitory.findById(any())).thenReturn(Optional.of(empleado));
        String actualizado= this.empleadoService.eliminar(cedula);
        // Assert
        assertTrue(actualizado.equals("Empleado con cedula "+empleado.getCedula()+ " fue eliminado con exito"));

    }


}
