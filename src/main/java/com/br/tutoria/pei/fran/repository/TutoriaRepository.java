package com.br.tutoria.pei.fran.repository;

import com.br.tutoria.pei.fran.entities.Tutoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutoriaRepository extends JpaRepository<Tutoria, Long> {

}

