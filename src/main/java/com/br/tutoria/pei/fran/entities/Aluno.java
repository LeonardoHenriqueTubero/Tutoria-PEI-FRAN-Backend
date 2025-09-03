package com.br.tutoria.pei.fran.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_aluno")
public class Aluno {

    @Id
    private Long ra;
    private String nome;
    private String email;
    private LocalDate dataNasc;
    private Integer idade;
    private Integer telefone;
    private String transporte;
    private String projetoVida;
    private String serie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dado_familia_id")
    private DadosFamilia dadoFamilia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @OneToMany(mappedBy = "aluno")
    @JsonIgnore
    private List<Avaliacao> avaliacoes;

    @OneToOne
    private Escolaridade escolaridade;

    @OneToOne
    @JsonIgnore
    private Participacao participacao;


    @OneToMany(mappedBy = "id.aluno")
    Set<Tutoria> tutorias = new HashSet<>();

    @OneToMany(mappedBy = "id.aluno")
    Set<RegistroAtendimento> registroAtendimentos = new HashSet<>();

    public Aluno() {}

    public Aluno(Long ra, String nome, String email, LocalDate dataNasc, Integer idade, Integer telefone, String transporte, String projetoVida, String serie, DadosFamilia dadoFamilia, Endereco endereco) {
        this.ra = ra;
        this.nome = nome;
        this.email = email;
        this.dataNasc = dataNasc;
        this.idade = idade;
        this.telefone = telefone;
        this.transporte = transporte;
        this.projetoVida = projetoVida;
        this.serie = serie;
        this.dadoFamilia = dadoFamilia;
        this.endereco = endereco;
    }

    public Long getRa() {
        return ra;
    }

    public void setRa(Long ra) {
        this.ra = ra;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(LocalDate dataNasc) {
        this.dataNasc = dataNasc;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Integer getTelefone() {
        return telefone;
    }

    public void setTelefone(Integer telefone) {
        this.telefone = telefone;
    }

    public String getTransporte() {
        return transporte;
    }

    public void setTransporte(String transporte) {
        this.transporte = transporte;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getProjetoVida() {
        return projetoVida;
    }

    public void setProjetoVida(String projetoVida) {
        this.projetoVida = projetoVida;
    }

    public DadosFamilia getDadoFamilia() {
        return dadoFamilia;
    }

    public void setDadoFamilia(DadosFamilia dadoFamilia) {
        this.dadoFamilia = dadoFamilia;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Escolaridade getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(Escolaridade escolaridade) {
        this.escolaridade = escolaridade;
    }

    @JsonIgnore
    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void addAvaliacao(Avaliacao avaliacao) {
        this.avaliacoes.add(avaliacao);
    }

    @JsonIgnore
    public Set<Tutoria> getTutorias() {
        return tutorias;
    }

    @JsonIgnore
    public List<Usuario> getUsuarioTutorias() {
        return tutorias.stream().map(Tutoria::getUsuario).toList();
    }

    @JsonIgnore
    public Set<RegistroAtendimento> getRegistroAtendimentos() {
        return registroAtendimentos;
    }

    @JsonIgnore
    public List<Usuario> getUsuarioRegistroAtendimentos() {
        return registroAtendimentos.stream().map(RegistroAtendimento::getUsuario).toList();
    }
}
