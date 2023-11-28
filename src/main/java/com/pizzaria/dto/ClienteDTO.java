package com.pizzaria.dto;

import lombok.Getter;
import lombok.Setter;


public class ClienteDTO {
    @Getter@Setter
    private Long id;
    @Getter@Setter
    private String nome;
    @Getter@Setter
    private String numero;

    public ClienteDTO() {
    }
    public ClienteDTO(Long id, String nome, String numero) {
        this.id = id;
        this.nome = nome;
        this.numero = numero;
    }
}
