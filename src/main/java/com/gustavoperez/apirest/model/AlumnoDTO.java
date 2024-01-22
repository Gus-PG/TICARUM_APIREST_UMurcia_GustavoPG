package com.gustavoperez.apirest.model;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Esta es la clase AlumnoDTO que representa un objeto de transferencia de datos (DTO) para un Alumno.
 * Un DTO es un objeto que se utiliza para encapsular datos y enviarlos de un sistema a otro.
 * @author Gustavo Pérez
 * @version 1.0
 */
public class AlumnoDTO {
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        private Long id;
        private String nombre;
        private String apellidos;
        private String dni;

    /**
     * Constructor vacío necesario para JPA.
     */
    public AlumnoDTO() {}

    /**
     * Constructor para crear un nuevo AlumnoDTO con un ID.
     *
     * @param id El ID del alumno.
     * @param nombre El nombre del alumno.
     * @param apellidos Los apellidos del alumno.
     * @param dni El DNI del alumno.
     */
    public AlumnoDTO(Long id, String nombre, String apellidos, String dni) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
    }

    /**
     * Constructor para crear un nuevo AlumnoDTO sin un ID.
     *
     * @param nombre El nombre del alumno.
     * @param apellidos Los apellidos del alumno.
     * @param dni El DNI del alumno.
     */
    public AlumnoDTO(String nombre, String apellidos, String dni) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
    }

    /**
     * Obtiene el ID del objeto.
     *
     * @return El ID del objeto.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID del objeto.
     *
     * @param id El nuevo ID del objeto.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del objeto.
     *
     * @return El nombre del objeto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del objeto.
     *
     * @param nombre El nuevo nombre del objeto.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene los apellidos del objeto.
     *
     * @return Los apellidos del objeto.
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Establece los apellidos del objeto.
     *
     * @param apellidos Los nuevos apellidos del objeto.
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Obtiene el DNI del objeto.
     *
     * @return El DNI del objeto.
     */
    public String getDni() {
        return dni;
    }

    /**
     * Establece el DNI del objeto.
     *
     * @param dni El nuevo DNI del objeto.
     */
    public void setDni(String dni) {
        this.dni = dni;
    }
}
