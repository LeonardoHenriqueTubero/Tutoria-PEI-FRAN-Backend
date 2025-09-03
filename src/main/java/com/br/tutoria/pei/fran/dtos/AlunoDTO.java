package com.br.tutoria.pei.fran.dtos;

import com.br.tutoria.pei.fran.entities.Aluno;

import java.time.LocalDate;

public class AlunoDTO {

    private Long ra;
    private String nome;
    private String email;
    private LocalDate dataNasc;
    private Integer idade;
    private Integer telefone;
    private String transporte;
    private String projetoVida;
    private String serie;

    private DadosFamiliaDTO dadoFamilia;
    private EnderecoDTO endereco;

    public AlunoDTO() {}

    public AlunoDTO(Long ra, String nome, String email, LocalDate dataNasc, Integer idade, Integer telefone, String transporte, String projetoVida, String serie, DadosFamiliaDTO dadosFamilia, EnderecoDTO endereco) {
        this.ra = ra;
        this.nome = nome;
        this.email = email;
        this.dataNasc = dataNasc;
        this.idade = idade;
        this.telefone = telefone;
        this.transporte = transporte;
        this.projetoVida = projetoVida;
        this.serie = serie;
        this.dadoFamilia = dadosFamilia;
        this.endereco = endereco;
    }

    public AlunoDTO(Aluno aluno) {
        ra = aluno.getRa();
        nome = aluno.getNome();
        email = aluno.getEmail();
        dataNasc = aluno.getDataNasc();
        idade = aluno.getIdade();
        telefone = aluno.getTelefone();
        transporte = aluno.getTransporte();
        projetoVida = aluno.getProjetoVida();
        serie = aluno.getSerie();
        dadoFamilia = new DadosFamiliaDTO(aluno.getDadoFamilia());
        endereco = new EnderecoDTO(aluno.getEndereco());
    }

    public Long getRa() {
        return ra;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public Integer getIdade() {
        return idade;
    }

    public Integer getTelefone() {
        return telefone;
    }

    public String getTransporte() {
        return transporte;
    }

    public String getProjetoVida() {
        return projetoVida;
    }

    public String getSerie() {
        return serie;
    }

    public DadosFamiliaDTO getDadoFamilia() {
        return dadoFamilia;
    }

    public EnderecoDTO getEndereco() {
        return endereco;
    }
}
