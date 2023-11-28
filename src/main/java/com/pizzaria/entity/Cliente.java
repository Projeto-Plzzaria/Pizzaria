package com.pizzaria.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter@Setter
@Audited
@AuditTable(value = "clienteAudited")
@Table(name = "cliente",schema = "public")
public class Cliente extends Pessoa {

    @Column(name = "rua", length = 50)
    private String rua;

    @Column(name = "num")
    private int num;

    @Column(name = "bairro", length = 30)
    private String bairro;

}
