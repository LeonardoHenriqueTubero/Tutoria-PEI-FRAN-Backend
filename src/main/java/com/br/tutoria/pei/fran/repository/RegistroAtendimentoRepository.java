package com.br.tutoria.pei.fran.repository;

import com.br.tutoria.pei.fran.entities.RegistroAtendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroAtendimentoRepository extends JpaRepository<RegistroAtendimento, Long> {

}

