package com.pizzaria.dto;

import com.pizzaria.entity.Cliente;

import java.util.List;
import java.util.stream.Collectors;

public class ClienteConverter {

    private ClienteConverter() {
        // Construtor vazio privado para nao ter instancias.
    }

    public static ClienteDTO toDto(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());
        dto.setNumero(cliente.getNumero());
        dto.setRua(cliente.getRua());
        dto.setNum(cliente.getNum());
        dto.setBairro(cliente.getBairro());
        return dto;
    }

    public static Cliente toEntity(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setId(dto.getId());
        cliente.setNome(dto.getNome());
        cliente.setNumero(dto.getNumero());
        cliente.setRua(dto.getRua());
        cliente.setNum(dto.getNum());
        cliente.setBairro(dto.getBairro());
        return cliente;
    }
    public static List<ClienteDTO> toDtoList(List<Cliente> clientes) {
        return clientes.stream()
                .map(ClienteConverter::toDto)
                .collect(Collectors.toList());
    }
}
