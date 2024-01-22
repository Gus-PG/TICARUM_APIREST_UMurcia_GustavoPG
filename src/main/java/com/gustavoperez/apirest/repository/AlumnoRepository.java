package com.gustavoperez.apirest.repository;

import com.gustavoperez.apirest.model.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Repository
 * Interfaz para el repositorio de alumnos.
 * Esta interfaz se encarga de las operaciones de la base de datos relacionadas con los alumnos.
 */
@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

    /**
     * Busca un alumno por su DNI.
     *
     * @param dni El DNI del alumno que se va a buscar.
     * @return El alumno encontrado.
     */
    Alumno findByDni(String dni);

}
