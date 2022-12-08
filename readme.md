# PriceApplicator
<p style="font-size: 20px; text-align: justify;">
La Aplicación esta desarrollada en spring-boot con Java 11
</p>


## Correr la Aplicación:
<p style="font-size: 16px; text-align: justify;">
la aplicación esta configurada para correr en el puerto 8080.<br/>
Es necesario estar ubicados dentro de la carpeta del proyecto al
mismo nivel del archivo POM.XML

para correr la aplicación desde la línea de comando
</p>

~~~
$ mvn spring-boot:run
~~~

## Correr los test:
<p style="font-size: 16px; text-align: justify;">
Es necesario estar ubicados dentro de la carpeta del proyecto al
mismo nivel del archivo POM.XML
</p>

~~~
$ mvn test
~~~


## Acceder al endpoint de la API:

### End Point:
http://localhost:8080/api/v1/pricetoapply?applicationDate=2020-06-14T10:00:00&productId=35455&brandId=1
<br/>para probar el endpoint es recomendable utilizar **Postman** o **Insomnia**
<br/>para consultar más detalles del endpoint pueden acceder a:
<br/>http://localhost:8080/swagger-ui/index.html
#### Parametros:

- **applicationDate**: Fecha y hora de la Petición ej: 2020-06-14T10:00:00
- **productId**: ID del producto, ej: 35455
- **brandId**: ID de la marca, ej: 1
