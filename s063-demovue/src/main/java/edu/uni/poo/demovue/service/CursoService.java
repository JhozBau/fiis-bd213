package edu.uni.poo.demovue.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.uni.poo.demovue.bean.Curso;
import edu.uni.poo.demovue.dao.CursoDao;

@Service
public class CursoService {
    @Autowired
    CursoDao cursoDao;
    
    public List<Curso> obtenerTodos(){
        return cursoDao.obtenerTodos();
    }

    public Curso buscar(Long id){
        return cursoDao.buscar(id);
    }

    public void agregar(Curso curso){
        cursoDao.agregar(curso);
    }

    public void actualizar(Curso curso){
        cursoDao.actualizar(curso);
    }

    public void eliminar(Curso curso){
        cursoDao.eliminar(curso);
    }

}
