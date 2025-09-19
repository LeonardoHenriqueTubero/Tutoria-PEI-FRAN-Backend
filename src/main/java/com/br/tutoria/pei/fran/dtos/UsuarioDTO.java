package com.br.tutoria.pei.fran.dtos;

import com.br.tutoria.pei.fran.entities.Usuario;
import jakarta.validation.constraints.NotBlank;

public class UsuarioDTO {

    @NotBlank(message = "Campo requerido")
    private String cpf;
    @NotBlank(message = "Campo requerido")
    private String nome;

    public UsuarioDTO() {
    }

    public UsuarioDTO(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }

    public UsuarioDTO(Usuario usuario) {
        this.cpf = usuario.getCpf();
        this.nome = usuario.getNome();
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
}
