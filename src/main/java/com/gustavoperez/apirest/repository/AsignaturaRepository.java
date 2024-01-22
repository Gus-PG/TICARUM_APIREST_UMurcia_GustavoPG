package com.gustavoperez.apirest.repository;

import com.gustavoperez.apirest.model.Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * @Repository
 * Interfaz para el repositorio de asignaturas.
 * Esta interfaz se encarga de las operaciones de la base de datos relacionadas con las asignaturas.
 */
@Repository
public interface AsignaturaRepository extends JpaRepository<Asignatura, Long> {

}
