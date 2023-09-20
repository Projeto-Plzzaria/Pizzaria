package com.pizzaria.service;

import com.pizzaria.entity.Pedido;
import com.pizzaria.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
@Service

public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    public List<Pedido> listartudo(){
        return pedidoRepository.findAll();
    }
    @Transactional(rollbackFor = Exception.class)
    public Pedido cadastrar(Pedido cadastrar) {
        return this.pedidoRepository.save(cadastrar);
    }

    @Transactional(rollbackFor = Exception.class)
    public void atualizar(Long id, Pedido atualizar) {
        final Pedido marcaBanco = this.pedidoRepository.findById(atualizar.getId()).orElse(null);
        Assert.isTrue(marcaBanco.getId().equals(id) ,"Error id da URL diferente do body");
        Assert.isTrue(marcaBanco == null || marcaBanco.getId().equals(atualizar.getId()),"nao identificado o registro informado");
        this.pedidoRepository.save(atualizar);
    }
}