package com.pizzaria.service;

import com.pizzaria.entity.Comida;
import com.pizzaria.repository.ComidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service

public class ComidaService {

    @Autowired
    private ComidaRepository comidaRepository;
    public List<Comida> listartudo(){
        return comidaRepository.findAll();
    }
    @Transactional(rollbackFor = Exception.class)
    public Comida cadastrar(Comida cadastrar) {
        return this.comidaRepository.save(cadastrar);
    }

    public Comida atualizar(Long id, Comida comidaAtualizada) {
        Comida comidaExistente = comidaRepository.findById(id).orElse(null);
        if (comidaExistente == null) {
            return null;
        } else {
            comidaExistente.setTamanho(comidaAtualizada.getTamanho());
            comidaExistente.setIngredientes(comidaAtualizada.getIngredientes());
            return comidaRepository.save(comidaExistente);
        }
    }
}
