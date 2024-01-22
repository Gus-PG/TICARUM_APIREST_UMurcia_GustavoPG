package com.gustavoperez.apirest.controller;

import com.gustavoperez.apirest.service.AlumnoService;
import com.gustavoperez.apirest.service.GrupoService;
import com.gustavoperez.apirest.model.*;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador REST para manejar las operaciones de los alumnos.
 * Este controlador maneja las solicitudes HTTP a la ruta "api/alumnos".
 *
 * @author Gustavo Pérez
 * @version 1.0
 */
@RestController
@RequestMapping("api/alumnos")
public class AlumnoController {

    /**
     * Servicio para manejar las operaciones de alumnos.
     * Este campo será inyectado automáticamente por Spring.
     */
    @Autowired
    private AlumnoService alumnoService;

    /**
     * Servicio para manejar las operaciones de grupos.
     * Este campo será inyectado automáticamente por Spring.
     */
    @Autowired
    private GrupoService grupoService;

    /**
     * Obtiene una lista de todos los alumnos.
     *
     * @return Una lista de objetos Alumno.
     */
    @GetMapping
    public ResponseEntity<?> getAllAlumnos() {
        List<Alumno> alumnos = alumnoService.obtenerAlumnos();
        if (alumnos.isEmpty()) {
            return new ResponseEntity<>("No hay alumnos que mostrar.", HttpStatus.NO_CONTENT);
        } else {
            List<AlumnoDTO> alumnosDTO = alumnos.stream()
                    .map(alumno -> new AlumnoDTO(alumno.getId(), alumno.getNombre(),
                            alumno.getApellidos(), alumno.getDni()))
                    .collect(Collectors.toList());
            return new ResponseEntity<>(alumnosDTO, HttpStatus.OK);
        }
    }


    /**
     * Obtiene los grupos a los que pertenece un alumno determinado.
     *
     * @param alumnoId El ID del alumno cuyos grupos se quieren obtener.
     * @return ResponseEntity que contiene un conjunto de grupos si el alumno se encuentra,
     *         o lanza una excepción si el alumno no se encontró.
     */
    @GetMapping("/{alumnoId}")
    public ResponseEntity<List<GrupoDTO>> obtenerGruposDelAlumno(@PathVariable("alumnoId") Long alumnoId) {
        if (!alumnoService.existeId(alumnoId)) {
            throw new ResourceNotFoundException("No encontrado alumno con Id [" + alumnoId + "].");
        }
        List<Grupo> grupos = grupoService.buscarPorAlumnoId(alumnoId);
        List<GrupoDTO> gruposDTO = grupos.stream()
                .map(grupo -> {
                    AsignaturaDTO asignaturaDTO = new AsignaturaDTO(grupo.getAsignatura().getCodigo(),
                            grupo.getAsignatura().getNombre());

                    return new GrupoDTO(grupo.getId(), grupo.getCodigo(), grupo.getNombre(), asignaturaDTO);
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(gruposDTO);
    }


    /**
     * Crea un nuevo alumno comprobando previamente que no haya ningún
     * alumno con el mismo DNI en la base de datos..
     *
     * @param alumno El objeto Alumno que se va a crear.
     * @return El objeto Alumno que se ha creado.
     */
    @PostMapping
    public ResponseEntity<?> crearAlumno(@RequestBody Alumno alumno) {
        Alumno alumnoExistente = alumnoService.buscarPorDni(alumno.getDni());
        if (alumnoExistente != null) {
            return new ResponseEntity<>("Ya hay un alumno en la Base de Datos con Dni ["
                    + alumno.getDni() + "].", HttpStatus.CONFLICT);
        } else {
            Alumno _alumno = alumnoService.crearAlumno(alumno);
            return new ResponseEntity<>(_alumno, HttpStatus.CREATED);
        }
    }


    /**
     * Elimina un alumno por su ID.
     *
     * @param id El ID del alumno que se quiere eliminar.
     */
    @DeleteMapping("{id}")
    public void deleteAlumnoById(@PathVariable("id") Long id) {
        alumnoService.deleteAlumno(id);
    }


    /**
     * Obtiene un alumno por su DNI.
     *
     * @param dni El DNI del alumno que se quiere obtener.
     * @return El objeto Alumno que corresponde al DNI proporcionado.
     */
    @GetMapping("/dni/{dni}")
    public Alumno obtenerAlumnoPorDni(@PathVariable("dni") String dni) {
        return alumnoService.obtenerAlumnoPorDni(dni);
    }
}
