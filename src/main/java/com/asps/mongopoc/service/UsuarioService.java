package com.asps.mongopoc.service;

import com.asps.mongopoc.entity.Usuario;
import com.asps.mongopoc.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public Usuario incluirOuAtualizar(Usuario usuario){
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public Usuario consultarPorId(String usuarioId){
        return usuarioRepository.findById(usuarioId).orElseThrow();
    }
}