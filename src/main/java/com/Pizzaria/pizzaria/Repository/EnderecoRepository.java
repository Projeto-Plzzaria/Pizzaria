package com.Pizzaria.pizzaria.Repository;

import com.Pizzaria.pizzaria.Entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    List<Endereco> findByAtivo(boolean ativo);
}
