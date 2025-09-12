package com.br.tutoria.pei.fran.Enums;

public enum UsuarioRole {
    COORDENADOR ("coordenador"),
    PROFESSOR ("professor");

    private String role;

     UsuarioRole(String role) {
        this.role = role;
    }
    public String getRole() {
         return role;
    }
}
