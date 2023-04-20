package com.example.integradorSpring.Controller;


import com.example.integradorSpring.Service.ClienteService;
import com.example.integradorSpring.Service.EmpleadoService;
import com.example.integradorSpring.dto.ClienteDTO;
import com.example.integradorSpring.dto.EmpleadoDTO;
import com.example.integradorSpring.entity.Empleado;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmpleadoController {

    private EmpleadoService service;

    @Autowired
    public EmpleadoController(EmpleadoService service) {
        this.service = service;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Su solicitud se ha procesado correctamente"),
            @ApiResponse(code = 404, message = " El servidor no ha podido encontrar el recurso solicitado, intene nuevamente"),
            @ApiResponse(code = 500, message = "Lo sentimos, ha habido un error interno en el servidor, no ha sido posible procesar la solicitud.")
    })

    @ApiOperation(value = "Crear una empleado", notes= "Crea una nuevo empleado en la base de datos con la información proporcionada en los parametros del endpoint", response = EmpleadoDTO.class)
    @PostMapping("/empleados")
    public EmpleadoDTO crear(@RequestBody EmpleadoDTO empleado){
        return service.crear(empleado);
    }

    @ApiOperation(value = "Buscar un empleado por cedula", notes= "Este endpoint que busca un empleado por cédula, es una funcionalidad que permite a los usuarios obtener los datos de un empleado en particular utilizando su número de identificación.", response = EmpleadoDTO.class)
    @GetMapping("/empleados/{cedula}")
    public EmpleadoDTO buscar(@PathVariable("cedula") Integer cedula){
        return service.buscar(cedula);
    }

    @ApiOperation(value = "Actualizar un  empleado", notes= "Este endpoint que actualiza los datos de un empleado es una funcionalidad que permite a los usuarios modificar la información de un empleado existente en la base de datos.", response = EmpleadoDTO.class)
    @PutMapping("/empleados/{cedula}")
    public EmpleadoDTO actualizar(@PathVariable("cedula") Integer cedula, @RequestBody EmpleadoDTO empleado){
        return service.actualizar(cedula, empleado);
    }

    @ApiOperation(value = "Eliminar un empleado", notes= "Este endpoint que elimina un empleado es una funcionalidad que permite a los usuarios eliminar los datos de un empleado existente en la base de datos.", response = String.class)
    @DeleteMapping("/empleados/{cedula}")
    public String eliminar(@PathVariable("cedula") Integer cedula){
        return service.eliminar(cedula);
    }
}
