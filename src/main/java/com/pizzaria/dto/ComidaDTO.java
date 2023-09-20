package com.pizzaria.dto;

import com.pizzaria.entity.Tamanho;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ComidaDTO {
    @Getter@Setter
    private Tamanho tamanho;

    @Getter@Setter
    private List<String> ingredientes;


    public ComidaDTO() {
    }

    public ComidaDTO(Tamanho tamanho, List<String> ingredientes) {
        this.tamanho = tamanho;
        this.ingredientes = ingredientes;
    }
}
