package com.Pizzaria.pizzaria.Repository;

import com.Pizzaria.pizzaria.Entity.Comida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComidaRepository extends JpaRepository<Comida, Long> {
    List<Comida> findByAtivo(boolean ativo);
}
