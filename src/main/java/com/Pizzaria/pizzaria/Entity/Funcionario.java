package com.Pizzaria.pizzaria.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

@Getter@Setter
@Entity
@Audited
@AuditTable(value = "funcionarioAudited")
@Table(name = "funcionario")
public class Funcionario extends Pessoa {

    @Column(name = "cargo",nullable = false,length = 20)
    private Cargo cargo;
    @Column(name = "email",nullable = false,length = 20)
    private String Email;

}
