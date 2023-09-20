package com.pizzaria.dto;

import lombok.Getter;
import lombok.Setter;


public class EnderecoDTO {
    @Getter @Setter
    private Long clienteId;
    @Getter @Setter
    private String rua;
    @Getter @Setter
    private int numero;
    @Getter @Setter
    private String bairro;

    public EnderecoDTO() {
    }

    public EnderecoDTO(Long clienteId, String rua, int numero, String bairro) {
        this.clienteId = clienteId;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
    }
}
