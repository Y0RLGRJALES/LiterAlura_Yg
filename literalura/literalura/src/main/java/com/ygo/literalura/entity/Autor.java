package com.ygo.literalura.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "autor")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @JsonProperty("birth_year")
    private Integer anioNacimiento;

    @JsonProperty("death_year")
    private Integer anioFallecimiento;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getAnioNacimiento() { return anioNacimiento; }
    public void setAnioNacimiento(Integer anioNacimiento) { this.anioNacimiento = anioNacimiento; }

    public Integer getAnioFallecimiento() { return anioFallecimiento; }
    public void setAnioFallecimiento(Integer anioFallecimiento) { this.anioFallecimiento = anioFallecimiento; }
}