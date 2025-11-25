package com.br.tutoria.pei.fran.dtos;

import com.br.tutoria.pei.fran.entities.RegistroAtendimento;

import java.time.LocalDate;

public class RegistroAtendimentoDTO {

    private Long id;
    private LocalDate data;
    private String assunto;
    private String observacoesProfessor;

    public RegistroAtendimentoDTO() {}

    public RegistroAtendimentoDTO(Long id, LocalDate data, String assunto, String observacoesProfessor) {
        this.id = id;
        this.data = data;
        this.assunto = assunto;
        this.observacoesProfessor = observacoesProfessor;
    }

    public RegistroAtendimentoDTO(RegistroAtendimento registro) {
        id = registro.getId();
        data = registro.getData();
        assunto = registro.getAssunto();
        observacoesProfessor = registro.getObservacoesProfessor();
    }

    public Long getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
    }

    public String getAssunto() {
        return assunto;
    }

    public String getObservacoesProfessor() {
        return observacoesProfessor;
    }
}
