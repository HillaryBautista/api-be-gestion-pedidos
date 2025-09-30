# api-be-gestion-pedidos

Backend desarrollado en **Java Spring Boot 3.0**, parte del sistema de gestión de pedidos. Implementa una arquitectura RESTful con seguridad, persistencia y modularidad.

## Características principales
- Framework: Spring Boot 3.0 (Java 17/21)
- Persistencia con Spring Data JPA (ej. PostgreSQL, MySQL)
- Seguridad con Spring Security y JWT
- Exposición de APIs RESTful para la gestión de pedidos
- Configuración externa en `application.properties` / `application.yml`
- Documentación de API con Springdoc/OpenAPI (Swagger) si está configurado

## Arquitectura del proyecto
- **Controladores (`@RestController`)**: definen los endpoints de la API
- **Servicios (`@Service`)**: encapsulan la lógica de negocio
- **Repositorios (`@Repository`)**: gestionan el acceso a la base de datos mediante JPA
- **Entidades (`@Entity`)**: mapeo de tablas a clases Java
- **DTOs**: transferencia de datos entre capas

### Estructura de carpetas
```
src/
 └── main/
     ├── java/com/tuempresa/gestionpedidos/
     │    ├── controller/
     │    ├── service/
     │    ├── repository/
     │    └── model/
     └── resources/
          ├── application.properties
          └── application.yml
```

## Requisitos previos
- JDK 17 o superior (Java 21 recomendado)
- Maven 3.9+
- PostgreSQL/MySQL (según configuración en `application.properties`)

## Configuración de entorno
En `src/main/resources/application.properties` o `application.yml` definir:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/gestion_pedidos
spring.datasource.username=usuario
spring.datasource.password=contraseña
spring.jpa.hibernate.ddl-auto=update
server.port=8080
jwt.secret=tu_clave_secreta
```

## Instalación y ejecución
```bash
# Clonar el repositorio
git clone <url-del-repo>
cd api-be-gestion-pedidos

# Compilar y empaquetar
mvn clean install

# Ejecutar la aplicación
mvn spring-boot:run
```
Por defecto la aplicación se levanta en: http://localhost:8082/

## Compilación y despliegue
Para generar un JAR ejecutable:
```bash
mvn clean package
java -jar target/api-be-gestion-pedidos-0.0.1-SNAPSHOT.jar
```

## Documentación Swagger
Si Springdoc/OpenAPI está habilitado, la documentación estará disponible en:
http://localhost:8082/swagger-ui.html

## Autor
Hillary Bautista
