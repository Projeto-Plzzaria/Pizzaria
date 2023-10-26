package com.pizzaria.dto;

import com.pizzaria.entity.Bebida;
import com.pizzaria.entity.Comida;
import com.pizzaria.entity.Pedido;

import java.util.List;
import java.util.stream.Collectors;

public class PedidoConverter {

    private PedidoConverter() {
        // Construtor vazio privado para nao ter instancias.
    }

    public static PedidoDTO toDTO(Pedido pedido) {
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setBebida((List<Bebida>) pedido.getBebida());
        pedidoDTO.setComida((List<Comida>) pedido.getComida());
        pedidoDTO.setFuncionario(pedido.getFuncionario());
        pedidoDTO.setCliente(pedido.getCliente());
        pedidoDTO.setValor(pedido.getValor());
        pedidoDTO.setId(pedido.getId());
        pedidoDTO.setObs(pedido.getObs());
        return pedidoDTO;
    }

    public static Pedido toEntity(PedidoDTO pedidoDTO) {
        Pedido pedido = new Pedido();
        pedido.setBebida((List<Bebida>) pedidoDTO.getBebida());
        pedido.setComida((List<Comida>) pedidoDTO.getComida());
        pedido.setFuncionario(pedidoDTO.getFuncionario());
        pedido.setCliente(pedidoDTO.getCliente());
        pedido.setValor(pedidoDTO.getValor());
        pedido.setId(pedidoDTO.getId());
        pedido.setObs(pedidoDTO.getObs());
        return pedido;
    }

    public static List<PedidoDTO> toDTOList(List<Pedido> pedidos) {
        return pedidos.stream()
                .map(PedidoConverter::toDTO)
                .collect(Collectors.toList());
    }
}