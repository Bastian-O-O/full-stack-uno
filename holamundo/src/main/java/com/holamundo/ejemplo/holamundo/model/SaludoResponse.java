package com.holamundo.ejemplo.holamundo.model;

public class SaludoResponse {
    private String nombre;
    private String mensaje;

    //Contructor
    public SaludoResponse(String nombre, String mensaje) {
        this.nombre = nombre;
        this.mensaje = mensaje;
    }

    //Getters
    public String getNombre() {
        return nombre;
    }
    public String getMensaje() {
        return mensaje;
    }
}
