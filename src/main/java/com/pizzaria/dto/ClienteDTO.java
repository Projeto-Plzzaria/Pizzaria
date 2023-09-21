package com.pizzaria.dto;

import lombok.Getter;
import lombok.Setter;


public class ClienteDTO {
    @Getter@Setter
    private String nome;
    @Getter@Setter
    private String numero;
    @Getter@Setter
    private EnderecoDTO endereco;


    public ClienteDTO() {
    }
    public ClienteDTO(String nome, String numero, EnderecoDTO endereco) {
        this.nome = nome;
        this.numero = numero;
        this.endereco = endereco;
    }
}
