package com.pizzaria.dto;

import com.pizzaria.entity.Comida;
import com.pizzaria.entity.Tamanho;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

public class ComidaDTO {
    @Getter@Setter
    private Tamanho tamanho;

    @Getter@Setter
    private String ingredientes;
    @Getter@Setter
    private Double valor;

    public ComidaDTO() {
    }

    public ComidaDTO(Tamanho tamanho, String ingredientes,Double valor) {
        this.tamanho = tamanho;
        this.ingredientes = ingredientes;
        this.valor = valor;
    }
    public static List<ComidaDTO> toDtoList(List<Comida> comidas) {
        return comidas.stream()
                .map(ComidaConverter::toDto)
                .collect(Collectors.toList());
    }
}
