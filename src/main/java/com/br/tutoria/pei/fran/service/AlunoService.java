package com.br.tutoria.pei.fran.service;

import com.br.tutoria.pei.fran.entities.Aluno;
import com.br.tutoria.pei.fran.entities.DadosFamilia;
import com.br.tutoria.pei.fran.entities.Endereco;
import com.br.tutoria.pei.fran.repository.AlunoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    private final AlunoRepository repository;

    @Autowired
    public AlunoService(AlunoRepository repository) {
        this.repository = repository;

    }

    @Transactional
    public Aluno insert(Aluno aluno, DadosFamilia familia, Endereco endereco) {
        aluno.setDadoFamilia(familia);
        aluno.setEndereco(endereco);
        return repository.save(aluno);
    }
}
