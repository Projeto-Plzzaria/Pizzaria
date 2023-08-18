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

    @OneToMany(mappedBy = "pedido_bebidas", cascade = CascadeType.ALL)
    private List<Bebida> bebidas;
    @OneToMany(mappedBy = "pedido_comidas", cascade = CascadeType.ALL)
    private List<Comida> comida;
    @OneToOne
    @JoinColumn(name = "funcionario",nullable = false)
    private Funcionario funcionario;
    @OneToOne
    @JoinColumn(name = "cliente",nullable = false)
    private Cliente cliente;

}
