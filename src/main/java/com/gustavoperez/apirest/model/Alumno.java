package com.gustavoperez.apirest.model;


import javax.persistence.*;
import java.util.*;

/**
 * Clase Alumno que representa a un alumno en el sistema.
 * Cada alumno tiene un id, nombre, apellidos, dni y un conjunto de grupos a los que pertenece.
 *
 * @author Gustavo Pérez
 * @version 1.0
 */
@Entity
@Table(name = "alumnos")
public class Alumno {
    /**
     * Id del alumno. Este campo es generado automáticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Nombre del alumno.
     */
    @Column(name="nombre")
    private String nombre;

    /**
     * Apellidos del alumno.
     */
    @Column(name="apellidos")
    private String apellidos;

    /**
     * DNI del alumno.
     */
    @Column(name="dni")
    private String dni;

    /**
     * Conjunto de grupos a los que pertenece el alumno.
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name="alumno_grupos"
            ,joinColumns = @JoinColumn(name="alumno_id")
            ,inverseJoinColumns = @JoinColumn(name="grupo_id")
    )
    private Set<Grupo> grupos = new HashSet<>();


    /**
     * Constructor vacío necesario para JPA.
     */
    public Alumno() {}

    /**
     * Constructor para crear un nuevo alumno.
     *
     * @param dni El DNI del alumno.
     * @param nombre El nombre del alumno.
     * @param apellidos Los apellidos del alumno.
     */
    public Alumno(String dni, String nombre, String apellidos) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
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

    /**
     * Obtiene los grupos del objeto.
     *
     * @return Los grupos del objeto.
     */
    public Set<Grupo> getGrupos(){
        return grupos;
    }

    /**
     * Establece los grupos del objeto.
     *
     * @param grupos Los nuevos grupos del objeto.
     */
    public void setGrupos(Set<Grupo> grupos) {
        this.grupos = grupos;
    }


    /**
     * Compara este objeto con el objeto especificado para verificar si son iguales.
     *
     * @param otro El objeto con el que se va a comparar este objeto.
     * @return Verdadero si este objeto es igual al objeto especificado, falso en caso contrario.
     */
    @Override
    public boolean equals(Object otro) {
        if (this == otro) return true;
        if (otro == null || getClass() != otro.getClass()) return false;
        Alumno alumno = (Alumno) otro;
        return Objects.equals(getDni(), alumno.getDni());
    }

    /**
     * Devuelve un valor hash para este objeto.
     *
     * @return Un valor hash para este objeto.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
