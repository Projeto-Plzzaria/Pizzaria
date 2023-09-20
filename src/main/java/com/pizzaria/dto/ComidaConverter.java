package com.pizzaria.dto;

import com.pizzaria.entity.Comida;

import java.util.List;
import java.util.stream.Collectors;

public class ComidaConverter {

    public static ComidaDTO toDto(Comida comida) {
        ComidaDTO dto = new ComidaDTO();
        dto.setTamanho(comida.getTamanho());
        dto.setIngredientes(comida.getIngredientes());
        return dto;
    }

    public static Comida toEntity(ComidaDTO dto) {
        Comida comida = new Comida();
        comida.setTamanho(dto.getTamanho());
        comida.setIngredientes(dto.getIngredientes());
        return comida;
    }

    public static List<ComidaDTO> toDtoList(List<Comida> comidas) {
        return comidas.stream()
                .map(ComidaConverter::toDto)
                .collect(Collectors.toList());
    }
}
