package com.Pizzaria.pizzaria.DTO;

import lombok.Getter;
import lombok.Setter;

public class ComidaDTO {
    @Getter@Setter
    private int tamanho;
    @Getter@Setter
    private int qidSabores;
    @Getter@Setter
    private int addIngrediente;

    public ComidaDTO() {
    }

    public ComidaDTO(int tamanho, int qidSabores, int addIngrediente) {
        this.tamanho = tamanho;
        this.qidSabores = qidSabores;
        this.addIngrediente = addIngrediente;
    }
}
