package com.holamundo.ejemplo.holamundo.model;

public class Cliente {
    private int id;
    private String nombre;
    private String correo;

    // Constructor
    public Cliente(int id, String nombre, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }


}
