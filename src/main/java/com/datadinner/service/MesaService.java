package com.datadinner.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.datadinner.exception.MesaNotFoundException;
import com.datadinner.model.Mesa;
import com.datadinner.repository.MesaRepository;

@Service
public class MesaService {

    private final MesaRepository mesaRepository;

    public MesaService(MesaRepository mesaRepository) {
        this.mesaRepository = mesaRepository;
    }

    public List<Mesa> getAllMesas() {
        return mesaRepository.findAll();
    }

    public Mesa getMesaById(int id) {
        return mesaRepository.findById(id)
                .orElseThrow(() -> new MesaNotFoundException(id));
    }

    public Mesa saveMesa(Mesa mesa) {
        return mesaRepository.save(mesa);
    }

    public void deleteMesa(int id) {
        Mesa mesa = getMesaById(id);
        mesaRepository.delete(mesa);
    }
}
