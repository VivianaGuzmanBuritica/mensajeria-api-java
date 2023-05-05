# API REST Mensajería de Paquetes :package::post_office:

Esta aplicación fue diseñada en el marco del proyecto integrador del Bootcamp de backend de Makaia como resultado de la implementación de los conocimientos adquiridos en la formación académica.

Esta API REST de Mensajeria de paquetes es una aplicación Spring Boot que  permite a los usuarios enviar y hacer seguimiento de los paquetes a través de una interfaz web. La API utiliza el protocolo HTTP para permitir que los usuarios interactúen con el sistema a través de una serie URL definidos para los microservicios de Cliente, Empleado y Envio. Para diseñar estas solución se emplearon las siguientes tecnologías:

- Java 11:coffee:
- Spring Boot :leaves:Versión de 2.7.10
- Gestor de dependecias con  Gradle-Groovy  :elephant:
- Motor de base de datos en MySQL :dolphin: y persistencia de datos co JPA e Hibernate. 
- Integración Continua con Github Actions :octocat: 
- Despliegue con Railway :bullettrain_side:.

Las principales dependencias utilizadas son : 👩‍💻

- Spring Data JPA (Persiste bases de datos SQL utilizando Java Persistence API mediante Spring Data y Hibernate.)
- Spring Web (Construye aplicaciones web, incluyendo RESTful, utilizando Spring MVC. Utiliza Apache Tomcat como contenedor integrado predeterminado.)
- Spring Security (Autenticación básica)
- JUnit (Testeo de pruebas unitarias)
- Swagger (Documentación de la API)

Adicionalmente esta API se encuentra documentada con Swagger y podrá probar la funcionalidad de  cada una de las clases en los Endpoints disponibles en el siguiente link: 

#### :eyes: :link:[ Link a documentación en Swagger - Mensajeria de paquetes](https://mensajeria-api-java-production.up.railway.app/swagger-ui/index.html#/)

No olvide tener a mano los permisos de acceso :closed_lock_with_key: de la aplicación:

Todas las acciones permitidas:
- userName: admin
- password: admin123

## :computer: UML Modelo:

