package com.br.tutoria.pei.fran.controllers;

import com.br.tutoria.pei.fran.entities.Aluno;
import com.br.tutoria.pei.fran.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/aluno")
public class AlunoController {

    private final AlunoService service;

    @Autowired
    public AlunoController(AlunoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Aluno> cadastrar(@RequestBody Aluno aluno) {
        aluno = service.insert(aluno, aluno.getDadoFamilia(), aluno.getEndereco());
        return ResponseEntity.ok(aluno);
    }
}
