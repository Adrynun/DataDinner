package com.datadinner.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datadinner.model.Usuario;
import com.datadinner.service.UsuarioService;

import com.datadinner.model.LoginRequest; 

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public Usuario login(@RequestBody LoginRequest loginRequest) {
        return usuarioService.authenticate(loginRequest.getNombreUsuario(), loginRequest.getPass());
    }
}