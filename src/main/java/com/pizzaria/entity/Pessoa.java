package com.pizzaria.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
public abstract class Pessoa extends AbstractEntity {
    @Getter
    @Setter
    @Column(name = "nome",nullable = false,length = 100)
    private String nome;
    @Getter
    @Setter
    @Column(name = "numero",nullable = false,length = 19)
    private String numero;






}
