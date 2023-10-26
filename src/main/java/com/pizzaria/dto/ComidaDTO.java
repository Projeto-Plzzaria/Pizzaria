package com.pizzaria.dto;

import com.pizzaria.entity.Comida;
import com.pizzaria.entity.Tamanho;
import com.pizzaria.entity.Sabores;  // Importe a classe Sabores se ainda não tiver importado
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
    private List<Sabores> sabores;


    public ComidaDTO() {
    }


    public ComidaDTO(Long id, Tamanho tamanho, List<Sabores> sabores) {
        this.id = id;
        this.tamanho = tamanho;
        this.sabores = sabores;

    }

    public static List<ComidaDTO> toDtoList(List<Comida> comidas) {
        return comidas.stream()
                .map(ComidaConverter::toDto)
                .collect(Collectors.toList());
    }
}
