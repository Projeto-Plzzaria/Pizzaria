package com.pizzaria;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzaria.dto.BebidaDTO;
import com.pizzaria.dto.ComidaDTO;
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
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@WebAppConfiguration
class PedidoTest {
    private MockMvc mockMvc;
    @InjectMocks
    private PedidoController pedidoController;

    @Mock
    private PedidoService pedidoService;
    @Mock
    private PedidoRepository pedidoRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(pedidoController).build();
    }

    @Test
    void testPedido() throws Exception {
        when(pedidoService.listartudo()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/Pedido/lista")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testCadastrarSuccess() throws Exception {
        PedidoDTO pedidoDTO = new PedidoDTO();


        when(pedidoService.cadastrar(any(Pedido.class)))
                .thenReturn(new Pedido());

        mockMvc.perform(post("/api/Pedido/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(pedidoDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("Cadastro feito com sucesso"));
    }
    //////////
    @Test
    void testCadastrarIllegalArgumentException() throws Exception {
        BebidaDTO bebidaDTO = new BebidaDTO();
        when(pedidoService.cadastrar(any(Pedido.class)))
                .thenThrow(new IllegalArgumentException("Argumento inválido"));

        mockMvc.perform(post("/api/Pedido/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(bebidaDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("ERRO: Argumento inválido"));
    }
    @Test
    void testListaIdSucesso() throws Exception {
        Long id = 1L;
        Bebida bebida = new Bebida(TamanhoB.L_1, "Laranja");
        bebida.setId(id);
        bebida.setTamanho(TamanhoB.L_1);
        bebida.setSabor("Cola");

        Comida comida = new Comida(Tamanho.GIGANTE, "Calabresa");
        comida.setId(id);
        comida.setTamanho(Tamanho.GIGANTE);

        Funcionario funcionario = new Funcionario();
        funcionario.setId(id);
        funcionario.setCargo(Cargo.CARGOS);
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
                .andExpect(MockMvcResultMatchers.jsonPath("$.funcionario.cargo").value("CARGOS"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.funcionario.email").value("funcionario@hotmail.com"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cliente.nome").value("Mauricio"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cliente.numero").value("45998874502"));
    }
    //////////
    @Test
    void testListaIdNaoEncontrado() throws Exception {
        Long id = 1L;
        when(pedidoRepository.findById(id))
                .thenReturn(Optional.empty());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/Pedido/lista/id/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    @Test
    void testDeleteExistente() {
        Long id = 1L;

        when(pedidoRepository.findById(id)).thenReturn(Optional.of(new Pedido()));
        ResponseEntity<String> response = pedidoController.delete(id);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Apagado com sucesso", response.getBody());

        verify(pedidoRepository, times(1)).deleteById(id);
    }
//////////
    @Test
    void testListaAtivo() {
        boolean ativo = true;
        List<Pedido> Ativas = new ArrayList<>();
        when(pedidoRepository.findByAtivo(ativo)).thenReturn(Ativas);
        ResponseEntity<List<PedidoDTO>> response = pedidoController.listaAtivo(ativo);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(Ativas.size(), response.getBody().size());
    }

    @Test
    void testAtualizarComExcecao() {
        Long id = 1L;
        PedidoDTO pedidoDTO = new PedidoDTO();

        when(pedidoService.atualizar(eq(id), any()))
                .thenThrow(new RuntimeException("Erro na atualização"));

        ResponseEntity<?> response = pedidoController.atualizar(id, pedidoDTO);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Erro na atualização", response.getBody());
    }




    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }












}
