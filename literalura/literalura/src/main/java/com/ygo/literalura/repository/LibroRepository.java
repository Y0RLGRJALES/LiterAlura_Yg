package com.ygo.literalura.repository;

import com.ygo.literalura.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    // Listar todos los libros
    List<Libro> findAll();

    // Contar libros por idioma
    long countByIdioma(String idioma);

    // Buscar libros por título (contiene texto, ignorando mayúsculas/minúsculas)
    List<Libro> findByTituloContainingIgnoreCase(String titulo);

    // Buscar libros por autor (relación con entidad Autor)
    List<Libro> findByAutor_NombreContainingIgnoreCase(String nombreAutor);

    // Listar todos los libros ordenados por id descendente
    List<Libro> findAllByOrderByIdDesc();

    // Listar los 10 más recientes
    List<Libro> findTop10ByOrderByIdDesc();
}
