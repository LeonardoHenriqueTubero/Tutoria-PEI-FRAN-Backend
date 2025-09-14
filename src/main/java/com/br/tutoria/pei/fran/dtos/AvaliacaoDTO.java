package com.br.tutoria.pei.fran.dtos;

import com.br.tutoria.pei.fran.entities.Avaliacao;

public class AvaliacaoDTO {

    private Long id;
    private String tipo;
    private String materia;
    private Integer numQuestoes;
    private Double numAcertos;

    public AvaliacaoDTO() {}

    public AvaliacaoDTO(Long id, String tipo, String materia, Integer numQuestoes, Double numAcertos) {
        this.id = id;
        this.tipo = tipo;
        this.materia = materia;
        this.numQuestoes = numQuestoes;
        this.numAcertos = numAcertos;
    }

    public AvaliacaoDTO(Avaliacao avaliacao) {
        id = avaliacao.getId();
        tipo = avaliacao.getTipo();
        materia = avaliacao.getMateria();
        numQuestoes = avaliacao.getNumQuestoes();
        numAcertos = avaliacao.getNumAcertos();
    }

    public Long getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getMateria() {
        return materia;
    }

    public Integer getNumQuestoes() {
        return numQuestoes;
    }

    public Double getNumAcertos() {
        return numAcertos;
    }
}
