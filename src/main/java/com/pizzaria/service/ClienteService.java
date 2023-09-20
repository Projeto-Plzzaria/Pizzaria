package com.pizzaria.service;


import com.pizzaria.entity.Cliente;
import com.pizzaria.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    public List<Cliente> listartudo(){
        return clienteRepository.findAll();
    }
    @Transactional(rollbackFor = Exception.class)
    public Cliente cadastrar(Cliente cadastrar) {
        Assert.notNull(cadastrar.getNome(), "Error, campo nome vazio");
        return this.clienteRepository.save(cadastrar);
    }

    public Cliente atualizar(Long id, Cliente clienteAtualizado) {
        Cliente clienteExistente = clienteRepository.findById(id).orElse(null);
        if (clienteExistente == null) {
            return null;
        } else {
            clienteExistente.setNome(clienteAtualizado.getNome());
            clienteExistente.setNumero(clienteAtualizado.getNumero());
            return clienteRepository.save(clienteExistente);
        }
    }
}
