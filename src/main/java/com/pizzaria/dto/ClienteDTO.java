package com.pizzaria.dto;

import lombok.Getter;
import lombok.Setter;


public class ClienteDTO {
    @Getter@Setter
    private String nome;
    @Getter@Setter
    private String numero;

    public ClienteDTO() {
    }
    public ClienteDTO(String nome, String numero) {
        this.nome = nome;
        this.numero = numero;
    }
}
