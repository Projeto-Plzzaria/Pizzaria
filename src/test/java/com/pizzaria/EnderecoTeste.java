package com.pizzaria;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzaria.controller.EnderecoController;
import com.pizzaria.dto.EnderecoDTO;
import com.pizzaria.entity.Endereco;
import com.pizzaria.repository.EnderecoRepository;
import com.pizzaria.service.EnderecoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class EnderecoTeste {



    private MockMvc mockMvc;
    @InjectMocks
    private EnderecoController Controller;

    @Mock
    private EnderecoService Service;
    @Mock
    private EnderecoRepository Repository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(Controller).build();
    }

    @Test
    public void testLista() throws Exception {
        when(Service.listartudo()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/Endereco/lista")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
    @Test
    public void testCadastrarSuccess() throws Exception {
        EnderecoDTO DTO = new EnderecoDTO();
        when(Service.cadastrar(any(Endereco.class)))
                .thenReturn(new Endereco());
        mockMvc.perform(post("/api/Endereco/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(DTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("Cadastro feito com sucesso"));
    }
    @Test
    public void testEndereco() {
        Long id = 1L;
        String rua = "Rua Teste";
        int numero = 123;
        String bairro = "Bairro Teste";

        Endereco endereco = new Endereco();
        endereco.setId(id);
        endereco.setRua(rua);
        endereco.setNumero(numero);
        endereco.setBairro(bairro);

        assertEquals(id, endereco.getId());
        assertEquals(rua, endereco.getRua());
        assertEquals(numero, endereco.getNumero());
        assertEquals(bairro, endereco.getBairro());
    }

    @Test
    public void testListaAtivo() {
        boolean ativo = true;
        List<Endereco> Ativas = new ArrayList<>();
        when(Repository.findByAtivo(ativo)).thenReturn(Ativas);
        ResponseEntity<List<EnderecoDTO>> response = Controller.listaAtivo(ativo);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(Ativas.size(), response.getBody().size());
    }

    @Test
    public void testDeleteExistente() {
        Long id = 1L;

        when(Repository.findById(id)).thenReturn(Optional.of(new Endereco()));
        ResponseEntity<String> response = Controller.delete(id);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Apagado com sucesso", response.getBody());

        verify(Repository, times(1)).deleteById(id);
    }

    @Test
    public void testAtualizarComSucesso() {
        Long id = 1L;

        when(Service.atualizar(eq(id), any())).thenReturn(new Endereco());

        EnderecoDTO aDTO = new EnderecoDTO();
        ResponseEntity<?> response = Controller.atualizar(id, aDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Atualizado com sucesso!", response.getBody());
    }


    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}