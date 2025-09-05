package com.br.tutoria.pei.fran.dtos;

import com.br.tutoria.pei.fran.entities.Escolaridade;

import java.util.ArrayList;
import java.util.List;

public class EscolaridadeDTO {

    private Boolean contatoFora;
    private Boolean difAprendizagem;
    private Boolean apoioPedagogico;
    private List<String> disciplinasFacilidade = new ArrayList<>();
    private List<String> disciplinasDificuldade = new ArrayList<>();
    private Boolean atividadeExtra;
    private Boolean difLocomotiva;
    private Boolean difVisao;
    private Boolean difAtencao;
    private Boolean difFala;
    private Boolean difEscrita;
    private Boolean adaptacaoGrupo;
    private Boolean reprovado;
    private String serieAnoReprovado;

    public EscolaridadeDTO() {}

    public EscolaridadeDTO(Boolean contatoFora, Boolean difAprendizagem, Boolean apoioPedagogico, List<String> disciplinasFacilidade, List<String> disciplinasDificuldade, Boolean atividadeExtra, Boolean difLocomotiva, Boolean difVisao, Boolean difAtencao, Boolean difFala, Boolean difEscrita, Boolean adaptacaoGrupo, Boolean reprovado, String serieAnoReprovado) {
        this.contatoFora = contatoFora;
        this.difAprendizagem = difAprendizagem;
        this.apoioPedagogico = apoioPedagogico;
        this.atividadeExtra = atividadeExtra;
        this.difLocomotiva = difLocomotiva;
        this.difVisao = difVisao;
        this.difAtencao = difAtencao;
        this.difFala = difFala;
        this.difEscrita = difEscrita;
        this.adaptacaoGrupo = adaptacaoGrupo;
        this.reprovado = reprovado;
        this.serieAnoReprovado = serieAnoReprovado;
    }

    public EscolaridadeDTO(Escolaridade escolaridade) {
        contatoFora = escolaridade.getContatoFora();
        difAprendizagem = escolaridade.getDifAprendizagem();
        apoioPedagogico = escolaridade.getApoioPedagogico();
        atividadeExtra = escolaridade.getAtividadeExtra();
        difLocomotiva = escolaridade.getDifLocomotiva();
        difVisao = escolaridade.getDifVisao();
        difAtencao = escolaridade.getDifAtencao();
        difFala = escolaridade.getDifFala();
        difEscrita = escolaridade.getDifEscrita();
        adaptacaoGrupo = escolaridade.getAdaptacaoGrupo();
        reprovado = escolaridade.getReprovado();
        serieAnoReprovado = escolaridade.getSerieAnoReprovado();
        for (String facilidade : escolaridade.getDisciplinasFacilidade()) {
            disciplinasFacilidade.add(facilidade);
        }
        for (String dificuldade : escolaridade.getDisciplinasDificuldade()) {
            disciplinasDificuldade.add(dificuldade);
        }
    }

    public Boolean getContatoFora() {
        return contatoFora;
    }

    public Boolean getDifAprendizagem() {
        return difAprendizagem;
    }

    public Boolean getApoioPedagogico() {
        return apoioPedagogico;
    }

    public List<String> getDisciplinasFacilidade() {
        return disciplinasFacilidade;
    }

    public List<String> getDisciplinasDificuldade() {
        return disciplinasDificuldade;
    }

    public Boolean getAtividadeExtra() {
        return atividadeExtra;
    }

    public Boolean getDifLocomotiva() {
        return difLocomotiva;
    }

    public Boolean getDifVisao() {
        return difVisao;
    }

    public Boolean getDifAtencao() {
        return difAtencao;
    }

    public Boolean getDifFala() {
        return difFala;
    }

    public Boolean getDifEscrita() {
        return difEscrita;
    }

    public Boolean getAdaptacaoGrupo() {
        return adaptacaoGrupo;
    }

    public Boolean getReprovado() {
        return reprovado;
    }

    public String getSerieAnoReprovado() {
        return serieAnoReprovado;
    }
}
