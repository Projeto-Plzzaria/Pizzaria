package com.pizzaria.dto;

import com.pizzaria.entity.Bebida;
import com.pizzaria.entity.Cliente;
import com.pizzaria.entity.Comida;
import com.pizzaria.entity.Funcionario;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter
public class PedidoDTO {

    private Long id;

    private List<Bebida> bebida;
    private List<Comida> comida;
    private String obs;
    private Funcionario funcionario;
    private Cliente cliente;
    private Double valor;

    public PedidoDTO() {
    }

    public PedidoDTO(List<Comida> comida,String obs, Long id, List<Bebida> bebida, Funcionario funcionario, Cliente cliente, Double valor) {
        this.obs = obs;
        this.comida = comida;
        this.bebida = bebida;
        this.funcionario = funcionario;
        this.cliente = cliente;
        this.valor = valor;
        this.id = id;
    }

}
