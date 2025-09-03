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
    private Integer cpf;
    private String nome;

    @OneToMany(mappedBy = "id.usuario")
    Set<Tutoria> tutorias = new HashSet<>();

    @OneToMany(mappedBy = "id.usuario")
    Set<RegistroAtendimento> registroAtendimentos = new HashSet<>();

    public Usuario(Integer cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }

    public Usuario() {

    }

    public Integer getCpf() {
        return cpf;
    }

    public void setCpf(Integer cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
