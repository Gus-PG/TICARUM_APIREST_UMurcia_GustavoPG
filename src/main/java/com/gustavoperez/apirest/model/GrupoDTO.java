package com.gustavoperez.apirest.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * Esta es la clase GrupoDTO que representa un objeto de transferencia de datos (DTO) para un Grupo.
 * Un DTO es un objeto que se utiliza para encapsular datos y enviarlos de un sistema a otro.
 * @author Gustavo Pérez
 * @version 1.0
 */
public class GrupoDTO {
    private Long id;
    private String codigo;
    private String nombre;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private AsignaturaDTO asignaturaDTO;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<AlumnoDTO> alumnosDTO;

    /**
     * Constructor vacío necesario para JPA.
     */
    public GrupoDTO() {}

    /**
     * Constructor para crear un nuevo GrupoDTO con una AsignaturaDTO.
     *
     * @param id El ID del grupo.
     * @param codigo El código del grupo.
     * @param nombre El nombre del grupo.
     * @param asignaturaDTO La AsignaturaDTO del grupo.
     */
    public GrupoDTO(Long id, String codigo, String nombre,
                    AsignaturaDTO asignaturaDTO) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.asignaturaDTO = asignaturaDTO;
    }

    /**
     * Constructor para crear un nuevo GrupoDTO con una lista de AlumnoDTO.
     *
     * @param id El ID del grupo.
     * @param codigo El código del grupo.
     * @param nombre El nombre del grupo.
     * @param alumnosDTO La lista de AlumnoDTO del grupo.
     */
    public GrupoDTO(Long id, String codigo, String nombre,
                    List<AlumnoDTO> alumnosDTO) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.alumnosDTO = alumnosDTO;
    }


    /**
     * Obtiene el ID del grupo.
     *
     * @return El ID del grupo.
     */
    public Long getId() { return id; }

    /**
     * Establece el ID del grupo.
     *
     * @param id El nuevo ID del grupo.
     */
    public void setId(Long id) { this.id = id; }

    /**
     * Obtiene el código del grupo.
     *
     * @return El código del grupo.
     */
    public String getCodigo() { return codigo; }

    /**
     * Establece el código del grupo.
     *
     * @param codigo El nuevo código del grupo.
     */
    public void setCodigo(String codigo) { this.codigo = codigo; }

    /**
     * Obtiene el nombre del grupo.
     *
     * @return El nombre del grupo.
     */
    public String getNombre() { return nombre; }

    /**
     * Establece el nombre del grupo.
     *
     * @param nombre El nuevo nombre del grupo.
     */
    public void setNombre(String nombre) { this.nombre = nombre; }

    /**
     * Obtiene la asignatura del grupo.
     *
     * @return La asignatura del grupo.
     */
    public AsignaturaDTO getAsignaturaDTO() { return asignaturaDTO; }

    /**
     * Establece la asignatura del grupo.
     *
     * @param asignaturaDTO La nueva asignatura del grupo.
     */
    public void setAsignaturaDTO(AsignaturaDTO asignaturaDTO) { this.asignaturaDTO = asignaturaDTO; }

    /**
     * Obtiene Listado con los alumnos del grupo.
     *
     * @return Listado con Los alumnos del grupo.
     */
    public List<AlumnoDTO> getAlumnosDTO() { return alumnosDTO; }

    /**
     * Establece los alumnos del grupo.
     *
     * @param alumnosDTO Los nuevos alumnos del grupo.
     */
    public void setAlumnosDTO(List<AlumnoDTO> alumnosDTO) { this.alumnosDTO = alumnosDTO; }
}
