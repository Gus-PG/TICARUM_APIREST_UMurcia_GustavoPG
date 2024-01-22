package com.gustavoperez.apirest.config;

import com.gustavoperez.apirest.service.AlumnoService;
import com.gustavoperez.apirest.service.AsignaturaService;
import com.gustavoperez.apirest.service.GrupoService;
import com.gustavoperez.apirest.model.Alumno;
import com.gustavoperez.apirest.model.Asignatura;
import com.gustavoperez.apirest.model.Grupo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DatabaseInitializer {

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
     * Servicio para manejar las operaciones de asignaturas.
     * Este campo será inyectado automáticamente por Spring.
     */
    @Autowired
    private AsignaturaService asignaturaService;

    /**
     * Método de inicialización que se ejecuta después de la inyección de dependencias.
     * Este método se utiliza para poblar la tabla de alumnos con datos iniciales.
     *
     * @PostConstruct asegura que este método se llamará una vez que se haya completado la inyección de dependencias por parte del contenedor de Spring.
     */
    @PostConstruct
    public void init() {

        //Poblamos tabla ALUMNOS.
        Alumno alumno1 = new Alumno("11111111H", "Alumno_1", "Apellidos_1");
        alumnoService.crearAlumno(alumno1);

        Alumno alumno2 = new Alumno("22222222J", "Alumno_2", "Apellidos_2");
        alumnoService.crearAlumno(alumno2);

        Alumno alumno3 = new Alumno("33333333P", "Alumno_3", "Apellidos_3");
        alumnoService.crearAlumno(alumno3);

        Alumno alumno4 = new Alumno("44444444A", "Alumno_4", "Apellidos_4");
        alumnoService.crearAlumno(alumno4);

        Alumno alumno5 = new Alumno("55555555K", "Alumno_5", "Apellidos_5");
        alumnoService.crearAlumno(alumno5);

        Alumno alumno6 = new Alumno("66666666Q", "Alumno_6", "Apellidos_6");
        alumnoService.crearAlumno(alumno6);

        Alumno alumno7 = new Alumno("77777777B", "Alumno_7", "Apellidos_7");
        alumnoService.crearAlumno(alumno7);

        Alumno alumno8 = new Alumno("88888888Y", "Alumno_8", "Apellidos_8");
        alumnoService.crearAlumno(alumno8);

        Alumno alumno9 = new Alumno("99999999R", "Alumno_9", "Apellidos_9");
        alumnoService.crearAlumno(alumno9);

        Alumno alumno10 = new Alumno("10101010P", "Alumno_10", "Apellidos_10");
        alumnoService.crearAlumno(alumno10);


        //Poblamos tabla ASIGNATURAS.
        Asignatura asignatura1 = new Asignatura("3875", "CALIDAD DEL SOFTWARE", "En esta asignatura se discutirán los conceptos fundamentales en Calidad y se dará una visión general acerca de los modelos y estándares más aceptados en la industria.");
        asignaturaService.crearAsignatura(1L, asignatura1);

        Asignatura asignatura2 = new Asignatura("3872", "DESARROLLO DE APLICACIONES WEB", "El desarrollo de aplicaciones informáticas evoluciona continuamente para adaptarse a las nuevas tecnologías de la información y las comunicaciones (TIC).");
        asignaturaService.crearAsignatura(2L, asignatura2);

        Asignatura asignatura3 = new Asignatura("3876", "ARQUITECTURA DEL SOFTWARE", "La asignatura introduce el concepto de Arquitectura de Software a través del desarrollo orientado a servicios, en particular, en base a microservicios en las plataformas Java y .NET.");
        asignaturaService.crearAsignatura(3L, asignatura3);

        Asignatura asignatura4 = new Asignatura("3888", "GESTIÓN DE LA SEGURIDAD EN SISTEMAS DE INFORMACIÓN", "la seguridad informática o la ciberseguridad se enfocan en la protección de los sistemas informáticos.");
        asignaturaService.crearAsignatura(4L, asignatura4);

        Asignatura asignatura5 = new Asignatura("1923", "PROGRAMACIÓN PARA LA IA", "La asignatura pretende introducir al alumno en un estilo de programación asociado de forma tradicional a la Inteligencia Artificial: el paradigma funcional.");
        asignaturaService.crearAsignatura(5L, asignatura5);


        //Poblamos tabla GRUPOS
        Grupo grupo1 = new Grupo("G1", "Prácticas CALIDAD SOFTWARE - Lab. A01", asignatura1);
        grupoService.crearGrupo(grupo1);

        Grupo grupo2 = new Grupo("G2", "Prácticas DAW Lab. A02", asignatura2);
        grupoService.crearGrupo(grupo2);

        Grupo grupo3 = new Grupo("G3", "Prácticas DAW Lab. A03", asignatura2);
        grupoService.crearGrupo(grupo3);

        Grupo grupo4 = new Grupo("G4", "Prácticas ARQUITECTURA SOFTWARE. Lab. A04", asignatura3);
        grupoService.crearGrupo(grupo4);

        Grupo grupo5 = new Grupo("G5", "Prácticas ARQUITECTURA SOFTWARE Lab. A05", asignatura3);
        grupoService.crearGrupo(grupo5);

        Grupo grupo6 = new Grupo("G6", "Prácticas PROGRAMACIÓN IA Lab. A03", asignatura5);
        grupoService.crearGrupo(grupo6);

        Grupo grupo7 = new Grupo("G7", "Prácticas PROGRAMACIÓN IA Lab. A02", asignatura5);
        grupoService.crearGrupo(grupo7);

        Grupo grupo8 = new Grupo("G8", "Prácticas PROGRAMACIÓN IA Lab. A01", asignatura5);
        grupoService.crearGrupo(grupo8);

        Grupo grupo9 = new Grupo("G9", "Prácticas PROGRAMACIÓN IA Lab. A04", asignatura5);
        grupoService.crearGrupo(grupo9);

        Grupo grupo10 = new Grupo("G10", "Prácticas PROGRAMACIÓN IA Lab. A05", asignatura5);
        grupoService.crearGrupo(grupo10);


        //Poblamos Tabla ALUMNO_GRUPOS
        agregarGrupoAAlumno(1L, 10L);
        agregarGrupoAAlumno(1L, 5L);
        agregarGrupoAAlumno(1L, 3L);
        agregarGrupoAAlumno(1L, 1L);
        agregarGrupoAAlumno(2L, 10L);
        agregarGrupoAAlumno(2L, 5L);
        agregarGrupoAAlumno(2L, 3L);
        agregarGrupoAAlumno(3L, 10L);
        agregarGrupoAAlumno(4L, 5L);
        agregarGrupoAAlumno(5L, 3L);
        agregarGrupoAAlumno(5L, 10L);
        agregarGrupoAAlumno(6L, 2L);
        agregarGrupoAAlumno(6L, 7L);
        agregarGrupoAAlumno(7L, 4L);
        agregarGrupoAAlumno(7L, 8L);
        agregarGrupoAAlumno(8L, 5L);
        agregarGrupoAAlumno(8L, 8L);
        agregarGrupoAAlumno(9L, 3L);
        agregarGrupoAAlumno(9L, 1L);
        agregarGrupoAAlumno(9L, 9L);
    }

    /**
     * Método para agregar un grupo a un alumno.
     *
     * @param alumnoId El ID del alumno al que se va a agregar el grupo.
     * @param grupoId El ID del grupo que se va a agregar al alumno.
     */
    public void agregarGrupoAAlumno(Long alumnoId, Long grupoId) {
        Alumno alumno = alumnoService.buscarPorId(alumnoId);
        Grupo grupo = grupoService.buscarPorId(grupoId);
        alumno.getGrupos().add(grupo);
        alumnoService.guardar(alumno);
    }
}
