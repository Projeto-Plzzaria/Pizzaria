package com.Pizzaria.pizzaria.DTO;

import com.Pizzaria.pizzaria.Entity.Pedido;
import com.Pizzaria.pizzaria.DTO.PedidoDTO;
public class PedidoConverter {

    public static PedidoDTO toDTO(Pedido pedido) {
        PedidoDTO pedidoDTO = new PedidoDTO();
        pedidoDTO.setBebida(pedido.getBebida());
        pedidoDTO.setComida(pedido.getComida());
        pedidoDTO.setFuncionario(pedido.getFuncionario());
        pedidoDTO.setCliente(pedido.getCliente());
        return pedidoDTO;
    }

    public static Pedido toEntity(PedidoDTO pedidoDTO) {
        Pedido pedido = new Pedido();
        pedido.setBebida(pedidoDTO.getBebida());
        pedido.setComida(pedidoDTO.getComida());
        pedido.setFuncionario(pedidoDTO.getFuncionario());
        pedido.setCliente(pedidoDTO.getCliente());
        return pedido;
    }
}