package edu.uni.poo.demovue.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uni.poo.demovue.bean.Alumno;
import edu.uni.poo.demovue.bean.Curso;
import edu.uni.poo.demovue.bean.Matricula;
import edu.uni.poo.demovue.dao.MatriculaDao;

@Service
public class MatriculaService {
    @Autowired
    MatriculaDao matriculaDao;

    @Autowired
    CursoService cursoService;

    @Autowired
    AlumnoService alumnoService;

    public void agregar(Matricula matricula){
        Alumno alu = alumnoService.buscar(matricula.getCodigoAlumno());
        Curso cur = cursoService.buscar(matricula.getCodigoCurso());
        if(validarMatricula(alu, cur) == true){
            matriculaDao.agregar(matricula);
        }        
    }

    private Boolean validarMatricula(Alumno alumno, Curso curso){
        Boolean res = false;
        BigDecimal credito = alumno.getCreditoDisponible();
        BigDecimal costo = curso.getCosto();
        if(credito.compareTo(costo) >= 0){
            res = true;
        }
        return res;
    }
}
