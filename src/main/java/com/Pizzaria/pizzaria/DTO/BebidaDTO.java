package com.Pizzaria.pizzaria.DTO;

import lombok.Getter;
import lombok.Setter;

public class BebidaDTO {
    @Getter@Setter
    private int tamanho;
    @Getter@Setter
    private String sabor;


    public BebidaDTO() {
    }

    public BebidaDTO(int tamanho, String sabor) {
        this.tamanho = tamanho;
        this.sabor = sabor;
    }
}