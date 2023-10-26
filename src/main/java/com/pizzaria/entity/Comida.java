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
@Table(name = "comida",schema = "public")
public class Comida extends AbstractEntity  {


    @Column(name = "tamanho",nullable = false,length = 10)
    private Tamanho tamanho;
    @Column(name = "qidSabores",nullable = false,length = 50)
    private String ingredientes;
    @Column(name = "valor")
    @Getter @Setter
    private Double valor;




    public Comida(Tamanho gigante, String calabresa) {
    //Construtor para teste
    }
    public Comida() {
        // Construtor sem argumentos
    }
    public <T> Comida(String grande, List<T> asList) {
        super();
    }
}
