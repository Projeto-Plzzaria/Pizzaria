package com.pizzaria.dto;

import com.pizzaria.entity.Cliente;
import lombok.Getter;
import lombok.Setter;


public class EnderecoDTO {
    @Getter @Setter
    private Long id;
    @Getter @Setter
    private Cliente cliente;
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

    public EnderecoDTO(Long id, Cliente cliente, String clientenome, String rua, int numero, String bairro) {
        this.id = id;
        this.clientenome = clientenome;
        this.cliente = cliente;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
    }
}
