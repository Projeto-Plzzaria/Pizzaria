package com.Pizzaria.pizzaria.DTO;

import com.Pizzaria.pizzaria.Entity.Cliente;
import com.Pizzaria.pizzaria.Entity.Endereco;

import java.util.List;
import java.util.stream.Collectors;

public class EnderecoConverter {

    public static EnderecoDTO toDto(Endereco endereco) {
        EnderecoDTO dto = new EnderecoDTO();
        dto.setClienteId(endereco.getCliente().getId());
        dto.setRua(endereco.getRua());
        dto.setNumero(endereco.getNumero());
        dto.setBairro(endereco.getBairro());
        return dto;
    }

    public static Endereco toEntity(EnderecoDTO dto) {
        Endereco endereco = new Endereco();
        Cliente cliente = new Cliente();
        cliente.setId(dto.getClienteId());
        endereco.setCliente(cliente);
        endereco.setRua(dto.getRua());
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
