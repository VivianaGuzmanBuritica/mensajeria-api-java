# API REST de reservas de habitación de hotel :hotel::sparkles:

<div align= "rigth">Esta API REST de reservas de habitación de hotel es una aplicación Spring Boot que te permite gestionar reservas de habitaciones en un hotel. El proyecto esta construido con la versión de '2.7.10' de Spring Boot, un gestor de dependecias con  Gradle-Groovy compatible con Java 11:coffee:, con un motor de base de datos en MySQL y persistencia de datos co JPA e Hibernate.Además de contar con Integración Continua con Github Actions y despliegue con Railway.

Las principales dependencias utilizadas son:

- Spring Data JPA (Persiste bases de datos SQL utilizando Java Persistence API mediante Spring Data y Hibernate.)
- Spring Web (Construye aplicaciones web, incluyendo RESTful, utilizando Spring MVC. Utiliza Apache Tomcat como contenedor integrado predeterminado.)
- JUnit (Testeo de pruebas unitarias)

Adicionalmente esta API se encuentra documentada con Swagger y podrá probar la funcionalidad de  cada una de las clases en los Endpoints disponibles en el siguiente link :eyes: :link:[Swagger documentación reservas](https://reservas-hotel-api-java-production.up.railway.app/swagger-ui/index.html#/)
## :computer: Endpoints:

### Endpoint de creación de cliente :raising_hand:

#### POST: /api/v1/cliente

Crea un nuevo cliente en la base de datos con la información proporcionada en el cuerpo de la solicitud.

##### Parámetros de entrada:

cedula: cedula del cliente (numero)
nombre: nombre del cliente (cadena de texto)
apellido: apellido del cliente (cadena de texto)
direccion: direccion del cliente (cadena de texto)
edad: edad del cliente (numero)
email: dirección de correo electrónico del cliente (cadena de texto)


Ejemplo de solicitud:

```java {.highlight .highlight-source-java .bg-black}
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
```java
{
"cedula": 1234,
"nombre": "Juan",
"apellido": "Pérez",
"direccion": "Pérez",
"edad": 31,
"email": "juan.perez@example.com"
}
```

### Endpoint de creación de reservas :key:

#### POST: /api/v1/cliente/{cedula}/habitacion/{numero}/fecha/{fecha}/reservar

Crea una nueva reserva en la base de datos con la información proporcionada en el cuerpo de la solicitud.

Parámetros de entrada:


##### Parámetros de entrada:

cedula: cedula del cliente creado(numero)
numero: numero de la habitacion(numero)
fecha: fecha que se desea reservar (cadena de texto con la fecha en formato YYYY- mm - dd)


Ejemplo de solicitud:

```(http://localhost:8080/api/v1/cliente/0/habitacion/1/fecha/2023-05-10/reservar)```

La API devolverá la nueva reserva creada en formato JSON:
```java
{
"codigo": 1,
"fecha": "2023-04-02T02:22:04.416Z",
"habitacion": {
    "numero": 1,
    "precioBase": 10000.0,
    "tipo": "estandar"
    },
"total": 10000.0
}
```

### Endpoint consulta de habitaciones disponibles :hotel:

#### POST: /api/v1/cliente/{cedula}/habitacion/{tipo}/consultar

Mostar una lista de las habitaciones disponibles sin reserva en la base de datos con la información proporcionada en el cuerpo de la solicitud.

Parámetros de entrada:


##### Parámetros de entrada:

cedula: cedula del cliente creado(numero)
tipo: tipo de la habitacion(cadena de texto)

Ejemplo de solicitud:

```(http://localhost:8080/api/v1/cliente/1234/habitacion/estandar/consultar)```

La API devolverá la nueva reserva creada en formato JSON:
```java
[
    [
        3,
        10000,
        "estandar"
    ]
]
```

## :computer: Diagramas de clases:

### Cliente:

![Cliente](https://github.com/VivianaGuzmanBuritica/reservas-hotel-api-java/blob/main/clase_cliente.drawio.png)

### Reserva:

![Cliente](https://github.com/VivianaGuzmanBuritica/reservas-hotel-api-java/blob/main/clase_reserva.drawio.png)

### Habitacion:

![Cliente](https://github.com/VivianaGuzmanBuritica/reservas-hotel-api-java/blob/main/clase_habitacion.drawio.png)


## :dizzy:  CI-Integración Continua:

Utilicé GitHub Actions para la integración continua. GitHub Actions es una herramienta de automatización que me permite ejecutar flujos de trabajo automatizados en respuesta a eventos específicos, como la creación de una solicitud de extracción o un nuevo commit. Con esta herramienta, puedo automatizar la ejecución de pruebas, el empaquetado de mi aplicación y la implementación en diferentes entornos, todo dentro del mismo flujo de trabajo. Esto me permite ahorrar tiempo y reducir errores al asegurarme de que mi aplicación se construya correctamente en cada cambio que hago en el código. Además, GitHub Actions es fácil de configurar y personalizar para satisfacer las necesidades específicas de mi proyecto.

## :arrow_double_up:  CD-Despliegue Continuo:

Utilicé  Railway para el despliegue continuo. La plataforma de alojamiento de aplicaciones proporciona una funcionalidad de despliegue continuo que permite implementar automáticamente cualquier cambio en el código en un entorno de producción. Al utilizar Git como fuente, Railway desencadena una construcción y despliegue automáticos en el entorno de producción en cada cambio en el repositorio sin la necesidad de intervención manual. 
 
 
 
 