package com.pizzaria.dto;

import com.pizzaria.entity.Bebida;

import java.util.List;
import java.util.stream.Collectors;

public class BebidaConverter {

    private BebidaConverter() {
        // Construtor vazio privado para nao ter instancias.
    }

    public static BebidaDTO toDto(Bebida bebida) {
        BebidaDTO dto = new BebidaDTO();
        dto.setId(bebida.getId());
        dto.setTamanho(bebida.getTamanho());
        dto.setSabor(bebida.getSabor());
        dto.setValor(bebida.getValor());
        return dto;
    }

    public static Bebida toEntity(BebidaDTO dto) {
        Bebida bebida = new Bebida();
        bebida.setId(dto.getId());
        bebida.setTamanho(dto.getTamanho());
        bebida.setSabor(dto.getSabor());
        bebida.setValor(dto.getValor());
        return bebida;
    }

    public static List<BebidaDTO> toDtoList(List<Bebida> bebidas) {
        return bebidas.stream()
                .map(BebidaConverter::toDto)
                .collect(Collectors.toList());
    }
}
