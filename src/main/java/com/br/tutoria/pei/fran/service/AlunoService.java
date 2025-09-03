package com.br.tutoria.pei.fran.service;

import com.br.tutoria.pei.fran.dtos.AlunoDTO;
import com.br.tutoria.pei.fran.entities.Aluno;
import com.br.tutoria.pei.fran.entities.DadosFamilia;
import com.br.tutoria.pei.fran.entities.Endereco;
import com.br.tutoria.pei.fran.repository.AlunoRepository;
import com.br.tutoria.pei.fran.repository.DadosFamiliaRepository;
import com.br.tutoria.pei.fran.repository.EnderecoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    private final AlunoRepository repository;
    private final DadosFamiliaRepository dadosFamiliarepository;
    private final EnderecoRepository enderecoRepository;

    @Autowired
    public AlunoService(AlunoRepository repository, DadosFamiliaRepository dadosFamiliarepository, EnderecoRepository enderecoRepository) {
        this.repository = repository;
        this.dadosFamiliarepository = dadosFamiliarepository;
        this.enderecoRepository = enderecoRepository;
    }

    @Transactional
    public AlunoDTO insert(AlunoDTO dto) {
        DadosFamilia familia = new DadosFamilia();
        Endereco endereco = new Endereco();

        familia = getDadosFamilia(dto);
        dadosFamiliarepository.save(familia);

        endereco = getEndereco(dto);
        enderecoRepository.save(endereco);

        Aluno aluno = new Aluno();
        dtoToEntity(dto, aluno);
        familia.addAlunos(aluno);
        endereco.getAlunos().add(aluno);
        aluno.setDadoFamilia(familia);
        aluno.setEndereco(endereco);
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

    private static Endereco getEndereco(AlunoDTO dto) {
        Endereco endereco = new Endereco();
        endereco.setRua(dto.getEndereco().getRua());
        endereco.setBairro(dto.getEndereco().getBairro());
        endereco.setCidade(dto.getEndereco().getCidade());
        endereco.setCep(dto.getEndereco().getCep());
        return endereco;
    }
}
