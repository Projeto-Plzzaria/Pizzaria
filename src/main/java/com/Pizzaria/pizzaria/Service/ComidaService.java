package com.Pizzaria.pizzaria.Service;

import com.Pizzaria.pizzaria.Entity.Comida;
import com.Pizzaria.pizzaria.Repository.ComidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Comida atualizar(Long id, Comida comidaAtualizada) {
        Comida comidaExistente = Repository.findById(id).orElse(null);
        if (comidaExistente == null) {
            return null;
        } else {
            comidaExistente.setTamanho(comidaAtualizada.getTamanho());
            comidaExistente.setIngredientes(comidaAtualizada.getIngredientes());
            return Repository.save(comidaExistente);
        }
    }
}
