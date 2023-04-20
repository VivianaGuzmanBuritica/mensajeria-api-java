package com.example.integradorSpring.Controller;


import com.example.integradorSpring.Service.EnvioService;
import com.example.integradorSpring.dto.*;
import com.example.integradorSpring.entity.Envio;
import com.example.integradorSpring.exception.ApiRequestException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EnvioController {

    private EnvioService service;

    @Autowired
    public EnvioController(EnvioService service) {
        this.service = service;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Su solicitud se ha procesado correctamente"),
            @ApiResponse(code = 404, message = " El servidor no ha podido encontrar el recurso solicitado, intene nuevamente"),
            @ApiResponse(code = 500, message = "Lo sentimos, ha habido un error interno en el servidor, no ha sido posible procesar la solicitud.")
    })


    @ApiOperation(value = "Crear un envio", notes= " Este endpoint que crea un envío es una funcionalidad que permite a los usuarios crear un nuevo envío en el sistema. Cuando un usuario realiza una solicitud al endpoint de creación de envío, el servidor procesa la solicitud y crea un nuevo registro de envío en la base de datos.\n" +
            "\n" +
            "Para crear un envío, el usuario debe proporcionar información sobre el cliente que está realizando el envío y el paquete que se está enviando. Además, el usuario debe especificar los detalles del lugar de origen y destino del envío.\n" +
            "\n" +
            "Una vez que se ha proporcionado toda la información necesaria, el servidor calcula el valor a pagar por el envío en función de los detalles del paquete y los detalles de origen y destino. El valor a pagar puede ser influenciado por factores como el tamaño del paquete, la distancia entre el origen y el destino, el método de envío, entre otros.\n" +
            "\n" +
            " Finalmente, el servidor devuelve una respuesta HTTP con un código de respuesta 200 OK indicando que el envío se ha creado correctamente, y un objeto con el número de guía y el estado del paquete ", response = EnvioCreadoDTO.class)
    @PostMapping("/envio")
    public EnvioCreadoDTO crear(@RequestBody EnvioDTO envio){
        return service.crear(envio);
    }

    @ApiOperation(value = "Actualizar el estado de un envio", notes="Este  endpoint para actualizar el estado de un envío es una funcionalidad que permite a los usuarios cambiar el estado actual de un envío registrado en el sistema. Cuando un usuario realiza una solicitud al endpoint de actualización de estado de envío, el servidor procesa la solicitud y actualiza el registro de envío correspondiente en la base de datos.\n" +
            "\n" +
            "El endpoint acepta un identificador único del envío y el nuevo estado del envío. En este caso, los posibles estados del envío son REGISTRADO, EN_RUTA y ENTREGADO. Dependiendo del estado actual del envío, se pueden permitir o no ciertas transiciones de estado. Por ejemplo, no se puede cambiar el estado de un envío a ENTREGADO si su estado actual es REGISTRADO.", response = EnvioCreadoDTO.class)
    @PutMapping("/envio/estado")
    public EnvioCreadoDTO cambiarEstado(@RequestBody EnvioCambiarEstadoDTO envioCambiarEstadoDTO){
        return service.cambiarEstado(envioCambiarEstadoDTO);
    }
    //

    @ApiOperation(value = "Buscar un envio", notes="Este endpoint para buscar un envío por número de guía es una funcionalidad que permite a los usuarios buscar información específica sobre un envío registrado en el sistema utilizando su número de guía único. Cuando un usuario realiza una solicitud al endpoint de búsqueda de envío por número de guía, el servidor procesa la solicitud y devuelve la información correspondiente del envío en la base de datos. ", response = EnvioDetalleDTO.class)
    @GetMapping("/envio/{num-guia}")
    public EnvioDetalleDTO buscar(@PathVariable("num-guia") Integer numGuia){
        return service.buscar(numGuia);
    }

    @ApiOperation(value = "Filtrar  envios por estado", notes="Este endpoint para filtrar los envíos por estado es una funcionalidad que permite a los usuarios buscar información sobre los envíos en función de su estado actual. Cuando un usuario realiza una solicitud al endpoint de filtrado de envíos por estado, el servidor procesa la solicitud y devuelve los registros de envío correspondientes en la base de datos que cumplen con los criterios de búsqueda.", response = List.class)
    @GetMapping("/envio/estado/{estado}")
    public List<Envio> filtar (@PathVariable ("estado") String estado){

        return service.filtar(estado);
    }
}
