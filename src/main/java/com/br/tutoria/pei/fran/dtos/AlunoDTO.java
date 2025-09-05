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
    private String endereco;
    private String imgUrl;

    private DadosFamiliaDTO dadoFamilia;
    private EscolaridadeDTO escolaridade;

    public AlunoDTO() {}

    public AlunoDTO(Long ra, String nome, String email, LocalDate dataNasc, Integer idade, Integer telefone, String transporte, String projetoVida, String serie, DadosFamiliaDTO dadosFamilia, String endereco, EscolaridadeDTO escolaridade, String imgUrl) {
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
        this.escolaridade = escolaridade;
        this.imgUrl = imgUrl;
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
        escolaridade = new EscolaridadeDTO(aluno.getEscolaridade());
        endereco = aluno.getEndereco();
        imgUrl = aluno.getImgUrl();
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

    public String getEndereco() {
        return endereco;
    }

    public EscolaridadeDTO getEscolaridade() {
        return escolaridade;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
