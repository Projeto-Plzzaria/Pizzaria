package com.Pizzaria.pizzaria.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@MappedSuperclass
public abstract class AbstractEntity {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",nullable=false,unique=true)
    private Long id;
    @Getter
    @Setter
    @Column(name = "ativo",nullable = false)
    private  boolean ativo;
    @Getter@Setter
    @Column(name = "cadastro",nullable = false)

    private LocalDateTime cadastro;
    @Getter@Setter
    @Column(name = "edicao")

    private LocalDateTime edicao;
    @PrePersist
    private void prePersist(){
        this.cadastro = LocalDateTime.now();
        this.ativo = true;
    }
    @PreUpdate
    private void preUpdate(){
        this.edicao = LocalDateTime.now();
    }
}

