package com.Pizzaria.pizzaria.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

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
