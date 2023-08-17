package com.Pizzaria.pizzaria.DTO;

import com.Pizzaria.pizzaria.Entity.Bebida;

import java.util.List;
import java.util.stream.Collectors;

public class BebidaConverter {

    public static BebidaDTO toDto(Bebida bebida) {
        BebidaDTO dto = new BebidaDTO();
        dto.setTamanho(bebida.getTamanho());
        dto.setSabor(bebida.getSabor());
        return dto;
    }

    public static Bebida toEntity(BebidaDTO dto) {
        Bebida bebida = new Bebida();
        bebida.setTamanho(dto.getTamanho());
        bebida.setSabor(dto.getSabor());
        return bebida;
    }

    public static List<BebidaDTO> toDtoList(List<Bebida> bebidas) {
        return bebidas.stream()
                .map(BebidaConverter::toDto)
                .collect(Collectors.toList());
    }
}
