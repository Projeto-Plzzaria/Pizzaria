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
    @JoinColumn(name = "cliente")
    private Cliente cliente;
    @Column(name = "rua", length = 50)
    private String rua;

    @Column(name = "clientenome",length = 50)
    private String clientenome;

    @Column(name = "numero")
    private int numero;

    @Column(name = "bairro", length = 30)
    private String bairro;

}
