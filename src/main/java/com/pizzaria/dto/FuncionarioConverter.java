package com.pizzaria.dto;



import com.pizzaria.entity.Funcionario;

import java.util.List;
import java.util.stream.Collectors;

public class FuncionarioConverter {

    public static FuncionarioDTO toDTO(Funcionario funcionario) {
        return new FuncionarioDTO(
                funcionario.getNome(),
                funcionario.getCargo()

        );
    }

    public static Funcionario toEntity(FuncionarioDTO funcionarioDTO) {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(funcionarioDTO.getNome());
        funcionario.setCargo(funcionarioDTO.getCargo());
        return funcionario;
    }
    public static List<FuncionarioDTO> toDTOList(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .map(FuncionarioConverter::toDTO)
                .collect(Collectors.toList());
    }
}
