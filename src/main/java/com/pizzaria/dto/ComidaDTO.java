package com.pizzaria.dto;

import com.pizzaria.entity.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

public class ComidaDTO {
    @Getter @Setter
    private Long id;
    @Getter @Setter
    private Tamanho tamanho;

    @Getter @Setter
    private Saborum saborum;

    @Getter @Setter
    private Sabordois sabordois;

    @Getter @Setter
    private Sabortres sabortres;


    public ComidaDTO() {
    }


    public ComidaDTO(Long id, Tamanho tamanho, Saborum saborum, Sabordois sabordois, Sabortres sabortres) {
        this.id = id;
        this.tamanho = tamanho;
        this.saborum = saborum;
        this.sabordois = sabordois;
        this.sabortres = sabortres;

    }

    public static List<ComidaDTO> toDtoList(List<Comida> comidas) {
        return comidas.stream()
                .map(ComidaConverter::toDto)
                .collect(Collectors.toList());
    }
}
