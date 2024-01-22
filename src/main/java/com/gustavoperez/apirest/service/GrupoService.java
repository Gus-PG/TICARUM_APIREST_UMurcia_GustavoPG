package com.gustavoperez.apirest.service;

import com.gustavoperez.apirest.model.Grupo;
import com.gustavoperez.apirest.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @Component
 * Esta es la clase GrupoService que actúa como un componente de Spring.
 * @author Gustavo Pérez
 * @version 1.0
 */
@Component
public class GrupoService {

    /**
     * Repositorio para manejar las operaciones de los grupos en la base de datos.
     * Este campo será inyectado automáticamente por Spring.
     */
    @Autowired
    private GrupoRepository grupoRepository;

    /**
     * Crea un nuevo grupo en la base de datos.
     *
     * @param grupo El grupo que se va a crear.
     * @return El grupo que se ha creado.
     */
    public Grupo crearGrupo(Grupo grupo) { return grupoRepository.save(grupo); }

    /**
     * Elimina un grupo de la base de datos.
     *
     * @param grupo El grupo que se va a eliminar.
     */
    public void eliminar(Grupo grupo){ grupoRepository.delete(grupo); }

    /**
     * Guarda un grupo en la base de datos.
     * Si el grupo ya existe, se actualizará. Si no existe, se creará.
     *
     * @param grupo El grupo que se va a guardar.
     * @return El grupo que se ha guardado.
     */
    public Grupo guardar(Grupo grupo){
        return grupoRepository.save(grupo);
    }

    /**
     * Busca un grupo por su ID.
     *
     * @param id El ID del grupo que se va a buscar.
     * @return El grupo con el ID proporcionado.
     */
    public Grupo buscarPorId(Long id){
        Optional<Grupo> optionalGrupo = grupoRepository.findById(id);
        return optionalGrupo.get();
    }

    /**
     * Obtiene un grupo por su ID.
     *
     * @param id El ID del grupo que se va a obtener.
     * @return Un Optional que puede contener el grupo con el ID proporcionado.
     */
    public Optional<Grupo> obtenerPorId(Long id){
        return grupoRepository.findById(id);
    }

    /**
     * Busca los grupos asociados a una asignatura.
     *
     * @param asignaturaId El ID de la asignatura cuyos grupos se van a buscar.
     * @return Una lista de grupos asociados a la asignatura.
     */
    public List<Grupo> buscarPorAsignaturaId(Long asignaturaId) {
        return grupoRepository.findByAsignaturaId(asignaturaId); }

    /**
     * Busca los grupos asociados a un alumno.
     *
     * @param alumnoId El ID del alumno cuyos grupos se van a buscar.
     * @return Una lista de grupos asociados al alumno.
     */
    public List<Grupo> buscarPorAlumnoId(Long alumnoId) {
        return grupoRepository.findGruposByAlumnosId(alumnoId); }

}
