package com.ygo.literalura.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "libro")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(length = 2000)
    private String descripcion;

    private String idioma;

    @JsonProperty("download_count")
    private Integer descargas;

    // Relación con Autor
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "autor_id")
    private Autor autor;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getIdioma() { return idioma; }
    public void setIdioma(String idioma) { this.idioma = idioma; }

    public Integer getDescargas() { return descargas; }
    public void setDescargas(Integer descargas) { this.descargas = descargas; }

    public Autor getAutor() { return autor; }
    public void setAutor(Autor autor) { this.autor = autor; }
}