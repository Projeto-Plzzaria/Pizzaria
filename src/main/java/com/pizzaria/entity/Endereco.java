package com.pizzaria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

@Getter
@Setter
@Entity
@Audited
@AuditTable(value = "enderecoAudited")
@Table(name = "endereco",schema = "public")

public class Endereco extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "cliente", nullable = false)
    private Cliente cliente;
    @Column(name = "rua",nullable = false,length = 50)
    private String rua;

    @Column(name = "numero",nullable = false)
    private int numero;

    @Column(name = "bairro",nullable = false,length = 30)
    private String bairro;

}
