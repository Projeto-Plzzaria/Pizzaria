package com.pizzaria.dto;



import com.pizzaria.entity.Funcionario;

import java.util.List;
import java.util.stream.Collectors;

public class FuncionarioConverter {

    private FuncionarioConverter() {
        // Construtor vazio privado para nao ter instancias.
    }

    public static FuncionarioDTO toDTO(Funcionario funcionario) {
        return new FuncionarioDTO(
                funcionario.getId(),
                funcionario.getNome(),
                funcionario.getCargo(),
                funcionario.getEmail(),
                funcionario.getNumero()

        );
    }

    public static Funcionario toEntity(FuncionarioDTO funcionarioDTO) {
        Funcionario funcionario = new Funcionario();
        funcionario.setId(funcionarioDTO.getId());
        funcionario.setNome(funcionarioDTO.getNome());
        funcionario.setCargo(funcionarioDTO.getCargo());
        funcionario.setNumero(funcionarioDTO.getNumero());
        funcionario.setEmail(funcionarioDTO.getEmail());
        return funcionario;
    }
    public static List<FuncionarioDTO> toDTOList(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .map(FuncionarioConverter::toDTO)
                .collect(Collectors.toList());
    }
}
