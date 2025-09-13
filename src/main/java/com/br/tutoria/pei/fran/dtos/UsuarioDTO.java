package com.br.tutoria.pei.fran.dtos;

import com.br.tutoria.pei.fran.entities.Usuario;

public class UsuarioDTO {
    private Integer cpf;
    private String nome;


    public UsuarioDTO(Integer cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }
    public UsuarioDTO() {
    }

    public UsuarioDTO(Usuario usuario) {
        this.cpf = usuario.getCpf();
        this.nome = usuario.getNome();
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
}
