package com.gustavoperez.apirest.controller;

import com.gustavoperez.apirest.service.AlumnoService;
import com.gustavoperez.apirest.service.AsignaturaService;
import com.gustavoperez.apirest.service.GrupoService;

import com.gustavoperez.apirest.model.*;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador REST para manejar las operaciones de los grupos.
 * Este controlador maneja las solicitudes HTTP a la ruta "api/grupos".
 *
 * @author Gustavo Pérez
 * @version 1.0
 */
@RestController
@RequestMapping("api/grupos")
public class GrupoController {

    /**
     * Servicio para manejar las operaciones de grupos.
     * Este campo será inyectado automáticamente por Spring.
     */
    @Autowired
    private GrupoService grupoService;

    /**
     * Servicio para manejar las operaciones de alumnos.
     * Este campo será inyectado automáticamente por Spring.
     */
    @Autowired
    private AlumnoService alumnoService;

    /**
     * Servicio para manejar las operaciones de asignaturas.
     * Este campo será inyectado automáticamente por Spring.
     */
    @Autowired
    private AsignaturaService asignaturaService;


    /**
     * Devuelve info de un grupo específico de una asignatura, junto con los alumnos del mismo.
     *
     * @param asignaturaId El ID de la asignatura a la que pertenece el grupo.
     * @param grupoId El ID del grupo que se quiere obtener.
     * @return ResponseEntity que contiene el objeto Grupo si se encuentra,
     *         o lanza una excepción si el grupo no se encontró.
     */
    @GetMapping("/{asignaturaId}/{grupoId}")
    public ResponseEntity<GrupoDTO> obtenerGrupo (@PathVariable Long asignaturaId, @PathVariable Long grupoId) {
        return grupoService.obtenerPorId(grupoId)
                .map(grupo -> {
                    List<AlumnoDTO> alumnosDTO = grupo.getAlumnos().stream()
                            .map(alumno -> new AlumnoDTO(alumno.getNombre(),
                                    alumno.getApellidos(), alumno.getDni()))
                            .collect(Collectors.toList());

                    GrupoDTO grupoDTO = new GrupoDTO(grupo.getId(), grupo.getCodigo(), grupo.getNombre(),
                            alumnosDTO);
                    return ResponseEntity.ok(grupoDTO);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Grupo id[" + grupoId + "] no encontrado."));
    }


    /**
     * Crea un nuevo grupo para una asignatura indicada.
     *
     * @param asignaturaId El ID de la asignatura a la que se va a añadir el grupo.
     * @param grupo El objeto Grupo que se va a crear.
     * @return ResponseEntity que devuelve el obj. Grupo creado si encuentra la asignatura indicada,
     *         o lanza una excepción si no la encuentra.
     */
    @PostMapping("/{asignaturaId}")
    public ResponseEntity<?> crearGrupo(@PathVariable Long asignaturaId, @Valid @RequestBody Grupo grupo) {
        return asignaturaService.buscarPorId(asignaturaId)
                .map(asignatura -> {
                    if (asignatura.getGrupos().size() >= 5) {
                        return new ResponseEntity<>("La asignatura ya tiene 5 grupos.",
                                HttpStatus.CONFLICT);
                    }
                    grupo.setAsignatura(asignatura);
                    Grupo grupoGuardado = grupoService.guardar(grupo);
                    return ResponseEntity.ok(grupoGuardado);
                }).orElseThrow(() -> new ResourceNotFoundException("Asignatura con id [" + asignaturaId + "] no encontrada."));
    }


    /**
     * Modifica un grupo concreto de una asignatura dada.
     *
     * @param asignaturaId El ID de la asignatura a la que pertenece el grupo.
     * @param grupoId El ID del grupo que se quiere modificar.
     * @param grupoActualizado El objeto Grupo con los datos actualizados.
     * @return ResponseEntity encapsula el Grupo modificado si se ha encontrado,
     *         o lanza una excepción si no lo encuentra.
     */
    @PutMapping("/{asignaturaId}/{grupoId}")
    public ResponseEntity<Grupo> modificarGrupo(@PathVariable Long asignaturaId,
                                                @PathVariable Long grupoId,
                                                @Valid @RequestBody Grupo grupoActualizado) {
        return grupoService.obtenerPorId(grupoId)
                .map(grupo -> {
                    grupo.setNombre(grupoActualizado.getNombre());
                    return ResponseEntity.ok(grupoService.guardar(grupo));
                }).orElseThrow(() -> new ResourceNotFoundException("Grupo id[" + grupoId + "] no encontrado."));
    }


    /**
     * Añade un alumno a un grupo en concreto para una asignatura indicada.
     *
     * @param asignaturaId El ID de la asignatura a la que pertenece el grupo.
     * @param grupoId El ID del grupo al que se va a añadir el alumno.
     * @return ResponseEntity con el bjeto Alumno añadido si encuentra el grupo,
     *         o lanza una excepción si no lo encuentra.
     */
    @PostMapping("/{asignaturaId}/{grupoId}")
    public ResponseEntity<?> anadirAlumno(@PathVariable Long asignaturaId
            , @PathVariable Long grupoId, @RequestBody AlumnoDTO alumnoDTO) {
        Alumno alumnoExistente = alumnoService.buscarPorDni(alumnoDTO.getDni());
        if (alumnoExistente == null) {
            return new ResponseEntity<>("El alumno con DNI " +
                    alumnoDTO.getDni() + " no existe.", HttpStatus.BAD_REQUEST);
        } else {
            return grupoService.obtenerPorId(grupoId)
                    .map(grupo -> {
                        boolean existeAlumno = grupo.getAlumnos().stream()
                                .anyMatch(alumno -> alumno.getDni().equals(alumnoExistente.getDni()));

                        if (existeAlumno) {
                            return new ResponseEntity<>("No realizado. El alumno con DNI " + alumnoDTO.getDni() +
                                    " ya está inscrito otr Grupo [" + grupo.getNombre() +
                                    "] de esta misma Asignatura.", HttpStatus.CONFLICT);
                        }
                        // Añadimos (comunicación bidireccional) registros.
                        grupo.getAlumnos().add(alumnoExistente);
                        alumnoExistente.getGrupos().add(grupo);
                        // Generamos respuesta.
                        Alumno alumnoGuardado = alumnoService.guardar(alumnoExistente);
                        AlumnoDTO alumnoGuardadoDTO = new AlumnoDTO(alumnoGuardado.getId(),
                                alumnoGuardado.getNombre(), alumnoGuardado.getApellidos(),
                                alumnoGuardado.getDni());
                        return ResponseEntity.ok(alumnoGuardadoDTO);
                    }).orElseThrow(() -> new ResourceNotFoundException("Grupo id [" + grupoId + "] no encontrado."));
        }
    }


    /**
     * Elimina un alumno del grupo indicado para una asignatura dada.
     *
     * @param asignaturaId ID de la asignatura a la que pertenece el grupo.
     * @param grupoId ID del grupo en el que se eliminará al alumno.
     * @param dni DNI del alumno a eliminar.
     * @return ResponseEntity vacío si eliminación fue exitosa,
     *         o lanza una excepción si grupo / alumno no se encontraron.
     */
    @Transactional
    @DeleteMapping("/{asignaturaId}/{grupoId}/{dni}")
    public ResponseEntity<?> eliminarAlumno(@PathVariable Long asignaturaId,
                                            @PathVariable Long grupoId,
                                            @PathVariable String dni) {
        return grupoService.obtenerPorId(grupoId)
                .map(grupo -> {
                    Alumno alumno = alumnoService.buscarPorDni(dni);
                    if(alumno != null) {
                        grupo.getAlumnos().remove(alumno);
                        alumno.getGrupos().remove(grupo);
                        grupoService.guardar(grupo);
                        return ResponseEntity.ok().build();
                    } else {
                        throw new ResourceNotFoundException("Alumno dni [" + dni + "] no encontrado.");
                    }
                }).orElseThrow(() -> new ResourceNotFoundException("Grupo id[" + grupoId + "] no econtrado."));
    }
}
