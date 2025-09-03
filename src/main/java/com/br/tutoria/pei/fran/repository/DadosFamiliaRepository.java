package com.br.tutoria.pei.fran.repository;

import com.br.tutoria.pei.fran.entities.DadosFamilia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DadosFamiliaRepository extends JpaRepository<DadosFamilia, Long> {
}
