package com.datadinner.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.datadinner.exception.UsuarioNotFoundException;
import com.datadinner.model.Usuario;
import com.datadinner.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    // Constructor final intacto
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(int id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException(id));
    }

    public Usuario createUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario updateUsuario(int id, Usuario usuario) {
        Usuario existing = getUsuarioById(id); // Usamos getUsuarioById que ya lanza excepción
        existing.setNombreUsuario(usuario.getNombreUsuario());
        existing.setPass(usuario.getPass());
        existing.setRol(usuario.getRol());
        return usuarioRepository.save(existing);
    }

    public void deleteUsuario(int id) {
        Usuario existing = getUsuarioById(id);
        usuarioRepository.delete(existing);
    }
}
