package com.br.tutoria.pei.fran.entities;


import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "tb_usuario")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpf;
    private String nome;

    @ManyToMany
    @JoinTable(name = "tb_usuario_papel",
    joinColumns = @JoinColumn(name = "usuario_id"),
    inverseJoinColumns = @JoinColumn(name = "papel_id"))
    private Set<Papel> papeis = new HashSet<>();

    @OneToMany(mappedBy = "id.usuario")
    Set<Tutoria> tutorias = new HashSet<>();

    @OneToMany(mappedBy = "id.usuario")
    Set<RegistroAtendimento> registroAtendimentos = new HashSet<>();

    @OneToMany
    List<Aluno> tutorandos = new ArrayList<>();

    public Usuario(Long id, String cpf, String nome) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
    }

    public Usuario() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void addPapel(Papel papel) {
        papeis.add(papel);
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return papeis;
    }

    @Override
    public String getPassword() {
        return this.cpf;
    }

    @Override
    public String getUsername() {
        return nome;
    }

    public boolean hasPapel(String nomePapel) {
        for(Papel papel : papeis) {
            if(papel.getAuthority().equals(nomePapel)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
