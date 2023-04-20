package com.example.integradorSpring.Controller;

import com.example.integradorSpring.entity.Cliente;
import com.example.integradorSpring.Service.ClienteService;
import com.example.integradorSpring.dto.ClienteDTO;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")

public class ClienteController {
    private ClienteService service;

    @Autowired
    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Su solicitud se ha procesado correctamente"),
            @ApiResponse(code = 404, message = " El servidor no ha podido encontrar el recurso solicitado, intene nuevamente"),
            @ApiResponse(code = 500, message = "Lo sentimos, ha habido un error interno en el servidor, no ha sido posible procesar la solicitud.")
    })

    @ApiOperation(value = "Crear una cliente", notes= "Crea una nuevo cliente en la base de datos con la información proporcionada en los parametros del endpoint", response = ClienteDTO.class)
    @PostMapping("/clientes")
    public ClienteDTO  crear(@RequestBody ClienteDTO cliente){
        return service.crear(cliente);
    }

    @ApiOperation(value = "Buscar un cliente por cedula", notes= "Este endpoint que busca un cliente por cédula, es una funcionalidad que permite a los usuarios obtener los datos de un cliente en particular utilizando su número de identificación.", response = ClienteDTO.class)
    @GetMapping("/clientes/{cedula}")
    public ClienteDTO buscar(@PathVariable("cedula") Integer cedula){
        return service.buscar(cedula);
    }

    @ApiOperation(value = "Actualizar un  cliente", notes= "Este endpoint que actualiza los datos de un cliente es una funcionalidad que permite a los usuarios modificar la información de un cliente existente en la base de datos.", response = ClienteDTO.class)
    @PutMapping("/clientes/{cedula}")
    public ClienteDTO actualizar(@PathVariable("cedula") Integer cedula, @RequestBody ClienteDTO cliente){
        return service.actualizar(cedula, cliente);
    }

    @ApiOperation(value = "Eliminar un cliente", notes= "Este endpoint que elimina un cliente es una funcionalidad que permite a los usuarios eliminar los datos de un cliente existente en la base de datos. Tenga en cuenta que solo se podrá eliminar si este cliente no tiene asociado un envio.", response = String.class)
    @DeleteMapping("/clientes/{cedula}")
    public String eliminar(@PathVariable("cedula") Integer cedula){
        return service.eliminar(cedula);
    }
}
