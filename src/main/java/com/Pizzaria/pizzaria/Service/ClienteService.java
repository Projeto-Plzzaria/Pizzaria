package com.Pizzaria.pizzaria.Service;

import Fozesc.com.demo.Entity.Pessoa;
import Fozesc.com.demo.Repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private PessoaRepository Repository;
    public List<Pessoa> listartudo(){
        return Repository.findAll();
    }
    @Transactional(rollbackFor = Exception.class)
    public Pessoa cadastrar(Pessoa cadastrar) {
        Assert.notNull(cadastrar.getNome(), "Error, campo nome vazio");
        Assert.notNull(cadastrar.getCpfCnpj(), "Error, campo cpf/cnpj vazio");
    //    Assert.notNull(cadastrar.getNDocumento(), "Error, campo doc vazio");
        return this.Repository.save(cadastrar);
    }

    @Transactional(rollbackFor = Exception.class)
    public void atualizar(Long id, Pessoa atualizar) {
        final Pessoa marcaBanco = this.Repository.findById(atualizar.getId()).orElse(null);
        Assert.isTrue(marcaBanco.getId().equals(id) ,"Error id da URL diferente do body");
        Assert.isTrue(marcaBanco == null || marcaBanco.getId().equals(atualizar.getId()),"nao identificado o registro informado");
        this.Repository.save(atualizar);
    }
}
