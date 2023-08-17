package com.Pizzaria.pizzaria.Repository;

import com.Pizzaria.pizzaria.Entity.Bebida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BebidasRepository extends JpaRepository<Bebida, Long> {
    List<Bebida> findByAtivo(boolean ativo);
}
