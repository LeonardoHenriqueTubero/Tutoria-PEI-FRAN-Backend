package com.br.tutoria.pei.fran.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    private Integer CPF;
    private String nome;
    private String papel;

    @OneToMany(mappedBy = "id.usuario")
    Set<Tutoria> tutorias = new HashSet<>();

    @OneToMany(mappedBy = "id.usuario")
    Set<RegistroAtendimento> registroAtendimentos = new HashSet<>();

    public Usuario(Integer CPF, String nome, String papel) {
        this.CPF = CPF;
        this.nome = nome;
        this.papel = papel;
    }

    public Integer getCPF() {
        return CPF;
    }

    public void setCPF(Integer CPF) {
        this.CPF = CPF;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }

    public Set<Tutoria> getTutorias() {
        return tutorias;
    }

    public List<Aluno> getAlunosTutorias() {
        return tutorias.stream().map(Tutoria::getAluno).toList();
    }

    public Set<RegistroAtendimento> getRegistroAtendimentos() {
        return registroAtendimentos;
    }

    public List<Aluno> getAlunosRegistroAtendimentos() {
        return registroAtendimentos.stream().map(RegistroAtendimento::getAluno).toList();
    }
}
