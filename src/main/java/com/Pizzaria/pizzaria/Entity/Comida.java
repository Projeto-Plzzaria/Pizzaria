package com.Pizzaria.pizzaria.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

@Getter
@Setter
@Entity
@Audited
@AuditTable(value = "comidaAudited")
@Table(name = "comida",schema = "public")
public class Comida extends AbstractEntity  {
    @Column(name = "tamanho",nullable = false)
    private int tamanho;
    @Column(name = "qidSabores",nullable = false,length = 50)
    private int  qidSabores;
    @Column(name = "addIngrediente",nullable = false,length = 50)
    private int addIngrediente;
}
