package com.holamundo.ejemplo.holamundo.controller;

//Importaciones
import com.holamundo.ejemplo.holamundo.model.SaludoResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class holamundocontrolador {
    @GetMapping("/saludo")
    public String saludo() {
        return "Hola Mundo";
    }
    @GetMapping("/saludo2")
    public String saludo2(@RequestParam(defaultValue = "Edward") String nombre) {
        return "Hola " + nombre;
    }
    @GetMapping("/saludojson")
    public SaludoResponse saludojson(@RequestParam(defaultValue = "Edward") String nombre) {
        String mensaje = "Hola " + nombre + ", ¡Bienvenido a la API!";
        return new SaludoResponse(nombre, mensaje);
    }
    // Ruta y metodo para "Hola a todos, mi nombre es Pedro"
    @GetMapping("/pedro")
    public String saludoPedro() {
        return "Hola a todos, mi nombre es Pedro";
    }

    //Operaciones Matemáticas Básicas
    @GetMapping("/suma")
    public String suma(@RequestParam Double a, @RequestParam Double b) {
        double resultado = a + b;
        return "El resultado de la suma es:"+resultado;
    }
    @GetMapping("/resta")
    public String resta(@RequestParam Double a, @RequestParam Double b) {
        double resultado = a - b;
        return "El resultado de la resta es:"+resultado;
    }
    @GetMapping("/division")
    public String division(@RequestParam Double a, @RequestParam Double b) {
        double resultado = a / b;
        return "El resultado de la division es:"+resultado;
    }
    @GetMapping("/multiplicacion")
    public String multiplicacion(@RequestParam Double a, @RequestParam Double b) {
        if (b == 0) {
            return "Error: No se puede dividir por cero.";
        }
        double resultado = a * b;
        return "El resultado de la multiplicacion es:"+resultado;
    }


}
