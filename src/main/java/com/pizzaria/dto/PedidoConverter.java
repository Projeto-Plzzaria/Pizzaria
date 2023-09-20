package com.pizzaria.dto;

import com.pizzaria.entity.Pedido;

import java.util.List;
import java.util.stream.Collectors;

public class PedidoConverter {

    public static PedidoDTO toDTO(Pedido pedido) {
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setBebida(pedido.getBebida());
        pedidoDTO.setComida(pedido.getComida());
        pedidoDTO.setFuncionario(pedido.getFuncionario());
        pedidoDTO.setCliente(pedido.getCliente());
        pedidoDTO.setValor(pedido.getValor());
        return pedidoDTO;
    }

    public static Pedido toEntity(PedidoDTO pedidoDTO) {
        Pedido pedido = new Pedido();
        pedido.setBebida(pedidoDTO.getBebida());
        pedido.setComida(pedidoDTO.getComida());
        pedido.setFuncionario(pedidoDTO.getFuncionario());
        pedido.setCliente(pedidoDTO.getCliente());
        pedido.setValor(pedidoDTO.getValor()); // Corrigido aqui
        return pedido;
    }

    public static List<PedidoDTO> toDTOList(List<Pedido> pedidos) {
        return pedidos.stream()
                .map(PedidoConverter::toDTO)
                .collect(Collectors.toList());
    }
}