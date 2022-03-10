package edu.uni.poo.demovue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.uni.poo.demovue.bean.Matricula;
import edu.uni.poo.demovue.service.MatriculaService;

@RestController
@RequestMapping("/matriculas")
public class MatriculaController {
    @Autowired
    MatriculaService matriculaService;

    @PostMapping
    public void agregar(@RequestBody Matricula matricula){
        matriculaService.agregar(matricula);
    }
}
