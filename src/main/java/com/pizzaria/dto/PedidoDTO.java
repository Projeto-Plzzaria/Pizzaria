package com.pizzaria.dto;

import com.pizzaria.entity.*;
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
    private Endereco endereco;
    private Double valor;

    public PedidoDTO() {
    }

    public PedidoDTO(List<Comida> comida,String obs, Long id, List<Bebida> bebida, Funcionario funcionario, Endereco endereco, Double valor) {
        this.obs = obs;
        this.comida = comida;
        this.bebida = bebida;
        this.funcionario = funcionario;
        this.endereco = endereco;
        this.valor = valor;
        this.id = id;
    }

}
