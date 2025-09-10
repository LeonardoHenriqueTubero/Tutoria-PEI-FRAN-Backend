package com.br.tutoria.pei.fran.controllers;
import com.br.tutoria.pei.fran.dtos.UsuarioDTO;
import com.br.tutoria.pei.fran.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuairoController {

    public final UsuarioRepository usuarioRepository;

    public UsuairoController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public List<UsuarioDTO> listaUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(usuario -> new UsuarioDTO(usuario.getCpf(), usuario.getNome()))
                .toList();
    }

    @PostMapping
    public UsuarioDTO adicionarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO usuarioNovo = new UsuarioDTO();{
        return usuarioNovo;
        }
    }
}