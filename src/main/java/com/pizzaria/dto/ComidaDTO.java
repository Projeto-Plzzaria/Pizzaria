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
    private List<String> ingredientes;


    public ComidaDTO() {
    }

    public ComidaDTO(Tamanho tamanho, List<String> ingredientes) {
        this.tamanho = tamanho;
        this.ingredientes = ingredientes;
    }

    public static List<ComidaDTO> toDtoList(List<Comida> comidas) {
        return comidas.stream()
                .map(ComidaConverter::toDto)
                .collect(Collectors.toList());
    }
}
