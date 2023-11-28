package com.pizzaria.service;

import com.pizzaria.entity.Comida;
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
/*
        if(cadastrar.getBebida() != null)
            for(int i=0; i<cadastrar.getBebida().size(); i++){
                cadastrar.getBebida().get(i)
            }

 */

        return this.pedidoRepository.save(cadastrar);
    }

    public Pedido atualizar(Long id, Pedido pedidoAtualizado) {
        Pedido pedidoExistente = pedidoRepository.findById(id).orElse(null);
        if (pedidoExistente == null) {
            return null;
        } else {
            pedidoExistente.setComida(pedidoAtualizado.getComida());
            pedidoExistente.setBebida(pedidoAtualizado.getBebida());
            pedidoExistente.setFuncionario(pedidoAtualizado.getFuncionario());
            pedidoExistente.setValor(pedidoAtualizado.getValor());
            pedidoExistente.setEndereco(pedidoAtualizado.getEndereco());
            return pedidoRepository.save(pedidoExistente);
        }
    }
}
