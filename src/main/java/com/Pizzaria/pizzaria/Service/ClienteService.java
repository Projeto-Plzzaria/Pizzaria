package com.Pizzaria.pizzaria.Service;


import com.Pizzaria.pizzaria.Entity.Cliente;
import com.Pizzaria.pizzaria.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository Repository;
    public List<Cliente> listartudo(){
        return Repository.findAll();
    }
    @Transactional(rollbackFor = Exception.class)
    public Cliente cadastrar(Cliente cadastrar) {
        Assert.notNull(cadastrar.getNome(), "Error, campo nome vazio");
        return this.Repository.save(cadastrar);
    }

    public Cliente atualizar(Long id, Cliente clienteAtualizado) {
        Cliente clienteExistente = Repository.findById(id).orElse(null);
        if (clienteExistente == null) {
            return null;
        } else {
            clienteExistente.setNome(clienteAtualizado.getNome());
            clienteExistente.setNumero(clienteAtualizado.getNumero());
            return Repository.save(clienteExistente);
        }
    }
}
