package com.pizzaria.service;

import com.pizzaria.entity.Funcionario;
import com.pizzaria.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Funcionario atualizar(Long id, Funcionario funcionarioAtualizado) {
        Funcionario funcionarioExistente = funcionarioRepository.findById(id).orElse(null);
        if (funcionarioExistente == null) {
            return null;
        } else {
            funcionarioExistente.setNome(funcionarioAtualizado.getNome());
            funcionarioExistente.setEmail(funcionarioAtualizado.getEmail());
            funcionarioExistente.setNumero(funcionarioAtualizado.getNumero());
            funcionarioExistente.setCargo(funcionarioAtualizado.getCargo());
            return funcionarioRepository.save(funcionarioExistente);
        }
    }





}
