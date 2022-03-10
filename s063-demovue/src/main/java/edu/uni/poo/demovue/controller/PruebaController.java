package edu.uni.poo.demovue.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PruebaController {
    @RequestMapping("/saludar")
    public String prueba(){
        return "Hola mundo REST";
    }
}
