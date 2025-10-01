package com.br.tutoria.pei.fran.repository;

import com.br.tutoria.pei.fran.entities.Papel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PapelRepository extends JpaRepository<Papel, Long> {

    Optional<Papel> findByAuthority(String authority);
}
