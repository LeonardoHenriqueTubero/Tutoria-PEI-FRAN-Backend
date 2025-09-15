package com.br.tutoria.pei.fran.dtos;

import com.br.tutoria.pei.fran.entities.Ocorrencia;
import jakarta.validation.constraints.Positive;

public class OcorrenciaDTO {

    private Long id;
    @Positive(message = "Nao pode ser negativo")
    private Integer numBi1;
    @Positive(message = "Nao pode ser negativo")
    private Integer numBi2;
    @Positive(message = "Nao pode ser negativo")
    private Integer numBi3;
    @Positive(message = "Nao pode ser negativo")
    private Integer numBi4;

    public OcorrenciaDTO() {}

    public OcorrenciaDTO(Long id, Integer numBi1, Integer numBi2, Integer numBi3, Integer numBi4) {
        this.id = id;
        this.numBi1 = numBi1;
        this.numBi2 = numBi2;
        this.numBi3 = numBi3;
        this.numBi4 = numBi4;
    }

    public OcorrenciaDTO(Ocorrencia ocorrencia) {
        id = ocorrencia.getId();
        numBi1 = ocorrencia.getNumBi1();
        numBi2 = ocorrencia.getNumBi2();
        numBi3 = ocorrencia.getNumBi3();
        numBi4 = ocorrencia.getNumBi4();
    }

    public Long getId() {
        return id;
    }

    public Integer getNumBi1() {
        return numBi1;
    }

    public Integer getNumBi2() {
        return numBi2;
    }

    public Integer getNumBi3() {
        return numBi3;
    }

    public Integer getNumBi4() {
        return numBi4;
    }
}
