package com.Pizzaria.pizzaria.Service;

import com.Pizzaria.pizzaria.Entity.Bebida;
import com.Pizzaria.pizzaria.Entity.Comida;
import com.Pizzaria.pizzaria.Repository.BebidasRepository;
import com.Pizzaria.pizzaria.Repository.ComidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
@Service

public class ComidaService {

    @Autowired
    private ComidaRepository Repository;
    public List<Comida> listartudo(){
        return Repository.findAll();
    }
    @Transactional(rollbackFor = Exception.class)
    public Comida cadastrar(Comida cadastrar) {
        return this.Repository.save(cadastrar);
    }

    @Transactional(rollbackFor = Exception.class)
    public void atualizar(Long id, Comida atualizar) {
        final Comida marcaBanco = this.Repository.findById(atualizar.getId()).orElse(null);
        Assert.isTrue(marcaBanco.getId().equals(id) ,"Error id da URL diferente do body");
        Assert.isTrue(marcaBanco == null || marcaBanco.getId().equals(atualizar.getId()),"nao identificado o registro informado");
        this.Repository.save(atualizar);
    }
}
