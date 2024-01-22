package com.gustavoperez.apirest.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * @Entity
 * Esta es la clase Asignatura que representa una entidad en la base de datos.
 * Cada instancia de Asignatura corresponde a una fila en la tabla "asignaturas".
 * @author Gustavo Pérez
 * @version 1.0
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name="asignaturas")
public class Asignatura {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 5)
    @Size(min=1, max=5)
    private String codigo;


    @Column(length=50)
    @Size(min=1,max=50)
    private String nombre;

    @Lob
    @Column(columnDefinition = "CLOB")
    private String descripcion;

    @OneToMany(mappedBy = "asignatura", fetch = FetchType.LAZY)
    private Set<Grupo> grupos;

    /**
     * Constructor vacío necesario para JPA.
     */
    public Asignatura() {}

    /**
     * Constructor para crear una nueva asignatura.
     *
     * @param codigo El código de la asignatura.
     * @param nombre El nombre de la asignatura.
     * @param descripcion La descripción de la asignatura.
     */
    public Asignatura(String codigo, String nombre, String descripcion) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
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
    public Set<Grupo> getGrupos() { return grupos; }
}
