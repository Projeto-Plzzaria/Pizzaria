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
    @Getter@Setter
    private EnderecoDTO endereco;


    public ClienteDTO() {
    }
    public ClienteDTO(Long id, String nome, String numero, EnderecoDTO endereco) {
        this.id = id;
        this.nome = nome;
        this.numero = numero;
        this.endereco = endereco;
    }
}
