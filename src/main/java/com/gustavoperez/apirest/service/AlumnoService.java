package com.gustavoperez.apirest.service;

import com.gustavoperez.apirest.model.Alumno;
import com.gustavoperez.apirest.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * @Service
 * Esta es la clase AlumnoService que actúa como un servicio en Spring.
 * @author Gustavo Pérez
 * @version 1.0
 */
@Service
public class AlumnoService {

    /**
     * Repositorio para manejar las operaciones de los alumnos en la base de datos.
     * Este campo será inyectado automáticamente por Spring.
     */
    @Autowired
    private AlumnoRepository alumnoRepository;

    /**
     * Este método se utiliza para crear un nuevo alumno.
     * @param alumno El alumno que se va a crear.
     * @return El alumno creado.
     */
    public Alumno crearAlumno(Alumno alumno) {
        return alumnoRepository.save(new Alumno(alumno.getDni(), alumno.getNombre(), alumno.getApellidos()));
    }

    /**
     * Este método se utiliza para obtener un alumno por su DNI.
     * @param dni El DNI del alumno que se va a buscar.
     * @return El alumno encontrado.
     */
    public Alumno obtenerAlumnoPorDni(String dni){
        Optional<Alumno> optionalAlumno = Optional.ofNullable(alumnoRepository.findByDni(dni));
        return optionalAlumno.get();
    }

    /**
     * Este método se utiliza para obtener todos los alumnos.
     * @return Una lista de todos los alumnos.
     */
    public List<Alumno> obtenerAlumnos() {
        return alumnoRepository.findAll();
    }

    /**
     * Este método se utiliza para eliminar un alumno por su ID.
     * @param id El ID del alumno que se va a eliminar.
     */
    public void deleteAlumno(Long id){
        alumnoRepository.deleteById(id);
    }

    /**
     * Este método se utiliza para guardar un alumno.
     * @param alumno El alumno que se va a guardar.
     * @return El alumno guardado.
     */
    public Alumno guardar(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    /**
     * Este método se utiliza para buscar un alumno por su DNI.
     * @param dni El DNI del alumno que se va a buscar.
     * @return El alumno encontrado.
     */
    public Alumno buscarPorDni (String dni) {
        return alumnoRepository.findByDni(dni);
    }

    /**
     * Este método se utiliza para buscar un alumno por su ID.
     * @param id El ID del alumno que se va a buscar.
     * @return El alumno encontrado.
     */
    public Alumno buscarPorId(Long id){
        Optional<Alumno> optionalAlumno = alumnoRepository.findById(id);
        return optionalAlumno.get();
    }

    /**
     * Este método se utiliza para verificar si existe un alumno con un ID específico.
     * @param id El ID del alumno que se va a verificar.
     * @return Verdadero si el alumno existe, falso en caso contrario.
     */
    public boolean existeId (Long id) { return alumnoRepository.existsById(id); }

}
