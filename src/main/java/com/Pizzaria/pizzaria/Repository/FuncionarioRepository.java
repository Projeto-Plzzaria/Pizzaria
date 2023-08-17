package com.Pizzaria.pizzaria.Repository;

import com.Pizzaria.pizzaria.Entity.Endereco;
import com.Pizzaria.pizzaria.Entity.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    List<Funcionario> findByAtivo(boolean ativo);
}
