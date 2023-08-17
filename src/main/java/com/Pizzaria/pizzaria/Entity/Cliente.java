package com.Pizzaria.pizzaria.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

@Entity
@Audited
@AuditTable(value = "clienteAudited")
@Table(name = "cliente",schema = "public")
public class Cliente extends Pessoa {
}
