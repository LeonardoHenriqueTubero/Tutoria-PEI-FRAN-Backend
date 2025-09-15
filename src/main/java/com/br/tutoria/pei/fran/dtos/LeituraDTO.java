package com.br.tutoria.pei.fran.dtos;

import com.br.tutoria.pei.fran.entities.Leitura;

public class LeituraDTO {

    private Long id;
    private Integer bimestre;
    private String livro;

    public LeituraDTO() {}

    public LeituraDTO(Long id, Integer bimestre, String livro) {
        this.id = id;
        this.bimestre = bimestre;
        this.livro = livro;
    }

    public LeituraDTO(Leitura leitura) {
        id = leitura.getId();
        bimestre = leitura.getBimestre();
        livro = leitura.getLivro();
    }

    public Long getId() {
        return id;
    }

    public Integer getBimestre() {
        return bimestre;
    }

    public String getLivro() {
        return livro;
    }
}
