package com.ygo.literalura.service;

import com.ygo.literalura.entity.Libro;
import com.ygo.literalura.repository.LibroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {
    private final LibroRepository libroRepository;

    public LibroService(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    // Listar todos los libros
    public List<Libro> listarTodos() {
        return libroRepository.findAll();
    }

    // Listar los más recientes (últimos 10)
    public List<Libro> listarMasRecientes() {
        return libroRepository.findTop10ByOrderByIdDesc();
    }

    // Contar libros por idioma
    public long contarPorIdioma(String idioma) {
        return libroRepository.countByIdioma(idioma);
    }

    // Buscar libros por título
    public List<Libro> buscarPorTitulo(String titulo) {
        return libroRepository.findByTituloContainingIgnoreCase(titulo);
    }

    // Buscar libros por autor
    public List<Libro> buscarPorAutor(String nombreAutor) {
        return libroRepository.findByAutor_NombreContainingIgnoreCase(nombreAutor);
    }
}
