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
@Audited
@AuditTable(value = "clienteAudited")
@Table(name = "cliente",schema = "public")
public class Cliente extends Pessoa {

    @OneToMany(mappedBy = "cliente")
    @Getter@Setter
    private List<Endereco> enderecos = new ArrayList<>();



}
