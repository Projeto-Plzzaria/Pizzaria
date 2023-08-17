package com.Pizzaria.pizzaria.Repository;

import com.Pizzaria.pizzaria.Entity.Endereco;
import com.Pizzaria.pizzaria.Entity.Funcionario;
import com.Pizzaria.pizzaria.Entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByAtivo(boolean ativo);
}
