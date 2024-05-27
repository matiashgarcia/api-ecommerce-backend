# UADE - Aplicaciones Interactivas 
## Proyecto: Ecommerce (Back-End) - Api Rest
### Tecnologías: Java 17, Spring Boot

---

## Para conectar a base de datos

Crear un archivo llamado **secrets.properties** dentro de **src/main/resources** y colocarle lo siguiente:

```bash
spring.datasource.url=jdbc:mysql://localhost:3306/<DATABASE_NAME>
spring.datasource.username=<DATABASE_USERNAME>
spring.datasource.password=<DATABASE_PASSWORD>
application.security.jwt.secretKey=<RANDOM_UUID_VALUE>
```