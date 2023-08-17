package com.Pizzaria.pizzaria.Service;

import com.Pizzaria.pizzaria.Entity.Bebida;
import com.Pizzaria.pizzaria.Entity.Cliente;
import com.Pizzaria.pizzaria.Repository.BebidasRepository;
import com.Pizzaria.pizzaria.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
@Service

public class BebidaService {
    @Autowired
    private BebidasRepository Repository;
    public List<Bebida> listartudo(){
        return Repository.findAll();
    }
    @Transactional(rollbackFor = Exception.class)
    public Bebida cadastrar(Bebida cadastrar) {
        return this.Repository.save(cadastrar);
    }

    @Transactional(rollbackFor = Exception.class)
    public void atualizarum(Long id, Bebida atualizar) {
        final Bebida marcaBanco = this.Repository.findById(atualizar.getId()).orElse(null);
        Assert.isTrue(marcaBanco.getId().equals(id) ,"Error id da URL diferente do body");
        Assert.isTrue(marcaBanco == null || marcaBanco.getId().equals(atualizar.getId()),"nao identificado o registro informado");
        this.Repository.save(atualizar);
    }


    public Bebida atualizar(Long id, Bebida bebidaAtualizada) {
        Bebida bebidaExistente = Repository.findById(id).orElse(null);
        if (bebidaExistente == null) {
            return null;
        } else {
            bebidaExistente.setSabor(bebidaAtualizada.getSabor());
            bebidaExistente.setTamanho(bebidaAtualizada.getTamanho());
            return Repository.save(bebidaExistente);
        }
    }


}
