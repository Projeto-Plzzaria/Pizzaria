package com.pizzaria.dto;

import com.pizzaria.entity.Cargo;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FuncionarioDTO {
    private Long id;
    private String nome;
    private Cargo cargo;
    private String email;
    private String numero;



    public FuncionarioDTO() {
    }

    public FuncionarioDTO(Long id, String nome, Cargo cargo, String email, String numero) {
        this.id = id;
        this.nome = nome;
        this.cargo = cargo;
        this.email = email;
        this.numero = numero;
    }
}