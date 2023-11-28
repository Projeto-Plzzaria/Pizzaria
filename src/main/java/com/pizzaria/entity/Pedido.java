package com.pizzaria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
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



    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "comida_produto",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id"))
    private List<Comida> comida;
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = " bebida_Produto",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "produto2_id"))
    private List<Bebida> bebida;
    @ManyToOne
    @JoinColumn(name = "funcionario")
    private Funcionario funcionario;
    @ManyToOne
    @JoinColumn(name = "cliente")
    private Cliente cliente;
    @Column(name = "obs", length = 100)
    private String obs;

    @Column(name = "valor")
    private Double valor;

}