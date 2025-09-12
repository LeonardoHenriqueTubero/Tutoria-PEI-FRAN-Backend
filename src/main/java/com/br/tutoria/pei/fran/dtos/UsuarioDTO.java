package com.br.tutoria.pei.fran.dtos;

import com.br.tutoria.pei.fran.entities.Usuario;

public class UsuarioDTO {
    private Integer cpf;
    private String nome;
    private String role;


    public UsuarioDTO(Integer cpf, String nome, String role) {
        this.cpf = cpf;
        this.nome = nome;
        this.role = role;
    }

    public UsuarioDTO(Usuario usuario) {
        this.cpf = usuario.getCpf();
        this.nome = usuario.getNome();
        //this.role = usuario.getRole();
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

    /*public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }*/
}
