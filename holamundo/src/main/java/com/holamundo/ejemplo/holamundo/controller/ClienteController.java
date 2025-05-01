package com.holamundo.ejemplo.holamundo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.holamundo.ejemplo.holamundo.model.Cliente;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Creamos dos nuevos controladores con sus respectivas rutas
@RestController
@RequestMapping("/api/clientes")

public class ClienteController {
//    @GetMapping
//    public List<Cliente> listarTodosLosClientes() {
//        return listaClientes;
//    }

    private List<Cliente> listaClientes = new ArrayList<>(Arrays.asList(
            new Cliente(1, "Ana González", "ana@gmail.com"),
            new Cliente(2, "Luis Martínez", "luis@gmail.com"),
            new Cliente(3, "Carla Fernández", "carla@gmail.com"),
            new Cliente(4, "Pedro López", "pedro.l@gmail.com"),
            new Cliente(5, "María Pérez", ""),
            new Cliente(6, "Pedro Ramírez", "pedro.r@correo.org"),
            new Cliente(7, "Laura Vidal", "laura.v@mail.com"),
            new Cliente(8, "Carlos Silva", "csilva@email.net"),
            new Cliente(9, "Sofía Morales", "sofia.m@example.com"),
            new Cliente(10, "Diego Rojas", "drojas@domain.com")
    ));


    @GetMapping("/saludo")
    public String saludoCliente() {
        return "Hola desde el controlador de clientes";
    }

    @GetMapping("/listar")
    public List<Cliente> listarClientes() {
        return listaClientes;
    }

    // Buscar cliente por ID
    @GetMapping("/{idCliente}")
    public ResponseEntity<?> buscarCliente(@PathVariable int idCliente) {
        for (Cliente cliente : listaClientes) {
            if (cliente.getId() == idCliente) {
                return ResponseEntity.ok(cliente); // Cliente encontrado
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .header("X-Cliente-No-Encontrado", "true")// Nombre del encabezado personalizado
                .body("Cliente con ID " + idCliente + " no encontrado.")// Cliente no encontrado
                ;
    }

    // Agregar un cliente (POST)
    @PostMapping
    public ResponseEntity<?> agregarCliente(@RequestBody Cliente nuevoCliente) {
        // Validar si el ID ya existe (NUEVO en el CODIGO)
        for (Cliente existente : listaClientes) {
            if (existente.getId() == nuevoCliente.getId()) {
                return ResponseEntity.status(HttpStatus.CONFLICT) // 409 Conflict
                        .body("Ya existe un cliente con el ID " + nuevoCliente.getId());
            }
        }

        listaClientes.add(nuevoCliente); // Se agrega el nuevo cliente a la lista
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Cliente agregado correctamente: " + nuevoCliente.getNombre());
    }

    //NUEVOS MÉTODOS
    // Actualizar un cliente (PUT)
    @PutMapping("/actualizar/{idCliente}")
    public ResponseEntity<?> actualizarCliente(@PathVariable int idCliente, @RequestBody Cliente clienteActualizado) {
        for(int i=0; i < listaClientes.size(); i++) {
            Cliente cliente = listaClientes.get(i);
            if (cliente.getId() == idCliente) {
                // Verificar que el ID coincida (o ignorarlo y usar el ID de la URL)
                if (clienteActualizado.getId() != 0 && clienteActualizado.getId() != idCliente) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body("El ID del cliente (" + clienteActualizado.getId() + ") no coincide con el ID de la URL (" + idCliente + ")");
                }
                // Actualizar el cliente en la lista
                clienteActualizado = new Cliente(idCliente, clienteActualizado.getNombre(), clienteActualizado.getCorreo());
                listaClientes.set(i, clienteActualizado);//Reemplaza el cliente en la lista
                return ResponseEntity.ok("Cliente actualizado correctamente: " + clienteActualizado.getNombre());
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("No se encontró el cliente con ID " + idCliente);
    }

    // Eliminar un cliente (DELETE)
    @DeleteMapping("/eliminar/{idCliente}")
    public ResponseEntity<?> eliminarCliente(@PathVariable int idCliente) {
        boolean encontrado = listaClientes.removeIf(cliente -> cliente.getId() == idCliente);

        if (encontrado) {
            return ResponseEntity.ok("Cliente con ID " + idCliente + " eliminado correctamente.");// 200 OK
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró el Cliente con ID " + idCliente); // 404 Not Found
        }
    }

}
