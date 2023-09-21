package com.pizzaria;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzaria.dto.PedidoDTO;
import com.pizzaria.entity.*;
import com.pizzaria.repository.PedidoRepository;
import com.pizzaria.controller.PedidoController;
import com.pizzaria.service.PedidoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@WebAppConfiguration
public class PedidoTeste {
    private MockMvc mockMvc;
    @InjectMocks
    private PedidoController pedidoController;

    @Mock
    private PedidoService pedidoService;
    @Mock
    private PedidoRepository pedidoRepository;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(pedidoController).build();
    }

    @Test
    public void testPedido() throws Exception {
        when(pedidoService.listartudo()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/Pedido/lista")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testCadastrarSuccess() throws Exception {
        PedidoDTO pedidoDTO = new PedidoDTO();


        when(pedidoService.cadastrar(any(Pedido.class)))
                .thenReturn(new Pedido());

        mockMvc.perform(post("/api/Pedido/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(pedidoDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("Cadastro feito com sucesso"));
    }
    @Test
    public void testListaIdSucesso() throws Exception {
        Long id = 1L;
        Bebida bebida = new Bebida(TamanhoB.L_1, "Laranja");
        bebida.setId(id);
        bebida.setTamanho(TamanhoB.L_1);
        bebida.setSabor("Cola");

        Comida comida = new Comida();
        comida.setId(id);
        comida.setTamanho(Tamanho.GIGANTE);

        Funcionario funcionario = new Funcionario();
        funcionario.setId(id);
        funcionario.setCargo(Cargo.cargos);
        funcionario.setEmail("funcionario@hotmail.com");

        Cliente cliente = new Cliente();
        cliente.setId(id);
        cliente.setNome("Mauricio");
        cliente.setNumero("45998874502");


        Pedido pedido = new Pedido();
        pedido.setId(id);
        pedido.setBebida(bebida);
        pedido.setComida(comida);
        pedido.setFuncionario(funcionario);
        pedido.setCliente(cliente);
        pedido.setValor(68.0);

        when(pedidoRepository.findById(id))
                .thenReturn(Optional.of(pedido));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/Pedido/lista/id/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.bebida.tamanho").value("L_1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bebida.sabor").value("Cola"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.comida.tamanho").value("GIGANTE"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.funcionario.cargo").value("cargos"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.funcionario.email").value("funcionario@hotmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cliente.nome").value("Mauricio"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cliente.numero").value("45998874502"));




    }

    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }












}
