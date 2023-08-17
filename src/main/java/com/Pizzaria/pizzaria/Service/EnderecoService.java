package com.Pizzaria.pizzaria.Service;

import com.Pizzaria.pizzaria.Entity.Endereco;
import com.Pizzaria.pizzaria.Entity.Funcionario;
import com.Pizzaria.pizzaria.Repository.EnderecoRepository;
import com.Pizzaria.pizzaria.Repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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

    @Transactional(rollbackFor = Exception.class)
    public void atualizar(Long id, Endereco atualizar) {
        final Endereco marcaBanco = this.Repository.findById(atualizar.getId()).orElse(null);
        Assert.isTrue(marcaBanco.getId().equals(id) ,"Error id da URL diferente do body");
        Assert.isTrue(marcaBanco == null || marcaBanco.getId().equals(atualizar.getId()),"nao identificado o registro informado");
        this.Repository.save(atualizar);
    }
}
