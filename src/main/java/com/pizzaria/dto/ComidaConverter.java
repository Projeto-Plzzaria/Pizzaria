package com.pizzaria.dto;

import com.pizzaria.entity.Comida;
import com.pizzaria.entity.Tamanho;

import java.util.List;
import java.util.stream.Collectors;

public class ComidaConverter {

    private ComidaConverter() {
        // Construtor vazio privado para nao ter instancias.
    }

    public static ComidaDTO toDto(Comida comida) {
        ComidaDTO dto = new ComidaDTO();
        dto.setTamanho(comida.getTamanho());
        dto.setIngredientes(comida.getIngredientes());
        dto.setValor(comida.getValor());
        return dto;
    }

    public static Comida toEntity(ComidaDTO dto) {
        Comida comida = new Comida(Tamanho.GIGANTE, "Calabresa");
        comida.setTamanho(dto.getTamanho());
        comida.setIngredientes(dto.getIngredientes());
        comida.setValor(dto.getValor());
        return comida;
    }

    public static List<ComidaDTO> toDtoList(List<Comida> comidas) {
        return comidas.stream()
                .map(ComidaConverter::toDto)
                .collect(Collectors.toList());
    }
}
