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
public class FuncionarioTeste {

    private MockMvc mockMvc;
    @InjectMocks
    private FuncionarioController funcionarioController;

    @Mock
    private FuncionarioService funcionarioService;
    @Mock
    private FuncionarioRepository funcionarioRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(funcionarioController).build();
    }


    @Test
    public void testFuncionario() throws Exception {
        when(funcionarioService.listartudo()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/Funcionario/lista")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }


    @Test
    public void testCadastrarSuccess() throws Exception {
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO();


        when(funcionarioService.cadastrar(any(Funcionario.class)))
                .thenReturn(new Funcionario());

        mockMvc.perform(post("/api/Funcionario/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(funcionarioDTO)))
                .andExpect(status().isOk())
                .andExpect(content().string("Cadastro feito com sucesso"));
    }
    @Test
    public void testListaIdSucesso() throws Exception {
        Long id = 1L;
        Funcionario funcionario = new Funcionario();
        funcionario.setId(id);
        funcionario.setCargo(Cargo.cargos);
        funcionario.setEmail("funcionario@hotmail.com");
        funcionario.setNome("Paulin");

        when(funcionarioRepository.findById(id))
                .thenReturn(Optional.of(funcionario));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/Funcionario/lista/id/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.cargo").value("cargos"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("Paulin"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("funcionario@hotmail.com"));
    }

    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
