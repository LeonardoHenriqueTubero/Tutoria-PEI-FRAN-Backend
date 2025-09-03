package com.br.tutoria.pei.fran.dtos;

import com.br.tutoria.pei.fran.entities.DadosFamilia;

public class DadosFamiliaDTO {

    private Long id;
    private String pai;
    private String mae;
    private String responsavel;
    private String estruturaFamiliar;
    private Integer numPai;
    private Integer numMae;
    private Integer numResponsavel;

    public DadosFamiliaDTO() {}

    public DadosFamiliaDTO(Long id, String pai, String mae, String responsavel, String estruturaFamiliar, Integer numPai, Integer numMae, Integer numResponsavel) {
        this.id = id;
        this.pai = pai;
        this.mae = mae;
        this.responsavel = responsavel;
        this.estruturaFamiliar = estruturaFamiliar;
        this.numPai = numPai;
        this.numMae = numMae;
        this.numResponsavel = numResponsavel;
    }

    public DadosFamiliaDTO(DadosFamilia dadosFamilia) {
        id = dadosFamilia.getId();
        pai = dadosFamilia.getPai();
        mae = dadosFamilia.getMae();
        responsavel = dadosFamilia.getResponsavel();
        estruturaFamiliar = dadosFamilia.getEstruturaFamiliar();
        numPai = dadosFamilia.getNumPai();
        numMae = dadosFamilia.getNumMae();
        numResponsavel = dadosFamilia.getNumResponsavel();
    }

    public Long getId() {
        return id;
    }

    public String getPai() {
        return pai;
    }

    public String getMae() {
        return mae;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public String getEstruturaFamiliar() {
        return estruturaFamiliar;
    }

    public Integer getNumPai() {
        return numPai;
    }

    public Integer getNumMae() {
        return numMae;
    }

    public Integer getNumResponsavel() {
        return numResponsavel;
    }
}
