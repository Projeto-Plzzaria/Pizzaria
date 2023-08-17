package com.Pizzaria.pizzaria.DTO;

import com.Pizzaria.pizzaria.Entity.Cargo;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FuncionarioDTO {
    private String nome;
    private Cargo cargo;


    public FuncionarioDTO() {
    }

    public FuncionarioDTO(String nome, Cargo cargo) {
        this.nome = nome;
        this.cargo = cargo;
    }
}