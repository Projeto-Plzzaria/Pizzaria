package com.pizzaria.service;

import com.pizzaria.entity.Funcionario;
import com.pizzaria.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
@Service

public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;
    public List<Funcionario> listartudo(){
        return funcionarioRepository.findAll();
    }
    @Transactional(rollbackFor = Exception.class)
    public Funcionario cadastrar(Funcionario cadastrar) {
        return this.funcionarioRepository.save(cadastrar);
    }

    @Transactional(rollbackFor = Exception.class)
    public void atualizar(Long id, Funcionario atualizar) {
        final Funcionario marcaBanco = this.funcionarioRepository.findById(atualizar.getId()).orElse(null);
        Assert.isTrue(marcaBanco.getId().equals(id) ,"Error id da URL diferente do body");
        Assert.isTrue(marcaBanco == null || marcaBanco.getId().equals(atualizar.getId()),"nao identificado o registro informado");
        this.funcionarioRepository.save(atualizar);
    }
}
