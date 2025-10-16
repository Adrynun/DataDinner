package com.datadinner.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.datadinner.model.Mesa;
import com.datadinner.service.MesaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/mesas")
public class MesaController {

    private final MesaService mesaService;

    public MesaController(MesaService mesaService) {
        this.mesaService = mesaService;
    }

    // 1. Listar todas las mesas
    @GetMapping
    public List<Mesa> getAllMesas() {
        return mesaService.getAllMesas();
    }

    // 2. Obtener una mesa por ID
    @GetMapping("/{id}")
    public Mesa getMesaById(@PathVariable int id) {
        return mesaService.getMesaById(id);
    }

    // 3. Crear una nueva mesa
    @PostMapping
    public Mesa createMesa(@Valid @RequestBody Mesa mesa) {
        return mesaService.saveMesa(mesa);
    }

    // 4. Actualizar una mesa existente
    @PutMapping("/{id}")
    public Mesa updateMesa(@PathVariable int id, @Valid @RequestBody Mesa mesa) {
        Mesa existing = mesaService.getMesaById(id);
        existing.setNumero(mesa.getNumero());
        existing.setEstado(mesa.getEstado());
        existing.setCapacidad(mesa.getCapacidad());
        existing.setX(mesa.getX());
        existing.setY(mesa.getY());
        return mesaService.saveMesa(existing);
    }

    // 5. Eliminar una mesa
    @DeleteMapping("/{id}")
    public void deleteMesa(@PathVariable int id) {
        mesaService.deleteMesa(id);
    }
}
