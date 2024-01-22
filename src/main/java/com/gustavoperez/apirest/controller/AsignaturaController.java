package com.gustavoperez.apirest.controller;

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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * Controlador REST para manejar las operaciones de las asignaturas.
 * Este controlador maneja las solicitudes HTTP a la ruta "api/asignaturas".
 *
 * @author Gustavo Pérez
 * @version 1.0
 */
@RestController
@RequestMapping("api/asignaturas")
public class AsignaturaController {

    /**
     * Servicio para manejar las operaciones de asignaturas.
     * Este campo será inyectado automáticamente por Spring.
     */
    @Autowired
    private AsignaturaService asignaturaService;

    /**
     * Servicio para manejar las operaciones de grupos.
     * Este campo será inyectado automáticamente por Spring.
     */
    @Autowired
    private GrupoService grupoService;


    /**
     * Obtiene info de una asignatura determinada, incluyendo sus grupos y alumnos.
     *
     * @param asignaturaId El ID de la asignatura que se quiere obtener.
     * @return ResponseEntity que contiene el objeto Asignatura si se encuentra,
     *         o lanza una excepción si la asignatura no se encontró.
     */
    @GetMapping("/{asignaturaId}")
    public ResponseEntity<AsignaturaDTO> obtenerAsignatura(@PathVariable Long asignaturaId) {
        Asignatura asignatura = asignaturaService.buscarPorId(asignaturaId)
                .orElseThrow(() -> new ResourceNotFoundException("Asignatura id[" + asignaturaId + "] no encontrada."));

        List<GrupoDTO> gruposDTO = asignatura.getGrupos().stream()
                .map(grupo -> {
                    List<AlumnoDTO> alumnosDTO = grupo.getAlumnos().stream()
                            .map(alumno -> new AlumnoDTO(alumno.getNombre(), alumno.getApellidos(), alumno.getDni()))
                            .collect(Collectors.toList());
                    return new GrupoDTO(grupo.getId(), grupo.getCodigo(), grupo.getNombre(), alumnosDTO); // incluye alumnosDTO
                })
                .collect(Collectors.toList());

        AsignaturaDTO asignaturaDTO = new AsignaturaDTO(asignatura.getId(), asignatura.getCodigo(),
                asignatura.getNombre(), asignatura.getDescripcion(), gruposDTO);

        return ResponseEntity.ok(asignaturaDTO);
    }


    /**
     * Crea una nueva asignatura.
     *
     * @param nuevaAsignatura El objeto Asignatura que se va a crear.
     * @return ResponseEntity que contiene el objeto Asignatura que se ha creado.
     */
    @PostMapping
    public ResponseEntity<Asignatura> crearAsignatura(@Valid @RequestBody Asignatura nuevaAsignatura) {
        return ResponseEntity.ok(asignaturaService.guardar(nuevaAsignatura));
    }


    /**
     * Actualiza una asignatura.
     *
     * @param asignaturaId El ID de la asignatura que se quiere actualizar.
     * @param asignaturaActualizada El objeto Asignatura con los datos actualizados.
     * @return ResponseEntity que contiene el objeto Asignatura actualizado si se encuentra,
     *         o lanza una excepción si la asignatura no se encontró.
     */
    @PutMapping("/{asignaturaId}")
    public ResponseEntity<Asignatura> actualizarAsignatura(@PathVariable Long asignaturaId,
                                                           @Valid @RequestBody Asignatura asignaturaActualizada) {
        return asignaturaService.buscarPorId(asignaturaId)
                .map(asignatura -> {
                    asignatura.setNombre(asignaturaActualizada.getNombre());
                    asignatura.setDescripcion((asignaturaActualizada.getDescripcion()));
                    return ResponseEntity.ok(asignaturaService.guardar(asignatura));
                }).orElseThrow(() -> new ResourceNotFoundException("Asignatura id [" + asignaturaId + "] no encontrada."));
    }


    /**
     * Elimina una asignatura y sus grupos asociados, junto con las inscripciones de los alumnos.
     *
     * @param asignaturaId El ID de la asignatura que se quiere eliminar.
     * @return ResponseEntity vacío si la eliminación fue exitosa,
     *         o lanza una excepción si la asignatura no se encontró.
     */
    @Transactional
    @DeleteMapping("/{asignaturaId}")
    public ResponseEntity<?> eliminarAsignatura(@PathVariable Long asignaturaId) {
        return asignaturaService.buscarPorId(asignaturaId)
                .map(asignatura -> {
                    // Obtener los grupos de la asignatura.
                    List<Grupo> grupos = new ArrayList<>(grupoService.buscarPorAsignaturaId(asignaturaId));
                    for (Grupo grupo: grupos) {
                        // Obtener los alumnos del grupo
                        Set<Alumno> alumnos = new HashSet<>(grupo.getAlumnos());
                        for (Alumno alumno: alumnos) {
                            // Eliminar el grupo de la lista de grupos del alumno
                            alumno.getGrupos().remove(grupo);
                            // Relación bidireccional
                            grupo.getAlumnos().remove(alumno);
                        }
                        // Elimino grupo.
                        grupoService.eliminar(grupo);
                    }
                    // Ahora eliminamos la asignatura.
                    asignaturaService.eliminar(asignatura);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Asignatura id[" + asignaturaId + "] no encontrada."));
    }



    /**
     * Crea una nueva asignatura con un ID específico.
     *
     * @param id El ID que se va a asignar a la nueva asignatura.
     * @param newAsignatura El objeto Asignatura que se va a crear.
     * @return ResponseEntity que contiene el objeto Asignatura que se ha creado.
     */
    @PostMapping("/{id}")
    public ResponseEntity<Asignatura> createAsignaturaConId(@PathVariable Long id,
                                                       @Valid @RequestBody Asignatura newAsignatura) {
        Asignatura asignatura = asignaturaService.crearAsignatura(id, newAsignatura);
        return new ResponseEntity<>(asignatura, HttpStatus.CREATED);
    }

}
