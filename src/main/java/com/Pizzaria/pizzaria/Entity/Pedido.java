package com.Pizzaria.pizzaria.Entity;

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
@AuditTable(value = "pedidoAudited")
@Table(name = "pedido",schema = "public")

public class Pedido extends AbstractEntity  {



    @OneToOne
    @JoinColumn(name = "bebida",nullable = false)
    private Bebida bebida;
  
    @OneToOne
    @JoinColumn(name = "comida", nullable = false)
    private Comida comida;
    @OneToOne
    @JoinColumn(name = "funcionario",nullable = false)
    private Funcionario funcionario;
    @OneToOne
    @JoinColumn(name = "cliente",nullable = false)
    private Cliente cliente;

    @Column(name = "valor",nullable = false)
    private Double valor;
}