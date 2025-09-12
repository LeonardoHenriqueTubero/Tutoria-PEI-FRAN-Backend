package com.br.tutoria.pei.fran.controllers;

import com.br.tutoria.pei.fran.dtos.UsuarioDTO;
import com.br.tutoria.pei.fran.entities.Usuario;
import com.br.tutoria.pei.fran.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AutentificacaoController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody @Validated Usuario usuario) {
        var cpfPassword = new UsernamePasswordAuthenticationToken(usuario.getNome(), usuario.getCpf());
        var auth = this.authenticationManager.authenticate(cpfPassword);

        return ResponseEntity.ok().build();
    }

}
