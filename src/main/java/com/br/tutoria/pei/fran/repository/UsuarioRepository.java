package com.br.tutoria.pei.fran.repository;

import com.br.tutoria.pei.fran.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    UserDetails findByCpf(Integer cpf);

    Integer cpf(Integer cpf);

    boolean findByCpf(CharSequence cpf);
}
