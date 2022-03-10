package edu.uni.poo.demovue.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import edu.uni.poo.demovue.bean.Curso;
import edu.uni.poo.demovue.service.CursoService;

@RestController
@RequestMapping("/cursos")
public class CursoController {
    @Autowired
    CursoService cursoService;

    @GetMapping
    public List<Curso> obtenerTodos(){
        return cursoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Curso buscar(@PathVariable Long id){
        return cursoService.buscar(id);
    }

    @PostMapping
    public void agregar(@RequestBody Curso curso){
        cursoService.agregar(curso);
    }

    @PatchMapping
    public void actualizar(@RequestBody Curso curso){
        cursoService.actualizar(curso);
    }

    @DeleteMapping
    public void eliminar(@RequestBody Curso curso){
        cursoService.eliminar(curso);
    }
}
