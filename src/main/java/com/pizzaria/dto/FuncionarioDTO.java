package com.pizzaria.dto;

import com.pizzaria.entity.Cargo;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FuncionarioDTO {
    private String nome;
    private Cargo cargo;
    private String email;


    public FuncionarioDTO() {
    }

    public FuncionarioDTO(String nome, Cargo cargo, String email) {
        this.nome = nome;
        this.cargo = cargo;
        this.email = email;
    }
}