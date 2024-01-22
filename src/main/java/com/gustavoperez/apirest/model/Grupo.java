package com.gustavoperez.apirest.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @Entity
 * Esta es la clase Grupo que representa una entidad en la base de datos.
 * Cada instancia de Grupo corresponde a una fila en la tabla "grupos".
 * @author Gustavo Pérez
 * @version 1.0
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name="grupos")
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="codigo")
    private String codigo;
    @Column(name="nombre")
    private String nombre;

    @ManyToOne(fetch=FetchType.EAGER, optional = false)
    @JoinColumn(name="asignatura_id", nullable = false)
    @OnDelete(action= OnDeleteAction.CASCADE)
    private  Asignatura asignatura;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
            },
            mappedBy = "grupos")
    @JsonIgnore
    private Set<Alumno> alumnos = new HashSet<>();

    /**
     * Constructor vacío necesario para JPA.
     */
    public Grupo() {}

    /**
     * Constructor para crear un nuevo grupo.
     *
     * @param codigo El código del grupo.
     * @param nombre El nombre del grupo.
     * @param asignatura La asignatura a la que pertenece el grupo.
     */
    public Grupo(String codigo, String nombre, Asignatura asignatura) {
        this.asignatura = asignatura;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    /**
     * Obtiene el ID del grupo.
     *
     * @return El ID del grupo.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID del grupo.
     *
     * @param id El nuevo ID del grupo.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene la asignatura del grupo.
     *
     * @return La asignatura del grupo.
     */
    public Asignatura getAsignatura() {
        return asignatura;
    }

    /**
     * Establece la asignatura del grupo.
     *
     * @param asignatura La nueva asignatura del grupo.
     */
    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    /**
     * Obtiene el código del grupo.
     *
     * @return El código del grupo.
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Establece el código del grupo.
     *
     * @param codigo El nuevo código del grupo.
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * Obtiene el nombre del grupo.
     *
     * @return El nombre del grupo.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del grupo.
     *
     * @param nombre El nuevo nombre del grupo.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene los alumnos del grupo.
     *
     * @return Los alumnos del grupo.
     */
    public Set<Alumno> getAlumnos() { return this.alumnos; }

    /**
     * Establece los alumnos del grupo.
     *
     * @param alumnos Los nuevos alumnos del grupo.
     */
    public void setAlumnos(Set<Alumno> alumnos) {
        this.alumnos = alumnos;
    }
}
