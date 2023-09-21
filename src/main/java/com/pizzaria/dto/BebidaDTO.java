package com.pizzaria.dto;

import com.pizzaria.entity.Bebida;
import com.pizzaria.entity.TamanhoB;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

public class BebidaDTO {
    @Getter@Setter
    private TamanhoB tamanho;
    @Getter@Setter
    private String sabor;


    public BebidaDTO() {
    }
    public BebidaDTO(TamanhoB tamanho, String sabor) {
        this.tamanho = tamanho;
        this.sabor = sabor;
    }

    public BebidaDTO(long l, String bebida1, boolean b) {
    }
    public static List<BebidaDTO> toDtoList(List<Bebida> bebidas) {
        return bebidas.stream()
                .map(BebidaConverter::toDto)
                .collect(Collectors.toList());
    }
}