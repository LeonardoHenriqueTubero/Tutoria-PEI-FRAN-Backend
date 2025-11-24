package com.br.tutoria.pei.fran.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_registro_atendimento")
public class RegistroAtendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long alunoRA;
    private LocalDate data;
    private String assunto;
    private String observacoesProfessor;

    public RegistroAtendimento() {}

    public RegistroAtendimento(Long id, Long alunoRA, LocalDate data, String assunto, String observacoesProfessor) {
        this.id = id;
        this.alunoRA = alunoRA;
        this.data = data;
        this.assunto = assunto;
        this.observacoesProfessor = observacoesProfessor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAlunoRA() {
        return alunoRA;
    }

    public void setAlunoRA(Long alunoRA) {
        this.alunoRA = alunoRA;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getObservacoesProfessor() {
        return observacoesProfessor;
    }

    public void setObservacoesProfessor(String observacoesProfessor) {
        this.observacoesProfessor = observacoesProfessor;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        RegistroAtendimento that = (RegistroAtendimento) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
