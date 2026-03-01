package com.ygo.literalura.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ygo.literalura.entity.Autor;
import com.ygo.literalura.entity.Libro;
import com.ygo.literalura.repository.LibroRepository;
import com.ygo.literalura.client.GutendexClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GutendexService {
    private final GutendexClient gutendexClient;
    private final LibroRepository libroRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public GutendexService(GutendexClient gutendexClient, LibroRepository libroRepository) {
        this.gutendexClient = gutendexClient;
        this.libroRepository = libroRepository;
    }

    // 🔹 Buscar exclusivamente por título
    public List<Libro> importarLibrosPorTitulo(String query) {
        List<Libro> libros = new ArrayList<>();
        try {
            String json = gutendexClient.buscarLibros(query);
            JsonNode root = objectMapper.readTree(json);

            for (JsonNode item : root.get("results")) {
                String titulo = item.get("title").asText();
                if (titulo.toLowerCase().contains(query.toLowerCase())) {
                    Libro libro = mapearLibro(item);
                    libroRepository.save(libro);
                    libros.add(libro);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return libros;
    }

    // 🔹 Buscar exclusivamente por autor
    public List<Libro> importarLibrosPorAutor(String query) {
        List<Libro> libros = new ArrayList<>();
        try {
            String json = gutendexClient.buscarLibros(query);
            JsonNode root = objectMapper.readTree(json);

            for (JsonNode item : root.get("results")) {
                if (item.has("authors")) {
                    for (JsonNode autorNode : item.get("authors")) {
                        String nombreAutor = autorNode.get("name").asText();
                        if (nombreAutor.toLowerCase().contains(query.toLowerCase())) {
                            Libro libro = mapearLibro(item);
                            libroRepository.save(libro);
                            libros.add(libro);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return libros;
    }

    // 🔹 Búsqueda libre (título o autor)
    public List<Libro> importarLibros(String query) {
        List<Libro> libros = new ArrayList<>();
        try {
            String json = gutendexClient.buscarLibros(query);
            JsonNode root = objectMapper.readTree(json);

            for (JsonNode item : root.get("results")) {
                Libro libro = mapearLibro(item);
                libroRepository.save(libro);
                libros.add(libro);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return libros;
    }

    // 🔹 Método auxiliar para mapear un libro con su autor
    private Libro mapearLibro(JsonNode item) {
        Libro libro = new Libro();
        libro.setTitulo(item.get("title").asText());

        // Mapear autor con nombre, nacimiento y fallecimiento
        Autor autor = new Autor();
        if (item.has("authors") && item.get("authors").size() > 0) {
            JsonNode autorNode = item.get("authors").get(0);
            autor.setNombre(autorNode.get("name").asText());

            if (autorNode.has("birth_year") && !autorNode.get("birth_year").isNull()) {
                autor.setAnioNacimiento(autorNode.get("birth_year").asInt());
            }
            if (autorNode.has("death_year") && !autorNode.get("death_year").isNull()) {
                autor.setAnioFallecimiento(autorNode.get("death_year").asInt());
            }
        } else {
            autor.setNombre("Autor desconocido");
        }
        libro.setAutor(autor);

        // Mapear descripción
        if (item.has("subjects") && item.get("subjects").size() > 0) {
            libro.setDescripcion(item.get("subjects").toString());
        } else {
            libro.setDescripcion("Sin descripción disponible");
        }

        // Mapear idioma
        if (item.has("languages") && item.get("languages").size() > 0) {
            libro.setIdioma(item.get("languages").get(0).asText());
        } else {
            libro.setIdioma("Desconocido");
        }

        // Mapear descargas
        if (item.has("download_count")) {
            libro.setDescargas(item.get("download_count").asInt());
        } else {
            libro.setDescargas(0);
        }

        return libro;
    }
}
