package com.pizzaria.dto;

import com.pizzaria.entity.*;

import java.util.List;
import java.util.stream.Collectors;

public class PedidoConverter {

    private PedidoConverter() {
        // Construtor vazio privado para nao ter instancias.
    }

    public static PedidoDTO toDTO(Pedido pedido) {
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setBebida((List<Bebida>) pedido.getBebida());
        pedidoDTO.setObs(pedido.getObs());
        pedidoDTO.setComida((List<Comida>) pedido.getComida());
        pedidoDTO.setFuncionario(pedido.getFuncionario());
        pedidoDTO.setEndereco(pedido.getEndereco());
        pedidoDTO.setValor(pedido.getValor());
        pedidoDTO.setId(pedido.getId());
        return pedidoDTO;
    }

    public static Pedido toEntity(PedidoDTO pedidoDTO) {
        Pedido pedido = new Pedido();
        Endereco endereco = new Endereco();
        endereco.setId(pedidoDTO.getId());
        pedido.setEndereco(pedidoDTO.getEndereco());
        pedido.setObs(pedidoDTO.getObs());
        pedido.setBebida((List<Bebida>) pedidoDTO.getBebida());
        pedido.setComida((List<Comida>) pedidoDTO.getComida());
        pedido.setFuncionario(pedidoDTO.getFuncionario());
        pedido.setValor(pedidoDTO.getValor());
        pedido.setId(pedidoDTO.getId());
        return pedido;
    }

    public static List<PedidoDTO> toDTOList(List<Pedido> pedidos) {
        return pedidos.stream()
                .map(PedidoConverter::toDTO)
                .collect(Collectors.toList());
    }
}