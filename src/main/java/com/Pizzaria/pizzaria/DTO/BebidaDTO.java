package com.Pizzaria.pizzaria.DTO;

import com.Pizzaria.pizzaria.Entity.Tamanho;
import com.Pizzaria.pizzaria.Entity.TamanhoB;
import lombok.Getter;
import lombok.Setter;

public class BebidaDTO {
    @Getter@Setter
    private TamanhoB tamanho;
    @Getter@Setter
    private String sabor;


    public BebidaDTO() {
    }
    public BebidaDTO(TamanhoB tamanho, String sabor) {
        this.tamanho = tamanho;
        this.sabor = sabor;
    }
}