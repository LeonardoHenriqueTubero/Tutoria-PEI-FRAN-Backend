package com.br.tutoria.pei.fran.dtos;

import com.br.tutoria.pei.fran.entities.Endereco;

public class EnderecoDTO {

    private String rua;
    private String bairro;
    private String cidade;
    private String cep;

    public EnderecoDTO() {}

    public EnderecoDTO(Long id, String rua, String bairro, String cidade, String cep) {
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.cep = cep;
    }

    public EnderecoDTO(Endereco endereco) {
        rua = endereco.getRua();
        bairro = endereco.getBairro();
        cidade = endereco.getCidade();
        cep = endereco.getCep();
    }

    public String getRua() {
        return rua;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getCep() {
        return cep;
    }
}
