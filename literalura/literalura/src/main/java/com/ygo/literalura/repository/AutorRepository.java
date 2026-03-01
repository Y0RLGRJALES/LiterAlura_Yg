package com.ygo.literalura.repository;

import com.ygo.literalura.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    // Lista todos los autores (ya lo hace findAll() por defecto, pero lo dejamos explícito)
    List<Autor> findAll();

    // Lista autores vivos en un año determinado
    // Ejemplo: si paso 1900, devuelve autores cuyo nacimiento <= 1900 y fallecimiento >= 1900
    List<Autor> findByAnioNacimientoLessThanEqualAndAnioFallecimientoGreaterThanEqual(Integer nacimiento, Integer fallecimiento);
}
