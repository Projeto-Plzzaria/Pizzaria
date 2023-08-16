package com.Pizzaria.pizzaria.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

@Entity
@Audited
@AuditTable(value = "funcionarioAudited", schema = "audited")
@Table(name = "funcionario",schema = "public")
public class Bebida extends AbstractEntity {
    @Column(name = "tamanho",nullable = false)
    private int tamanho;
    @Column(name = "sabor",nullable = false,length = 10)
    private String sabor;
}
