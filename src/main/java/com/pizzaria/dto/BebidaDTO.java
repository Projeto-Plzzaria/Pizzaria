package com.pizzaria.dto;

import com.pizzaria.entity.Bebida;
import com.pizzaria.entity.TamanhoB;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

public class BebidaDTO {
    @Getter @Setter
    private Long id;
    @Getter@Setter
    private TamanhoB tamanho;
    @Getter@Setter
    private String sabor;
    @Getter@Setter
    private Double valor;

    public BebidaDTO() {
    }
    public BebidaDTO(Long id, TamanhoB tamanho, String sabor,Double valor) {
        this.id = id;
        this.tamanho = tamanho;
        this.sabor = sabor;
        this.valor = valor;
    }
    public static List<BebidaDTO> toDtoList(List<Bebida> bebidas) {
        return bebidas.stream()
                .map(BebidaConverter::toDto)
                .collect(Collectors.toList());
    }
}