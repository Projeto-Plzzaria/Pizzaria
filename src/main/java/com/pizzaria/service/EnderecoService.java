package com.pizzaria.service;

import com.pizzaria.entity.Endereco;
import com.pizzaria.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service

public class EnderecoService {
    @Autowired
    private EnderecoRepository Repository;
    public List<Endereco> listartudo(){
        return Repository.findAll();
    }
    @Transactional(rollbackFor = Exception.class)
    public Endereco cadastrar(Endereco cadastrar) {
        return this.Repository.save(cadastrar);
    }

    public Endereco atualizar(Long id, Endereco enderecoAtualizado) {
        Endereco enderecoExistente = Repository.findById(id).orElse(null);
        if (enderecoExistente == null) {
            return null;
        } else {
            enderecoExistente.setCliente(enderecoAtualizado.getCliente());
            enderecoExistente.setRua(enderecoAtualizado.getRua());
            enderecoExistente.setNumero(enderecoAtualizado.getNumero());
            enderecoExistente.setBairro(enderecoAtualizado.getBairro());
            return Repository.save(enderecoExistente);
        }
    }
}
