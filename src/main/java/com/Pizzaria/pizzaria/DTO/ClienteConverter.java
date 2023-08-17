package com.Pizzaria.pizzaria.DTO;

import com.Pizzaria.pizzaria.Entity.Cliente;

import java.util.List;
import java.util.stream.Collectors;

public class ClienteConverter {

    public static ClienteDTO toDto(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setNome(cliente.getNome());
        dto.setNumero(cliente.getNumero());
        return dto;
    }

    public static Cliente toEntity(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setNumero(dto.getNumero());
        return cliente;
    }
    public static List<ClienteDTO> toDtoList(List<Cliente> clientes) {
        return clientes.stream()
                .map(ClienteConverter::toDto)
                .collect(Collectors.toList());
    }
}
