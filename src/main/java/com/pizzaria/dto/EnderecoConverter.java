package com.pizzaria.dto;

import com.pizzaria.entity.Cliente;
import com.pizzaria.entity.Endereco;

import java.util.List;
import java.util.stream.Collectors;

public class EnderecoConverter {

    private EnderecoConverter() {
        // Construtor vazio privado para nao ter instancias.
    }

    public static EnderecoDTO toDto(Endereco endereco) {
        EnderecoDTO dto = new EnderecoDTO();
        dto.setId(endereco.getId());
        dto.setCliente(endereco.getCliente());
        dto.setClientenome(endereco.getCliente().getNome());
        dto.setRua(endereco.getRua());
        dto.setNumero(endereco.getNumero());
        dto.setBairro(endereco.getBairro());
        return dto;
    }

    public static Endereco toEntity(EnderecoDTO dto) {
        Endereco endereco = new Endereco();
        Cliente cliente = new Cliente();
        cliente.setId(dto.getId());
        endereco.setCliente(dto.getCliente());
        endereco.setRua(dto.getRua());
        endereco.setClientenome(dto.getClientenome());
        endereco.setNumero(dto.getNumero());
        endereco.setBairro(dto.getBairro());
        return endereco;
    }

    public static List<EnderecoDTO> toDtoList(List<Endereco> enderecos) {
        return enderecos.stream()
                .map(EnderecoConverter::toDto)
                .collect(Collectors.toList());
    }

}
