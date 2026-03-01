package com.ygo.literalura.runner;

import com.ygo.literalura.entity.Libro;
import com.ygo.literalura.entity.Autor;
import com.ygo.literalura.service.LibroService;
import com.ygo.literalura.service.GutendexService;
import com.ygo.literalura.repository.AutorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class Consultas implements CommandLineRunner {
    private final LibroService libroService;
    private final GutendexService gutendexService;
    private final AutorRepository autorRepository;

    public Consultas(LibroService libroService, GutendexService gutendexService, AutorRepository autorRepository) {
        this.libroService = libroService;
        this.gutendexService = gutendexService;
        this.autorRepository = autorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("\n*** Bienvenido a LiterAlura ***");
            System.out.println("Su Aplicación Favorita para consultar sus libros");
            System.out.println("\n=== MENÚ DE CONSULTAS ===");
            System.out.println("1. Buscar por el título del libro (Gutendex)");
            System.out.println("2. Buscar por el autor del libro (Gutendex)");
            System.out.println("3. Buscar por el título del libro o el autor del libro (Gutendex)");
            System.out.println("4. Listar todos los libros en la Base de Datos");
            System.out.println("5. Listar los más recientes");
            System.out.println("6. Listar todos los autores en la Base de Datos");
            System.out.println("7. Listar autores vivos en determinado año");
            System.out.println("8. Exhibir cantidad de libros en un determinado idioma");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            String entrada = scanner.nextLine();
            try {
                opcion = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número válido.");
                opcion = -1;
            }

            switch (opcion) {
                case 1 -> {
                    System.out.print("Ingrese el título: ");
                    String titulo = scanner.nextLine();
                    mostrarLibros(gutendexService.importarLibrosPorTitulo(titulo));
                }
                case 2 -> {
                    System.out.print("Ingrese el autor: ");
                    String autor = scanner.nextLine();
                    mostrarLibros(gutendexService.importarLibrosPorAutor(autor));
                }
                case 3 -> {
                    System.out.print("Ingrese título o autor: ");
                    String query = scanner.nextLine();
                    mostrarLibros(gutendexService.importarLibros(query));
                }
                case 4 -> {
                    System.out.println("=== Listado de todos los libros en BD ===");
                    mostrarLibros(libroService.listarTodos());
                }
                case 5 -> {
                    System.out.println("=== Listado de los libros más recientes ===");
                    mostrarLibros(libroService.listarMasRecientes());
                }
                case 6 -> {
                    System.out.println("=== Lista de autores en BD ===");
                    autorRepository.findAll().forEach(autor -> {
                        System.out.println("Autor: " + autor.getNombre() +
                                " | Nacimiento: " + autor.getAnioNacimiento() +
                                " | Fallecimiento: " + autor.getAnioFallecimiento());
                    });
                }
                case 7 -> {
                    System.out.print("Ingrese el año: ");
                    String entradaAnio = scanner.nextLine();
                    try {
                        int anio = Integer.parseInt(entradaAnio);
                        System.out.println("=== Autores vivos en " + anio + " ===");
                        autorRepository.findByAnioNacimientoLessThanEqualAndAnioFallecimientoGreaterThanEqual(anio, anio)
                                .forEach(autor -> {
                                    System.out.println("Autor: " + autor.getNombre() +
                                            " | Nacimiento: " + autor.getAnioNacimiento() +
                                            " | Fallecimiento: " + autor.getAnioFallecimiento());
                                });
                    } catch (NumberFormatException e) {
                        System.out.println("Debe ingresar un año válido.");
                    }
                }
                case 8 -> {
                    System.out.println("Seleccione el idioma:");
                    System.out.println("1. Español (es)");
                    System.out.println("2. Inglés (en)");
                    System.out.println("3. Francés (fr)");
                    System.out.println("4. Alemán (de)");
                    System.out.println("5. Italiano (it)");
                    System.out.print("Opción: ");

                    String entradaIdioma = scanner.nextLine();
                    String codigoIdioma = "";

                    switch (entradaIdioma) {
                        case "1" -> codigoIdioma = "es";
                        case "2" -> codigoIdioma = "en";
                        case "3" -> codigoIdioma = "fr";
                        case "4" -> codigoIdioma = "de";
                        case "5" -> codigoIdioma = "it";
                        default -> {
                            System.out.println("Opción inválida, intente de nuevo.");
                            codigoIdioma = null;
                        }
                    }

                    if (codigoIdioma != null) {
                        long cantidad = libroService.contarPorIdioma(codigoIdioma);
                        System.out.println("Cantidad de libros en idioma '" + codigoIdioma + "': " + cantidad);
                    }
                }
                case 0 -> System.out.println("Saliendo del programa... Realizado por Y0rl4ndi Gr4j4l3s");
                default -> {
                    if (opcion != -1) {
                        System.out.println("Opción inválida, intente de nuevo.");
                    }
                }
            }
        }
    }

    private void mostrarLibros(List<Libro> libros) {
        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros.");
        } else {
            for (Libro libro : libros) {
                System.out.println("Título: " + libro.getTitulo());
                System.out.println("Autor: " + (libro.getAutor() != null ? libro.getAutor().getNombre() : "Desconocido"));
                System.out.println("Descripción: " + libro.getDescripcion());
                System.out.println("Idioma: " + libro.getIdioma());
                System.out.println("Descargas: " + libro.getDescargas());
                System.out.println("-------------------------");
            }
        }
    }
}
