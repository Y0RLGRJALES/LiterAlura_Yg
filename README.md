# LiterAlura 📚

Bienvenido a **LiterAlura**,  
mi aplicación favorita para consultar y gestionar libros desde la API de Gutendex, con almacenamiento en PostgreSQL.  
Este proyecto lo desarrollé como parte de mi portafolio didáctico, integrando **Spring Boot**, **JPA/Hibernate** y **PostgreSQL**.

---

## 🚀 Funcionalidades principales

El menú de consultas incluye:

1. **Buscar por el título del libro (Gutendex)**  
2. **Buscar por el autor del libro (Gutendex)**  
3. **Buscar por el título o el autor del libro (Gutendex)**  
4. **Listar todos los libros en la Base de Datos**  
5. **Listar los más recientes**  
6. **Listar todos los autores en la Base de Datos**  
7. **Listar autores vivos en determinado año**  
8. **Exhibir cantidad de libros en un determinado idioma**  

---

## 🛠️ Tecnologías utilizadas

- **Java 17**  
- **Spring Boot 3**  
- **Spring Data JPA**  
- **PostgreSQL**  
- **Gutendex API** (fuente de datos de libros)  

---

## 🗂️ Estructura del proyecto

- `entity/` → Entidades `Libro` y `Autor` con relación entre ellas.  
- `repository/` → Repositorios JPA (`LibroRepository`, `AutorRepository`).  
- `service/` → Lógica de negocio (`LibroService`, `GutendexService`).  
- `runner/` → Clase `Consultas` con el menú interactivo en consola.  

---

## ⚙️ Configuración

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/Y0RLGRJALES/LiterAlura_Yg
```

2. Configurar la base de datos en `application.properties`:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_password
   spring.jpa.hibernate.ddl-auto=update
   ```
3. Ejecutar la aplicación:

   ```bash
   mvn spring-boot:run
   ```

---

## 🌍 Idiomas soportados

Para la opción 8, se utilizan abreviaturas ISO 639-1:

* Español = `es`
* Inglés = `en`
* Francés = `fr`
* Alemán = `de`
* Italiano = `it`

---

## ✨ Autor

Proyecto desarrollado por Y0RLGRJALES,
Ingeniera de software especializada en backend y documentación didáctica.
Este proyecto forma parte de mi portafolio profesional, mostrando integración de APIs externas, consultas flexibles y visualización clara de datos en PostgreSQL.

