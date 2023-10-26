package com.pizzaria.dto;

import lombok.Getter;
import lombok.Setter;


public class EnderecoDTO {
    @Getter @Setter
    private Long id;
    @Getter @Setter
    private Long clienteId;
    @Getter @Setter
    private String clientenome;
    @Getter @Setter
    private String rua;
    @Getter @Setter
    private int numero;
    @Getter @Setter
    private String bairro;

    public EnderecoDTO() {
    }

    public EnderecoDTO(Long id, Long clienteId, String clientenome, String rua, int numero, String bairro) {
        this.id = id;
        this.clientenome = clientenome;
        this.clienteId = clienteId;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
    }
}
