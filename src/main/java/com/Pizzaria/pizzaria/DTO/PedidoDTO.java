package com.Pizzaria.pizzaria.DTO;

import com.Pizzaria.pizzaria.Entity.*;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class PedidoDTO {
    private Bebida bebida;
    private Comida comida;
    private Funcionario funcionario;
    private Cliente cliente;

    public PedidoDTO() {
    }

    public PedidoDTO(Bebida bebida, Comida comida, Funcionario funcionario, Cliente cliente) {
        this.bebida = bebida;
        this.comida = comida;
        this.funcionario = funcionario;
        this.cliente = cliente;
    }

}
