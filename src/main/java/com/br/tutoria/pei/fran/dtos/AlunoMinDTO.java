package com.br.tutoria.pei.fran.dtos;

public class AlunoMinDTO {

    private String name;
    private String imgUrl;

    public AlunoMinDTO() {}

    public AlunoMinDTO(String name, String imgUrl) {
        this.name = name;
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
