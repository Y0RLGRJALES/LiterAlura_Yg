package com.ygo.literalura.controller;

import com.ygo.literalura.entity.Libro;
import com.ygo.literalura.service.LibroService;
import com.ygo.literalura.service.GutendexService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libros")
public class LibroController {
    private final LibroService libroService;
    private final GutendexService gutendexService;

    public LibroController(LibroService libroService, GutendexService gutendexService) {
        this.libroService = libroService;
        this.gutendexService = gutendexService;
    }

    // 🔹 Buscar por título directamente en Gutendex
    @GetMapping("/buscar/titulo/{titulo}")
    public List<Libro> buscarPorTitulo(@PathVariable String titulo) {
        return gutendexService.importarLibros(titulo);
    }

    // 🔹 Buscar por autor directamente en Gutendex
    @GetMapping("/buscar/autor/{autor}")
    public List<Libro> buscarPorAutor(@PathVariable String autor) {
        return gutendexService.importarLibros(autor);
    }

    // 🔹 Buscar por título o autor (flexible)
    @GetMapping("/buscar/{query}")
    public List<Libro> buscarPorTituloOAutor(@PathVariable String query) {
        return gutendexService.importarLibros(query);
    }

    // 🔹 Listar todos los libros guardados en BD
    @GetMapping("/todos")
    public List<Libro> listarTodos() {
        return libroService.listarTodos();
    }

    // 🔹 Listar los más recientes
    @GetMapping("/recientes")
    public List<Libro> listarMasRecientes() {
        return libroService.listarMasRecientes();
    }
}