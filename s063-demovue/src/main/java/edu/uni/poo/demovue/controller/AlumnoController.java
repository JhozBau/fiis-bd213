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
import edu.uni.poo.demovue.bean.Alumno;
import edu.uni.poo.demovue.service.AlumnoService;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {
    @Autowired
    AlumnoService alumnoService;

    @GetMapping
    public List<Alumno> obtenerTodos(){
        return alumnoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public Alumno buscar(@PathVariable Long id){
        return alumnoService.buscar(id);
    }

    @PostMapping
    public void agregar(@RequestBody Alumno alumno){
        alumnoService.agregar(alumno);
    }

    @PatchMapping
    public void actualizar(@RequestBody Alumno alumno){
        alumnoService.actualizar(alumno);
    }

    @DeleteMapping
    public void eliminar(@RequestBody Alumno alumno){
        alumnoService.eliminar(alumno);
    }
}
