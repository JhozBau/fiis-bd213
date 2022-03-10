package edu.uni.poo.demovue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.uni.poo.demovue.bean.Alumno;
import edu.uni.poo.demovue.dao.AlumnoDao;

@Service
public class AlumnoService {
    @Autowired
    AlumnoDao alumnoDao;
    
    public List<Alumno> obtenerTodos(){
        return alumnoDao.obtenerTodos();
    }

    public Alumno buscar(Long id){
        return alumnoDao.buscar(id);
    }

    public void agregar(Alumno alumno){
        alumnoDao.agregar(alumno);
    }

    public void actualizar(Alumno alumno){
        alumnoDao.actualizar(alumno);
    }

    public void eliminar(Alumno alumno){
        alumnoDao.eliminar(alumno);
    }

}