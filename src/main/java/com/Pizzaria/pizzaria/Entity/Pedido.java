package com.Pizzaria.pizzaria.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

@Getter
@Setter
@Entity
@Audited
@AuditTable(value = "pedidoAudited", schema = "audited")
@Table(name = "pedido",schema = "public")

public class Pedido extends AbstractEntity  {
    @OneToMany
    @JoinColumn(name = "bebida",nullable = false)
    private Bebida bebida;
    @OneToMany
    @JoinColumn(name = "comida", nullable = false)
    private Comida comida;
    @OneToMany
    @JoinColumn(name = "funcionario",nullable = false)
    private Funcionario funcionario;
    @OneToMany
    @JoinColumn(name = "cliente",nullable = false)
    private Cliente cliente;
}
