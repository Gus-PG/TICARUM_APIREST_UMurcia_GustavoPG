package com.gustavoperez.apirest.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * Esta es la clase AsignaturaDTO que representa un objeto de transferencia de datos (DTO) para una Asignatura.
 * Un DTO es un objeto que se utiliza para encapsular datos y enviarlos de un sistema a otro.
 * @author Gustavo Pérez
 * @version 1.0
 */
public class AsignaturaDTO {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Long id;
    private String codigo;
    private String nombre;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String descripcion;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<GrupoDTO> grupos;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<AlumnoDTO> alumnos;

    /**
     * Constructor para crear un nuevo AsignaturaDTO sin un ID.
     *
     * @param codigo El código de la asignatura.
     * @param nombre El nombre de la asignatura.
     */
    public AsignaturaDTO(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    /**
     * Constructor para crear un nuevo AsignaturaDTO con un ID y una lista de GrupoDTO.
     *
     * @param id El ID de la asignatura.
     * @param codigo El código de la asignatura.
     * @param nombre El nombre de la asignatura.
     * @param descripcion La descripción de la asignatura.
     * @param gruposDTO La lista de GrupoDTO de la asignatura.
     */
    public AsignaturaDTO(Long id, String codigo, String nombre,
                         String descripcion, List<GrupoDTO> gruposDTO) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.grupos = gruposDTO;
    }

    /**
     * Constructor vacío necesario para JPA.
     */
    public AsignaturaDTO() {}

    /**
     * Constructor para crear un nuevo AsignaturaDTO con un ID, una lista de GrupoDTO y una lista de AlumnoDTO.
     *
     * @param id El ID de la asignatura.
     * @param codigo El código de la asignatura.
     * @param nombre El nombre de la asignatura.
     * @param descripcion La descripción de la asignatura.
     * @param grupos La lista de GrupoDTO de la asignatura.
     * @param alumnos La lista de AlumnoDTO de la asignatura.
     */
    public AsignaturaDTO(Long id, String codigo, String nombre, String descripcion, List<GrupoDTO> grupos, List<AlumnoDTO> alumnos) {
        this.id = id;
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.grupos = grupos;
        this.alumnos = alumnos;
    }

    /**
     * Obtiene el ID de la asignatura.
     *
     * @return El ID de la asignatura.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID de la asignatura.
     *
     * @param id El nuevo ID de la asignatura.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el código de la asignatura.
     *
     * @return El código de la asignatura.
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Establece el código de la asignatura.
     *
     * @param codigo El nuevo código de la asignatura.
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtiene el nombre de la asignatura.
     *
     * @return El nombre de la asignatura.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la asignatura.
     *
     * @param nombre El nuevo nombre de la asignatura.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción de la asignatura.
     *
     * @return La descripción de la asignatura.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción de la asignatura.
     *
     * @param descripcion La nueva descripción de la asignatura.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene los grupos de la asignatura.
     *
     * @return Los grupos de la asignatura.
     */
    public List<GrupoDTO> getGrupos() {
        return grupos;
    }

    /**
     * Establece los grupos de la asignatura.
     *
     * @param grupos Los nuevos grupos de la asignatura.
     */
    public void setGrupos(List<GrupoDTO> grupos) {
        this.grupos = grupos;
    }

    /**
     * Obtiene los alumnos de la asignatura.
     *
     * @return Los alumnos de la asignatura.
     */
    public List<AlumnoDTO> getAlumnos() {
        return alumnos;
    }

    /**
     * Establece los alumnos de la asignatura.
     *
     * @param alumnos Los nuevos alumnos de la asignatura.
     */
    public void setAlumnos(List<AlumnoDTO> alumnos) {
        this.alumnos = alumnos;
    }

}
