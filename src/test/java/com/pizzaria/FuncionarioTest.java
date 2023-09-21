package com.pizzaria;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pizzaria.controller.FuncionarioController;

import com.pizzaria.dto.FuncionarioDTO;
import com.pizzaria.entity.Cargo;

import com.pizzaria.entity.Funcionario;
import com.pizzaria.repository.FuncionarioRepository;
import com.pizzaria.service.FuncionarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@WebAppConfiguration
class FuncionarioTest {

    private MockMvc mockMvc;
    @InjectMocks
    private FuncionarioController funcionarioController;

    @Mock
    private FuncionarioService funcionarioService;
    @Mock
    private FuncionarioRepository funcionarioRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(funcionarioController).build();
    }


    @Test
    void testFuncionario() throws Exception {
        when(funcionarioService.listartudo()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/Funcionario/lista")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }


    @Test
    void testCadastrarSuccess() throws Exception {
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO();


        when(funcionarioService.cadastrar(any(Funcionario.class)))
                .thenReturn(new Funcionario());

        mockMvc.perform(post("/api/Funcionario/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(funcionarioDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("Cadastro feito com sucesso"));
    }
    ///
    @Test
    void testCadastrarDataIntegrityViolationException() throws Exception {
        FuncionarioDTO bebidaDTO = new FuncionarioDTO();
        when(funcionarioService.cadastrar(any(Funcionario.class)))
                .thenThrow(new DataIntegrityViolationException("Erro de violação de integridade"));

        mockMvc.perform(post("/api/Funcionario/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(bebidaDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("ERRO: Erro de violação de integridade"));
    }
    @Test
    void testCadastrarIllegalArgumentException() throws Exception {
        FuncionarioDTO bebidaDTO = new FuncionarioDTO();
        when(funcionarioService.cadastrar(any(Funcionario.class)))
                .thenThrow(new IllegalArgumentException("Argumento inválido"));

        mockMvc.perform(post("/api/Funcionario/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(bebidaDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("ERRO: Argumento inválido"));
    }
    ///
    @Test
    void testListaIdSucesso() throws Exception {
        Long id = 1L;
        Funcionario funcionario = new Funcionario();
        funcionario.setId(id);
        funcionario.setCargo(Cargo.CARGOS);
        funcionario.setEmail("funcionario@hotmail.com");
        funcionario.setNome("Paulin");

        when(funcionarioRepository.findById(id))
                .thenReturn(Optional.of(funcionario));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/Funcionario/lista/id/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.cargo").value("CARGOS"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Paulin"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("funcionario@hotmail.com"));
    }
    ///
    @Test
    void testListaIdNaoEncontrado() throws Exception {
        Long id = 1L;
        when(funcionarioRepository.findById(id))
                .thenReturn(Optional.empty());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/Funcionario/lista/id/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
    ///
    @Test
    void testDeleteExistente() {
        Long id = 1L;

        when(funcionarioRepository.findById(id)).thenReturn(Optional.of(new Funcionario()));
        ResponseEntity<String> response = funcionarioController.delete(id);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Apagado com sucesso", response.getBody());

        verify(funcionarioRepository, times(1)).deleteById(id);
    }
    ///
    @Test
    void testDeleteIdNaoEncontrado() throws Exception {
        Long id = 2L;

        when(funcionarioRepository.findById(id))
                .thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/Funcionario/delete/{id}", id))
                .andExpect(status().isNotFound());

        verify(funcionarioRepository, times(1)).findById(id);
        verify(funcionarioRepository, never()).deleteById(id);
    }
    ////
    @Test
    void testAtualizarComSucesso() {
        Long id = 1L;

        when(funcionarioService.atualizar(eq(id), any())).thenReturn(new Funcionario());

        FuncionarioDTO aDTO = new FuncionarioDTO();
        ResponseEntity<?> response = funcionarioController.atualizar(id, aDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Atualizado com sucesso!", response.getBody());
    }
    ////
    @Test
    void testAtualizarComExcecao() {
        Long id = 1L;
        FuncionarioDTO bebidaDTO = new FuncionarioDTO();

        when(funcionarioService.atualizar(eq(id), any()))
                .thenThrow(new RuntimeException("Erro na atualização"));

        ResponseEntity<?> response = funcionarioController.atualizar(id, bebidaDTO);
        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Erro na atualização", response.getBody());
    }

    ///
    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}