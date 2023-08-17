package com.Pizzaria.pizzaria.DTO;

import com.Pizzaria.pizzaria.Entity.Comida;

import java.util.List;
import java.util.stream.Collectors;

public class ComidaConverter {

    public static ComidaDTO toDto(Comida comida) {
        ComidaDTO dto = new ComidaDTO();
        dto.setTamanho(comida.getTamanho());
        dto.setQidSabores(comida.getQidSabores());
        dto.setAddIngrediente(comida.getAddIngrediente());
        return dto;
    }

    public static Comida toEntity(ComidaDTO dto) {
        Comida comida = new Comida();
        comida.setTamanho(dto.getTamanho());
        comida.setQidSabores(dto.getQidSabores());
        comida.setAddIngrediente(dto.getAddIngrediente());
        return comida;
    }

    public static List<ComidaDTO> toDtoList(List<Comida> comidas) {
        return comidas.stream()
                .map(ComidaConverter::toDto)
                .collect(Collectors.toList());
    }
}
