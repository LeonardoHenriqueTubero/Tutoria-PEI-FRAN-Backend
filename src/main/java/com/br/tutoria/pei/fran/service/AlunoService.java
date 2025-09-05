package com.br.tutoria.pei.fran.service;

import com.br.tutoria.pei.fran.dtos.AlunoDTO;
import com.br.tutoria.pei.fran.entities.Aluno;
import com.br.tutoria.pei.fran.entities.DadosFamilia;
import com.br.tutoria.pei.fran.entities.Escolaridade;
import com.br.tutoria.pei.fran.repository.AlunoRepository;
import com.br.tutoria.pei.fran.repository.DadosFamiliaRepository;
import com.br.tutoria.pei.fran.repository.EscolaridadeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    private final AlunoRepository repository;
    private final DadosFamiliaRepository dadosFamiliarepository;
    private final EscolaridadeRepository escolaridadeRepository;

    @Autowired
    public AlunoService(AlunoRepository repository, DadosFamiliaRepository dadosFamiliarepository, EscolaridadeRepository escolaridadeRepository) {
        this.repository = repository;
        this.dadosFamiliarepository = dadosFamiliarepository;
        this.escolaridadeRepository = escolaridadeRepository;
    }

    @Transactional
    public AlunoDTO insert(AlunoDTO dto) {
        DadosFamilia familia = getDadosFamilia(dto);
        Escolaridade escolaridade = getEscolaridade(dto);

        Aluno aluno = new Aluno();
        dtoToEntity(dto, aluno);
        familia.addAlunos(aluno);
        escolaridade.setAluno(aluno);
        aluno.setDadoFamilia(familia);
        aluno.setEscolaridade(escolaridade);
        familia = dadosFamiliarepository.save(familia);
        escolaridade = escolaridadeRepository.save(escolaridade);
        aluno = repository.save(aluno);
        return new AlunoDTO(aluno);
    }

    private void dtoToEntity(AlunoDTO dto, Aluno entity) {
        entity.setRa(dto.getRa());
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
        entity.setDataNasc(dto.getDataNasc());
        entity.setIdade(dto.getIdade());
        entity.setTelefone(dto.getTelefone());
        entity.setTransporte(dto.getTransporte());
        entity.setProjetoVida(dto.getProjetoVida());
        entity.setSerie(dto.getSerie());
        entity.setEndereco(dto.getEndereco());
        entity.setImgUrl(dto.getImgUrl());
    }

    private static DadosFamilia getDadosFamilia(AlunoDTO dto) {
        DadosFamilia familia = new DadosFamilia();
        familia.setPai(dto.getDadoFamilia().getPai());
        familia.setMae(dto.getDadoFamilia().getMae());
        familia.setResponsavel(dto.getDadoFamilia().getResponsavel());
        familia.setEstruturaFamiliar(dto.getDadoFamilia().getEstruturaFamiliar());
        familia.setNumPai(dto.getDadoFamilia().getNumPai());
        familia.setNumMae(dto.getDadoFamilia().getNumMae());
        familia.setNumResponsavel(dto.getDadoFamilia().getNumResponsavel());
        return familia;
    }

    private static Escolaridade getEscolaridade(AlunoDTO dto) {
        Escolaridade escolaridade = new Escolaridade();
        escolaridade.setContatoFora(dto.getEscolaridade().getContatoFora());
        escolaridade.setDifAprendizagem(dto.getEscolaridade().getDifAprendizagem());
        escolaridade.setApoioPedagogico(dto.getEscolaridade().getApoioPedagogico());
        escolaridade.setAtividadeExtra(dto.getEscolaridade().getAtividadeExtra());
        escolaridade.setDifLocomotiva(dto.getEscolaridade().getDifLocomotiva());
        escolaridade.setDifVisao(dto.getEscolaridade().getDifVisao());
        escolaridade.setDifAtencao(dto.getEscolaridade().getDifAtencao());
        escolaridade.setDifFala(dto.getEscolaridade().getDifFala());
        escolaridade.setDifEscrita(dto.getEscolaridade().getDifEscrita());
        escolaridade.setAdaptacaoGrupo(dto.getEscolaridade().getAdaptacaoGrupo());
        escolaridade.setReprovado(dto.getEscolaridade().getReprovado());
        escolaridade.setSerieAnoReprovado(dto.getEscolaridade().getSerieAnoReprovado());
        escolaridade.getDisciplinasFacilidade().addAll(dto.getEscolaridade().getDisciplinasFacilidade());
        escolaridade.getDisciplinasDificuldade().addAll(dto.getEscolaridade().getDisciplinasDificuldade());
        return escolaridade;
    }
}
