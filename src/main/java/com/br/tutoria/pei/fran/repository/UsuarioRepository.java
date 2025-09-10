package com.br.tutoria.pei.fran.repository;

import com.br.tutoria.pei.fran.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
