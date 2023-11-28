package com.pizzaria.dto;

import jakarta.persistence.Column;
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
    private String rua;
    @Getter@Setter
    private int num;
    @Getter@Setter
    private String bairro;


    public ClienteDTO() {
    }
    public ClienteDTO(Long id, String nome, String numero, String rua, int num, String bairro) {
        this.id = id;
        this.nome = nome;
        this.numero = numero;
        this.rua = rua;
        this.num = num;
        this.bairro = bairro;
    }
}
