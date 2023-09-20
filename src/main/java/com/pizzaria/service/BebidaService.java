package com.pizzaria.service;

import com.pizzaria.entity.Bebida;
import com.pizzaria.repository.BebidasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
@Service

public class BebidaService {
    @Autowired
    private BebidasRepository bebidasRepository;

    public List<Bebida> listartudo(){
        return bebidasRepository.findAll();
    }
    @Transactional(rollbackFor = Exception.class)
    public Bebida cadastrar(Bebida cadastrar) {
        return this.bebidasRepository.save(cadastrar);
    }

    @Transactional(rollbackFor = Exception.class)
    public void atualizarum(Long id, Bebida atualizar) {
        final Bebida marcaBanco = this.bebidasRepository.findById(atualizar.getId()).orElse(null);
        Assert.isTrue(marcaBanco.getId().equals(id) ,"Error id da URL diferente do body");
        Assert.isTrue(marcaBanco == null || marcaBanco.getId().equals(atualizar.getId()),"nao identificado o registro informado");
        this.bebidasRepository.save(atualizar);
    }

    public Bebida atualizar(Long id, Bebida bebidaAtualizada) {
        Bebida bebidaExistente = bebidasRepository.findById(id).orElse(null);
        if (bebidaExistente == null) {
            return null;
        } else {
            bebidaExistente.setSabor(bebidaAtualizada.getSabor());
            bebidaExistente.setTamanho(bebidaAtualizada.getTamanho());
            return bebidasRepository.save(bebidaExistente);
        }
    }
}
