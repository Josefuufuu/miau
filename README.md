# Backend de gestión de proyectos

Aplicación construida con Spring Boot que expone servicios transaccionales para administrar usuarios, roles y permisos de una
plataforma de proyectos. Se utiliza Spring Data JPA para acceder a una base de datos H2 en memoria y se incluyen scripts para la
creación del esquema y la carga de datos iniciales.

## Características principales

- **Modelo relacional completo**: usuarios, roles, permisos, proyectos y tareas con sus respectivas relaciones.
- **Servicios de negocio**: lógica para crear, actualizar, consultar y eliminar usuarios, roles y permisos garantizando las reglas de negocio solicitadas.
- **Ejecución sin REST**: un `CommandLineRunner` muestra por consola los datos iniciales disponibles usando únicamente la capa de servicios.
- **Scripts SQL**: archivos `schema.sql` y `data.sql` que generan el esquema y cargan datos por defecto en H2 al iniciar la aplicación.
- **Pruebas unitarias**: casos con JUnit 5 que cubren operaciones CRUD de los servicios críticos.
- **Jacoco**: configuración incluida en `pom.xml` para generar reportes de cobertura durante la ejecución de pruebas.

## Requisitos previos

- Java 17 o superior.
- Maven 3.9+.

## Ejecución

1. Compilar y ejecutar pruebas con cobertura:

   ```bash
   mvn test
   ```

   El reporte de Jacoco quedará disponible en `target/site/jacoco/index.html`.

2. Ejecutar la aplicación (modo no web):

   ```bash
   mvn spring-boot:run
   ```

   Durante el arranque se listan en consola los usuarios, roles y permisos disponibles utilizando los servicios de negocio.

## Estructura de paquetes

- `com.example.miau.domain`: entidades JPA del modelo de datos.
- `com.example.miau.repository`: repositorios Spring Data JPA.
- `com.example.miau.service`: servicios y lógica de negocio.
- `src/main/resources`: configuración de Spring Boot, scripts de esquema y datos.
- `src/test/java`: pruebas unitarias de los servicios.

## Datos iniciales

Los usuarios y roles pre-cargados permiten realizar operaciones inmediatamente después de iniciar la aplicación:

- **Usuario administrador**: `admin / admin@example.com` con rol `ADMIN` (todos los permisos).
- **Usuario gestor**: `pmaria / pmaria@example.com` con rol `PROJECT_MANAGER`.

## Cobertura y calidad

- Jacoco configurado para ejecutarse automáticamente durante la fase `test`.
- Las pruebas incluidas aseguran que no existan usuarios sin roles y roles sin permisos.

