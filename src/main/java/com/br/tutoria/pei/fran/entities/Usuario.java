package com.br.tutoria.pei.fran.entities;

import com.br.tutoria.pei.fran.Enums.UsuarioRole;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_usuario")
public class Usuario implements UserDetails {

    @Id
    private Integer cpf;
    private String nome;
    private UsuarioRole role;

   /* public User(String nome, Integer cpf, UsuarioRole role) {
        this.nome = nome;
        this.cpf = cpf;
        this.role = role;
    }*/

    @OneToMany(mappedBy = "id.usuario")
    Set<Tutoria> tutorias = new HashSet<>();

    @OneToMany(mappedBy = "id.usuario")
    Set<RegistroAtendimento> registroAtendimentos = new HashSet<>();

    public Usuario(Integer cpf, String nome, UsuarioRole role) {
        this.cpf = cpf;
        this.nome = nome;
        this.role = role;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UsuarioRole.COORDENADOR) {
            return List.of(new SimpleGrantedAuthority("ROLE_COORDENADOR"), new SimpleGrantedAuthority("ROLE_PROFESSOR"));
        }
        else return List.of(new SimpleGrantedAuthority("ROLE_PROFESSOR"));
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return nome;
    }

    @Override
    public boolean isAccountNonExpired() {
        return  true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return  true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return  true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