![UML](https://github.com/VivianaGuzmanBuritica/mensajeria-api-java/blob/main/UML_mensajeria.drawio.png)

## :space_invader: Patrones de diseño:

### DTO (Data Transfer Object):
Se utiliza este patrón para transferencia de datos entre diferentes capas de la aplicación, y para mejorar la seguridad de la aplicación al controlar qué datos se transfieren.

### State:
se utiliza este patrón de comportamiento para el manejo del cambio de estado de un envío cambiando así su comportamiento en función de estos estados sin cambiar su estructura, además se articula con la implementación de los tipos de dato especial Enum para definir los valores de los estados ("RECIBIDO", "EN_RUTA", "ENTREGADO") para que de manera que sean fijo en toda la aplicación.

	

## :computer: Diagrama flujo creación de un envio:

![UML](https://github.com/VivianaGuzmanBuritica/mensajeria-api-java/blob/main/diagrama%20flujo%20crear%20envio.drawio.png)

## :computer: Diagrama Entidad Relación:

![MER](https://github.com/VivianaGuzmanBuritica/mensajeria-api-java/blob/main/MER.png)



## :computer: Endpoints:

## Endpoint de creación de cliente :raising_hand:

### POST: /api/v1/clientes

🙍 **Crear** un nuevo cliente en la base de datos con la información proporcionada en el cuerpo de la solicitud.

##### Parámetros de entrada:

- cedula: cedula del cliente (numero)
- nombre: nombre del cliente (cadena de texto)
- apellido: apellido del cliente (cadena de texto)
- celular: número de celular del cliente(cadena de texto)
- diResidencia: direccion del cliente (cadena de texto)
- email: dirección de correo electrónico del cliente (cadena de texto)
- ciudad: ciudad de residencia (cadena de texto)

Ejemplo de solicitud:

```java 
{
"cedula": Integer,
"nombre": String,
"apellido": String,
"direccion": String,
"edad": Integer,
"email": String
}
```

La API devolverá el nuevo cliente creado en formato JSON:
```json
{
	"cedula": 1234, 
	"nombre": "vivi",
	"apellido": "guzman",
	"celular": "3165778",
	"email": "vivi123@mail.com",
	"dirResidencia": "av123",
	"ciudad": "cali"	
}
```

### GET: /api/v1/clientes/{cedula}

 🕵 Este endpoint permite  **buscar** un cliente por cédula, es una funcionalidad que permite a los usuarios obtener los datos de un cliente en particular utilizando su número de identificación.

##### Parámetros de entrada:

- cedula: cedula del cliente (numero)

Ejemplo de solicitud:

```(https://mensajeria-api-java-production.up.railway.app/api/v1/clientes/1234)```

La API devolverá el cliente encontrado en formato JSON:
```json
{
	"cedula": 1234, 
	"nombre": "vivi",
	"apellido": "guzman",
	"celular": "3165778",
	"email": "vivi123@mail.com",
	"dirResidencia": "av123",
	"ciudad": "cali"	
}
```

### PUT: /api/v1/clientes/{cedula}

♻ Este endpoint permite  **actualizar** los datos de un cliente es una funcionalidad que permite a los usuarios modificar la información de un cliente existente en la base de datos.

##### Parámetros de entrada:

- cedula: cedula del cliente (numero)
- nombre: nombre del cliente (cadena de texto)
- apellido: apellido del cliente (cadena de texto)
- celular: número de celular del cliente(cadena de texto)
- diResidencia: direccion del cliente (cadena de texto)
- email: dirección de correo electrónico del cliente (cadena de texto)
- ciudad: ciudad de residencia (cadena de texto)

Ejemplo de solicitud:

```(https://mensajeria-api-java-production.up.railway.app/api/v1/clientes/1234)```
```java 
{
"cedula": Integer,
"nombre": String,
"apellido": String,
"direccion": String,
"edad": Integer,
"email": String
}
```

La API devolverá el cliente actualizado en formato JSON:
```json
{
	"cedula": 1234, 
	"nombre": "vivi",
	"apellido": "guzman",
	"celular": "3165778",
	"email": "vivi123@mail.com",
	"dirResidencia": "av123",
	"ciudad": "cali"	
}
```

### DELETE: /api/v1/clientes/{cedula}

❌ Este endpoint permite  **eliminar** un cliente es una funcionalidad que permite a los usuarios eliminar los datos de un cliente existente en la base de datos. Tenga en cuenta que solo se podrá eliminar si este cliente no tiene asociado un envio.

##### Parámetros de entrada:

- cedula: cedula del cliente (numero)

Ejemplo de solicitud:

```(https://mensajeria-api-java-production.up.railway.app/api/v1/clientes/1234)```

La API devolverá el mensaje:
```json
	"El cliente con cedula  fue eliminado con exito"
```


### Endpoint de creación de empleado👨‍🔧

### POST: /api/v1/empleados

👨‍🔧 **Crear** un nuevo empleado en la base de datos con la información proporcionada en el cuerpo de la solicitud.

##### Parámetros de entrada:

- cedula: cedula del empleado (numero)
- nombre: nombre del empleado (cadena de texto)
- apellido: apellido del empleado (cadena de texto)
- celular: número de celular del empleado(cadena de texto)
- diResidencia: direccion del empleado (cadena de texto)
- email: dirección de correo electrónico del empleado (cadena de texto)
- ciudad: ciudad de residencia (cadena de texto)
- antiguedad: tiempo que lleva trabajando en la empresa (numeros decimales)
- rhSangre: tipo de sangre (cadena de texto)
- tipo: tipo de trabajo de desempeña, puede ser REPARTIDOR - COORDINADO (cadena de texto)  


Ejemplo de solicitud:

```java 
{
  "antiguedad": 0,
  "apellido": "string",
  "cedula": 0,
  "celular": "string",
  "ciudad": "string",
  "dirResidencia": "string",
  "email": "string",
  "nombre": "string",
  "rhSangre": "string",
  "tipo": "string"
}
```

La API devolverá el nuevo empleado creado en formato JSON:
```json
{
	"cedula": 12345, 
	"nombre": "vivi",
	"apellido": "guzman",
	"celular": "3165778",
	"email": "vivi123@mail.com",
	"dirResidencia": "av123",
	"ciudad": "cali",
	"antiguedad": 1.5,
	"rhSangre":"O-",
	"tipo": "REPARTIDOR"
}
```

### GET: /api/v1/empleados/{cedula}

 🕵 Este endpoint permite  **buscar** un empleado por cédula, es una funcionalidad que permite a los usuarios obtener los datos de un empleado en particular utilizando su número de identificación.

##### Parámetros de entrada:

- cedula: cedula del cliente (numero)

Ejemplo de solicitud:

```(https://mensajeria-api-java-production.up.railway.app/api/v1/empleados/12345)```

La API devolverá el empleado encontrado en formato JSON:
```json
{
	"cedula": 12345, 
	"nombre": "vivi",
	"apellido": "guzman",
	"celular": "3165778",
	"email": "vivi123@mail.com",
	"dirResidencia": "av123",
	"ciudad": "cali",
	"antiguedad": 1.5,
	"rhSangre":"O-",
	"tipo": "REPARTIDOR"
}
```

### PUT: /api/v1/empleado/{cedula}

♻ Este endpoint permite  **actualizar** los datos de un empleado es una funcionalidad que permite a los usuarios modificar la información de un empleado existente en la base de datos.

- cedula: cedula del empleado (numero)
- nombre: nombre del empleado (cadena de texto)
- apellido: apellido del empleado (cadena de texto)
- celular: número de celular del empleado(cadena de texto)
- diResidencia: direccion del empleado (cadena de texto)
- email: dirección de correo electrónico del empleado (cadena de texto)
- ciudad: ciudad de residencia (cadena de texto)
- antiguedad: tiempo que lleva trabajando en la empresa (numeros decimales)
- rhSangre: tipo de sangre (cadena de texto)
- tipo: tipo de trabajo de desempeña, puede ser REPARTIDOR - COORDINADO (cadena de texto)  

Ejemplo de solicitud:

```(https://mensajeria-api-java-production.up.railway.app/api/v1/empleados/12345)```

```java 
{
  "antiguedad": 0,
  "apellido": "string",
  "cedula": 0,
  "celular": "string",
  "ciudad": "string",
  "dirResidencia": "string",
  "email": "string",
  "nombre": "string",
  "rhSangre": "string",
  "tipo": "string"
}
```

La API devolverá el nuevo empleado creado en formato JSON:
```json
{
	"cedula": 12345, 
	"nombre": "Tati",
	"apellido": "guzman",
	"celular": "3165778",
	"email": "vivi123@mail.com",
	"dirResidencia": "av123",
	"ciudad": "cali",
	"antiguedad": 1.5,
	"rhSangre":"O-",
	"tipo": "REPARTIDOR"
}
```

### DELETE: /api/v1/empleados/{cedula}

❌ Este endpoint permite  **eliminar** un empleado es una funcionalidad que permite a los usuarios eliminar los datos de un empleado existente en la base de datos. Tenga en cuenta que solo se podrá eliminar si este cliente no tiene asociado un envio.

##### Parámetros de entrada:

- cedula: cedula del empleado (numero)

Ejemplo de solicitud:

```(https://mensajeria-api-java-production.up.railway.app/api/v1/empleados/12345)```

La API devolverá el mensaje:
```json
	"El empleado con cedula 12345 fue eliminado con exito"
```

## Endpoint de creación de envio :package:

### POST: /api/v1/envio

👩‍💻 Este endpoint que **crea** un envío es una funcionalidad que permite a los usuarios crear un nuevo envío en el sistema. Cuando un usuario realiza una solicitud al endpoint de creación de envío, el servidor procesa la solicitud y crea un nuevo registro de envío en la base de datos.

Para crear un envío, el usuario debe proporcionar información sobre el cliente que está realizando el envío y el paquete que se está enviando. Además, el usuario debe especificar los detalles del lugar de origen y destino del envío.

Una vez que se ha proporcionado toda la información necesaria, el servidor calcula el valor a pagar por el envío en función de los detalles del paquete y los detalles de origen y destino. El valor a pagar puede ser influenciado por factores como el tamaño del paquete, la distancia entre el origen y el destino, el método de envío, entre otros.

Finalmente, el servidor devuelve una respuesta HTTP con un código de respuesta 200 OK indicando que el envío se ha creado correctamente, y un objeto con el número de guía y el estado del paquete

##### Parámetros de entrada:

- cedulaCliente: cedula del cliente (numero)
- celularRecibe: numero de celular de quien recibe el paquete (cadena de texto)
- nombreRecibe: nombre de la persona que recibe el paquete (cadena de texto)
- ciudadDestino: ciudad de destino del paquete (cadena de texto)
- ciudadOrigen: ciudad de origen del paquete (cadena de texto)
- peso: peso en kilogramos del paquete (número decimal)
- valorDeclarado: valor en peso declarado del contenido del paquete (número decimal)
Ejemplo de solicitud:

```java 
{
  "cedulaCliente": 0,
  "celularRecibe": "string",
  "ciudadDestino": "string",
  "ciudadOrigen": "string",
  "dirDestino": "string",
  "nombreRecibe": "string",
  "peso": 0,
  "valorDeclarado": 0
}
```

La API devolverá el nuevo envio creado en formato JSON:
```json
{
	"numGuia": 4,
	"estado": "RECIBIDO"
}
```

### GET: /api/v1/envio/{num-guia}

 🕵 Este endpoint para **buscar** un envío por número de guía es una funcionalidad que permite a los usuarios buscar información específica sobre un envío registrado en el sistema utilizando su número de guía único. Cuando un usuario realiza una solicitud al endpoint de búsqueda de envío por número de guía, el servidor procesa la solicitud y devuelve la información correspondiente del envío en la base de datos.

##### Parámetros de entrada:

- numGuia: Número de guía del envio (numero)

Ejemplo de solicitud:

```(https://mensajeria-api-java-production.up.railway.app/api/v1/envio/4)```

La API devolverá el envio encontrado en formato JSON:
```json
{
	"numGuia": 4,
	"cedulaCliente": 123,
	"nombreCliente": "vivi",
	"ciudadOrigen": "Cali",
	"ciudadDestino": "Medellin",
	"dirDestino": "poblado",
	"nombreRecibe": "Juan",
	"celularRecibe": "23456",
	"valorDeclarado": 10.5,
	"peso": 1.5,
	"valorEnvio": 30000.0
}
```

### PUT: /api/v1/envio/estado

♻ Este endpoint para actualizar el estado de un envío es una funcionalidad que permite a los usuarios cambiar el estado actual de un envío registrado en el sistema. Cuando un usuario realiza una solicitud al endpoint de actualización de estado de envío, el servidor procesa la solicitud y actualiza el registro de envío correspondiente en la base de datos.

El endpoint acepta un identificador único del envío y el nuevo estado del envío. En este caso, los posibles estados del envío son REGISTRADO, EN_RUTA y ENTREGADO. Dependiendo del estado actual del envío, se pueden permitir o no ciertas transiciones de estado. Por ejemplo, no se puede cambiar el estado de un envío a ENTREGADO si su estado actual es REGISTRADO.

##### Parámetros de entrada:
- cedula: cedula del empleado (número)
- estado: estado del envio "RECIBIDO, EN_RUTA, ENTREGADO" (cadena de texto)
- numGuia: número de guía del envio asignado (número)
 
Ejemplo de solicitud:

```(https://mensajeria-api-java-production.up.railway.app/api/v1/envio/estado)```

```java 
{
  "cedulaEmpleado": 12345,
  "estado": "RECIBIDO",
  "numGuia": 4
}
```

La API devolverá el nuevo estado del envio creado en formato JSON:
```json
{
	"numGuia": 4,
	"estado": "EN_RUTA"
}
```

### GET: /api/v1/envio/estado/{estado}

📜 Este endpoint para filtrar los envíos por estado es una funcionalidad que permite a los usuarios buscar información sobre los envíos en función de su estado actual. Cuando un usuario realiza una solicitud al endpoint de filtrado de envíos por estado, el servidor procesa la solicitud y devuelve los registros de envío correspondientes en la base de datos que cumplen con los criterios de búsqueda.

##### Parámetros de entrada:

- cedula: cedula del empleado (numero)

Ejemplo de solicitud:

```(https://mensajeria-api-java-production.up.railway.app/api/v1/envio/estado/EN_RUTA)```

La API devolverá el mensaje:
```json
	[
	{
		"numGuia": 4,
		"ciudadOrigen": "Cali",
		"ciudadDestino": "Medellin",
		"dirDestino": "poblado",
		"nombreRecibe": "Juan",
		"celularRecibe": "23456",
		"horaEntrega": "Value(Year,4,19,EXCEEDS_PAD)'-'Value(MonthOfYear,2)'-'Value(DayOfMonth,2)' 'Value(HourOfDay,2)':'Value(MinuteOfHour,2)':'Value(SecondOfMinute,2)",
		"estado": "EN_RUTA",
		"valorEnvio": 30000.0,
		"cliente": {
			"cedula": 123,
			"nombre": "vivi",
			"apellido": "guzman",
			"celular": "3165778",
			"email": "vivi123@mail.com",
			"dirResidencia": "av123",
			"ciudad": "cali"
		},
		"paquete": {
			"id": 3,
			"tipo": "LIVIANO",
			"peso": 1.5,
			"valorDeclarado": 10.5
		}
	}
]
```
	

## :computer: Diagramas de clases:


### Cliente:

![Cliente](https://github.com/VivianaGuzmanBuritica/mensajeria-api-java/blob/main/mensajeria_clase_cliente.drawio.png)

### Empleado:

![Empleado](https://github.com/VivianaGuzmanBuritica/mensajeria-api-java/blob/main/mensajeria_clase_empleado.drawio.png)

### Envio:

![Envio](https://github.com/VivianaGuzmanBuritica/mensajeria-api-java/blob/main/mensajeria_clase_envio.drawio.png)


## :dizzy:  CI-Integración Continua:

Utilicé GitHub Actions :octocat: para la integración continua. GitHub Actions es una herramienta de automatización que me permite ejecutar flujos de trabajo automatizados en respuesta a eventos específicos, como la creación de una solicitud de extracción o un nuevo commit. Con esta herramienta, puedo automatizar la ejecución de pruebas, el empaquetado de mi aplicación y la implementación en diferentes entornos, todo dentro del mismo flujo de trabajo. Esto me permite ahorrar tiempo y reducir errores al asegurarme de que mi aplicación se construya correctamente en cada cambio que hago en el código. Además, GitHub Actions es fácil de configurar y personalizar para satisfacer las necesidades específicas de mi proyecto.

## :arrow_double_up:  CD-Despliegue Continuo:

Utilicé  Railway :bullettrain_side: para el despliegue continuo. La plataforma de alojamiento de aplicaciones proporciona una funcionalidad de despliegue continuo que permite implementar automáticamente cualquier cambio en el código en un entorno de producción. Al utilizar Git como fuente, Railway desencadena una construcción y despliegue automáticos en el entorno de producción en cada cambio en el repositorio sin la necesidad de intervención manual. 

	
	
 
 
 
 
