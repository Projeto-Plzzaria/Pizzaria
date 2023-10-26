package com.pizzaria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.util.List;

@Getter
@Setter
@Entity
@Audited
@AuditTable(value = "comidaAudited")
@Table(name = "comida", schema = "public")
public class Comida extends AbstractEntity {

    @Column(name = "tamanho", nullable = false, length = 10)
    private Tamanho tamanho;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    @Column(name = "sabor",length = 20)
    private List<Sabores> sabores;

    @Column(name = "valor")
    @Getter @Setter
    private Double valor;


    public Comida() {
        // Construtor padrão vazio
    }

}
