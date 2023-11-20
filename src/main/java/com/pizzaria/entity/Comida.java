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
@AuditTable(value = "comidaAudited")
@Table(name = "comida", schema = "public")
public class Comida extends AbstractEntity {

    @Column(name = "tamanho", nullable = false, length = 10)
    private Tamanho tamanho;

    @Column(name = "saborum", nullable = false)
    private Saborum saborum;

    @Column(name = "sabordois")
    private Sabordois sabordois;

    @Column(name = "sabortres")
    private Sabortres sabortres;

    @Column(name = "valor")
    private Double valor;


    public Comida() {
        // Construtor padrão vazio
    }

}
