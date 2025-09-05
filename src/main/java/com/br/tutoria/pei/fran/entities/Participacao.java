package com.br.tutoria.pei.fran.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_participacao")
public class Participacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer semestre;
    private String clubeJuvenil;
    private String eletiva;
    private Boolean liderTurma;
    private Boolean alunoGremista;
    private Boolean jovemAcolhedor;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "participacao")
    private Aluno aluno;

    public Participacao() {}

    public Participacao(Long id, Integer semestre, String clubeJuvenil, String eletiva, Boolean liderTurma, Boolean alunoGremista, Boolean jovemAcolhedor, Aluno aluno) {
        this.id = id;
        this.semestre = semestre;
        this.clubeJuvenil = clubeJuvenil;
        this.eletiva = eletiva;
        this.liderTurma = liderTurma;
        this.alunoGremista = alunoGremista;
        this.jovemAcolhedor = jovemAcolhedor;
        this.aluno = aluno;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }

    public String getClubeJuvenil() {
        return clubeJuvenil;
    }

    public void setClubeJuvenil(String clubeJuvenil) {
        this.clubeJuvenil = clubeJuvenil;
    }

    public String getEletiva() {
        return eletiva;
    }

    public void setEletiva(String eletiva) {
        this.eletiva = eletiva;
    }

    public Boolean getLiderTurma() {
        return liderTurma;
    }

    public void setLiderTurma(Boolean liderTurma) {
        this.liderTurma = liderTurma;
    }

    public Boolean getAlunoGremista() {
        return alunoGremista;
    }

    public void setAlunoGremista(Boolean alunoGremista) {
        this.alunoGremista = alunoGremista;
    }

    public Boolean getJovemAcolhedor() {
        return jovemAcolhedor;
    }

    public void setJovemAcolhedor(Boolean jovemAcolhedor) {
        this.jovemAcolhedor = jovemAcolhedor;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Participacao that = (Participacao) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
