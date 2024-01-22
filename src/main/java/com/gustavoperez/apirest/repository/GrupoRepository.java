package com.gustavoperez.apirest.repository;

import com.gustavoperez.apirest.model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Repository
 * Interfaz para el repositorio de grupos.
 * Esta interfaz se encarga de las operaciones de la base de datos relacionadas con los grupos.
 */
@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {

    /**
     * Busca los grupos asociados a una asignatura.
     *
     * @param asignaturaId El ID de la asignatura cuyos grupos se van a buscar.
     * @return Una lista de grupos asociados a la asignatura.
     */
    List<Grupo> findByAsignaturaId(Long asignaturaId);

    /**
     * Busca los grupos asociados a un alumno.
     *
     * @param alumnoId El ID del alumno cuyos grupos se van a buscar.
     * @return Una lista de grupos asociados al alumno.
     */
    List<Grupo> findGruposByAlumnosId (Long alumnoId);
}
