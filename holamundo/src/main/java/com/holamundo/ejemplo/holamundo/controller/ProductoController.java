package com.holamundo.ejemplo.holamundo.controller;

//Librerias y paquetes
import com.holamundo.ejemplo.holamundo.model.Producto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/productos")//para hacer el GET de la API
public class ProductoController {
    private List<Producto> listaProductos = new ArrayList<>(Arrays.asList(
            new Producto(1, "Notebook Lenovo", 550000),
            new Producto(2, "Mouse Logitech", 15000),
            new Producto(3, "Monitor Samsung", 120000),
            new Producto(4, "Teclado Redragon", 25000),
            new Producto(5, "Parlantes Genius", 30000),
            new Producto(6, "Auriculares JBL", 20000),
            new Producto(7, "Webcam Logitech", 35000),
            new Producto(8, "Microfono HyperX", 40000),
            new Producto(9, "Silla Gamer", 80000),
            new Producto(10, "Mesa Gamer", 90000),
            new Producto(11, "Laptop HP", 700000)
    ));

    @GetMapping("/saludo")
    public String saludoProducto() {
        return "Hola desde el controlador de producto";
    }

    @GetMapping("/listar")
    public List<Producto> listarProductos() {
        return listaProductos;
    }

    //Buscar por ID
    @GetMapping("/{idProducto}")
    public ResponseEntity<?> buscarProducto(@PathVariable int idProducto) {
        for (Producto producto : listaProductos) {
            if (producto.getId() == idProducto) {
                return ResponseEntity.ok(producto);//Producto Encontrado
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("producto con ID "+ idProducto + " no encontrado en la lista");//Producto NO Encontrado
    }
    //Nuevos MÉTODOS
    //Agregar nuevos productos por POST
    @PostMapping("/agregar")
    public ResponseEntity<?> agregarProducto(@RequestBody Producto nuevoProducto) {
        //Validar si el ID ya existe
        for (Producto producto : listaProductos) {
            if (producto.getId() == nuevoProducto.getId()) {
                return ResponseEntity.status(HttpStatus.CONFLICT)//mensaje de error
                        .body("Ya existe un producto con el ID " + nuevoProducto.getId());
            }
        }
        //Agregar el nuevo producto a la lista
        listaProductos.add(nuevoProducto);
        return ResponseEntity.status(HttpStatus.CREATED)//mensaje de éxito
                .body("Producto agregado con éxito: " + nuevoProducto);
    }

    //Modificar productos por PUT
    @PutMapping("/modificar/{idProducto}")
    public ResponseEntity<?> actualizarProducto(@PathVariable int idProducto, @RequestBody Producto productoActualizado) {
        for (int i = 0; i < listaProductos.size(); i++) {
            Producto producto = listaProductos.get(i);
            if (producto.getId() == idProducto) {
                //verificar que el ID coincida (o ignorarlo y usar el ID de la URL)
                if (productoActualizado.getId() != 0 && productoActualizado.getId() != idProducto) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body("El ID del producto ("+ productoActualizado.getId() + ") no coincide con el ID de la URL (" + idProducto + ")");
                }
                //Actualizar el producto en la lista
                productoActualizado = new Producto(idProducto, productoActualizado.getNombre(), productoActualizado.getPrecio());//Crea nuevo objeto con el ID correcto
                listaProductos.set(i, productoActualizado);//Reemplaza el producto en la lista

                return ResponseEntity.ok("Producto actualizado con éxito: " + productoActualizado);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("No se encontró el producto con ID " + idProducto);
    }

    //Eliminar productos por DELETE
    @DeleteMapping("/eliminar/{idProducto}")
    public ResponseEntity<?> eliminarProducto(@PathVariable int idProducto) {
        boolean encontrado = listaProductos.removeIf(producto -> producto.getId() == idProducto);

        if (encontrado) {
            return ResponseEntity.ok("Producto con ID"+ idProducto + " eliminado con éxito");// 200 OK
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró el producto con ID " + idProducto); //404 Not Found
        }
    }

}
