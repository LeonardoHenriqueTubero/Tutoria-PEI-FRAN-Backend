package com.br.tutoria.pei.fran.service;

import com.br.tutoria.pei.fran.dtos.AlunoDTO;
import com.br.tutoria.pei.fran.dtos.AlunoMinDTO;
import com.br.tutoria.pei.fran.dtos.AvaliacaoDTO;
import com.br.tutoria.pei.fran.dtos.ParticipacaoDTO;
import com.br.tutoria.pei.fran.entities.*;
import com.br.tutoria.pei.fran.repository.AlunoRepository;
import com.br.tutoria.pei.fran.repository.DadosFamiliaRepository;
import com.br.tutoria.pei.fran.repository.EscolaridadeRepository;
import com.br.tutoria.pei.fran.service.exceptions.DatabaseException;
import com.br.tutoria.pei.fran.service.exceptions.EntityAlreadyExistingException;
import com.br.tutoria.pei.fran.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        if (repository.existsById(dto.getRa())) {
            throw new EntityAlreadyExistingException("Entidade já criada!");
        }
        DadosFamilia familia = dadosFamiliarepository.findPaiOrMaeOrResponsavel(dto.getDadoFamilia().getPai(),
        dto.getDadoFamilia().getMae(), dto.getDadoFamilia().getResponsavel()).orElseGet(DadosFamilia::new);
        Escolaridade escolaridade = new Escolaridade();

        setDadosFamilia(familia, dto);
        setEscolaridade(escolaridade, dto);

        Aluno aluno = new Aluno();
        dtoToEntity(dto, aluno);
        familia.addAlunos(aluno);
        escolaridade.setAluno(aluno);
        aluno.setDadoFamilia(familia);
        aluno.setEscolaridade(escolaridade);
        aluno.setParticipacao(new Participacao());

        aluno = repository.save(aluno);
        return new AlunoDTO(aluno);
    }

    @Transactional
    public AlunoDTO update(Long ra, AlunoDTO dto) {
        Aluno aluno = repository.getReferenceById(ra);
        DadosFamilia familia = dadosFamiliarepository.getReferenceById(aluno.getDadoFamilia().getId());
        Escolaridade escolaridade = escolaridadeRepository.getReferenceById(aluno.getEscolaridade().getId());

        dtoToEntity(dto, aluno);
        setDadosFamilia(familia, dto);
        setEscolaridade(escolaridade, dto);

        aluno = repository.save(aluno);
        aluno.getDadoFamilia().getAlunos().forEach(System.out::println);
        return new AlunoDTO(aluno);
    }

    @Transactional(readOnly = true)
    public List<AlunoMinDTO> getAllNames() {
        return repository.getAllNamesAndImage();
    }

    @Transactional(readOnly = true)
    public AlunoDTO getAlunosByRa(Long ra) {
        Aluno aluno = repository.findById(ra).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));
        return new AlunoDTO(aluno);
    }

    //Não estou certo desse tanto de idas no banco de dados
    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long ra) {
        if (!repository.existsById(ra)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            Aluno aluno = repository.findById(ra).get();

            DadosFamilia familia = dadosFamiliarepository.findFamiliaByIdWithAluno(aluno.getDadoFamilia().getId());

            familia.getAlunos().remove(aluno);
            aluno.setDadoFamilia(null);
            if (familia.getAlunos().isEmpty()) {
                dadosFamiliarepository.delete(familia);
            }

            repository.deleteById(ra);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Erro de integridade violada");
        }
    }

    @Transactional
    public ParticipacaoDTO addParticipacao(Long ra, ParticipacaoDTO dto) {
        Aluno aluno = repository.getReferenceById(ra);
        Participacao participacao = new Participacao();
        dtoToParticipacao(participacao, dto);

        aluno.setParticipacao(participacao);
        aluno = repository.save(aluno);

        return new ParticipacaoDTO(aluno.getParticipacao());
    }

    @Transactional(readOnly = true)
    public ParticipacaoDTO getParticipacao(Long ra) {
        Aluno aluno = repository.findById(ra).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));

        return new ParticipacaoDTO(aluno.getParticipacao());
    }

    @Transactional
    public AvaliacaoDTO addAvaliacao(Long ra, AvaliacaoDTO avaliacaoDTO) {
        Aluno aluno = repository.getReferenceById(ra);
        Avaliacao avaliacao = new Avaliacao();

        dtoToAvaliacao(avaliacao, avaliacaoDTO);

        aluno.addAvaliacao(avaliacao);
        avaliacao.setAluno(aluno);

        aluno = repository.save(aluno);

        return new AvaliacaoDTO(avaliacao);
    }

    @Transactional(readOnly = true)
    public List<AvaliacaoDTO> getAllAvaliacoes(Long ra) {
        Aluno aluno = repository.findById(ra).orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado"));

        return aluno.getAvaliacoes().stream().map(AvaliacaoDTO::new).toList();
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

    private static void setDadosFamilia(DadosFamilia familia, AlunoDTO dto) {
        familia.setPai(dto.getDadoFamilia().getPai());
        familia.setMae(dto.getDadoFamilia().getMae());
        familia.setResponsavel(dto.getDadoFamilia().getResponsavel());
        familia.setEstruturaFamiliar(dto.getDadoFamilia().getEstruturaFamiliar());
        familia.setNumPai(dto.getDadoFamilia().getNumPai());
        familia.setNumMae(dto.getDadoFamilia().getNumMae());
        familia.setNumResponsavel(dto.getDadoFamilia().getNumResponsavel());
    }

    private static void setEscolaridade(Escolaridade escolaridade, AlunoDTO dto) {
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
    }

    private static void dtoToParticipacao(Participacao participacao, ParticipacaoDTO dto) {
        participacao.setAlunoGremista1(dto.getAlunoGremista1());
        participacao.setAlunoGremista2(dto.getAlunoGremista2());
        participacao.setEletiva1(dto.getEletiva1());
        participacao.setEletiva2(dto.getEletiva2());
        participacao.setClubeJuvenil1(dto.getClubeJuvenil1());
        participacao.setClubeJuvenil2(dto.getClubeJuvenil2());
        participacao.setLiderTurma1(dto.getLiderTurma1());
        participacao.setLiderTurma2(dto.getLiderTurma2());
        participacao.setJovemAcolhedor1(dto.getJovemAcolhedor1());
        participacao.setJovemAcolhedor2(dto.getJovemAcolhedor2());
    }

    private static void dtoToAvaliacao(Avaliacao avaliacao, AvaliacaoDTO dto) {
        avaliacao.setTipo(dto.getTipo());
        avaliacao.setMateria(dto.getMateria());
        avaliacao.setNumQuestoes(dto.getNumQuestoes());
        avaliacao.setNumAcertos(dto.getNumAcertos());
    }
}
