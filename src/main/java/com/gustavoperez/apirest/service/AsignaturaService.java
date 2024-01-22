package com.gustavoperez.apirest.service;

import com.gustavoperez.apirest.model.Asignatura;
import com.gustavoperez.apirest.repository.AsignaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Service
 * Esta es la clase AsignaturaService que actúa como un servicio en Spring.
 * @author Gustavo Pérez
 * @version 1.0
 */
@Service
public class AsignaturaService {

    /**
     * Repositorio para manejar las operaciones de las asignaturas en la base de datos.
     * Este campo será inyectado automáticamente por Spring.
     */
    @Autowired
    private AsignaturaRepository asignaturaRepository;

    /**
     * Crea una nueva asignatura en la base de datos.
     *
     * @param id El ID que se asignará a la nueva asignatura.
     * @param newAsignatura La asignatura que se va a crear.
     * @return La asignatura que se ha creado.
     */
    public Asignatura crearAsignatura(Long id, Asignatura newAsignatura){
        newAsignatura.setId(id);
        return asignaturaRepository.save(newAsignatura);
    }

    /**
     * Busca una asignatura por su ID.
     *
     * @param id El ID de la asignatura que se va a buscar.
     * @return Un Optional que puede contener la asignatura con el ID proporcionado.
     */
    public Optional<Asignatura> buscarPorId(Long id){
        return asignaturaRepository.findById(id);
    }

    /**
     * Guarda una asignatura en la base de datos.
     * Si la asignatura ya existe, se actualizará. Si no existe, se creará.
     *
     * @param asignatura La asignatura que se va a guardar.
     * @return La asignatura que se ha guardado.
     */
    public Asignatura guardar(Asignatura asignatura){
        return asignaturaRepository.save(asignatura);
    }

    /**
     * Elimina una asignatura de la base de datos.
     *
     * @param asignatura La asignatura que se va a eliminar.
     */
    public void eliminar(Asignatura asignatura) {
        asignaturaRepository.delete(asignatura);
    }
}
