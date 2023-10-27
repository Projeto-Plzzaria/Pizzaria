package com.pizzaria.dto;

import com.pizzaria.entity.Comida;
import com.pizzaria.entity.Tamanho;
import com.pizzaria.entity.Sabores;  // Importe a classe Sabores se ainda não tiver importado

import java.util.List;
import java.util.stream.Collectors;

public class ComidaConverter {

    private ComidaConverter() {
        // Construtor vazio privado para não ter instâncias.
    }

    public static ComidaDTO toDto(Comida comida) {
        ComidaDTO dto = new ComidaDTO();
        dto.setId(comida.getId());
        dto.setTamanho(comida.getTamanho());
        dto.setSabores(comida.getSabores());

        return dto;
    }

    public static Comida toEntity(ComidaDTO dto) {
        Comida comida = new Comida();
        comida.setId(dto.getId());
        comida.setTamanho(dto.getTamanho());
        comida.setSabores(dto.getSabores());

        return comida;
    }

    public static List<ComidaDTO> toDtoList(List<Comida> comidas) {
        return comidas.stream()
                .map(ComidaConverter::toDto)
                .collect(Collectors.toList());
    }
}
