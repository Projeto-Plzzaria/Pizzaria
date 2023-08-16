package com.Pizzaria.pizzaria.Repository;

import Fozesc.com.demo.Entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Pessoa, Long> {
    List<Pessoa> findByAtivo(boolean ativo);
}
