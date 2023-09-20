package com.pizzaria.dto;

import com.pizzaria.entity.Bebida;
import com.pizzaria.entity.Cliente;
import com.pizzaria.entity.Comida;
import com.pizzaria.entity.Funcionario;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class PedidoDTO {
    private Bebida bebida;
    private Comida comida;
    private Funcionario funcionario;
    private Cliente cliente;
    private Double valor;

    public PedidoDTO() {
    }

    public PedidoDTO(Bebida bebida, Comida comida, Funcionario funcionario, Cliente cliente, Double valor) {
        this.bebida = bebida;
        this.comida = comida;
        this.funcionario = funcionario;
        this.cliente = cliente;
        this.valor = valor;
    }

}
