package com.pizzaria.entity;

import jakarta.persistence.*;
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
