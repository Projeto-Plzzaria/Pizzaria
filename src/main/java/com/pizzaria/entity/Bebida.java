package com.pizzaria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

@Entity
@Audited
@AuditTable(value = "bebidaAudited")
@Table(name = "bebida",schema = "public")
public class Bebida extends AbstractEntity {

    @Column(name = "tamanho",nullable = false)
    @Getter @Setter
    private TamanhoB tamanho;
    @Column(name = "sabor")
    @Getter @Setter
    private String sabor;

    @Column(name = "valor")
    @Getter @Setter
    private Double valor;

    public Bebida() {
        super();
    }

    public Bebida(TamanhoB l1, String laranja) {
        //Somente para os testes
    }
}
