package com.holamundo.ejemplo.holamundo.model;

import java.util.List;

public class Producto {
    //Atributos
    private int id;
    private String nombre;
    private double precio;

    //Constructor
    public Producto(int id, String nombre, double precio){
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    //Setters
    public int getId() {return id;}
    public String getNombre() {return nombre;}
    public double getPrecio() {return precio;}
}
