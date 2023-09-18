package com.Pizzaria.pizzaria.Controller;

import com.Pizzaria.pizzaria.DTO.BebidaConverter;
import com.Pizzaria.pizzaria.DTO.BebidaDTO;
import com.Pizzaria.pizzaria.Entity.Bebida;
import com.Pizzaria.pizzaria.Repository.BebidasRepository;
import com.Pizzaria.pizzaria.Service.BebidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/api/Bebida")
public class BebidaController {
    @Autowired
    private BebidasRepository Repository;
    @Autowired
    private BebidaService Service;



    @GetMapping("/lista")
    public ResponseEntity<List<BebidaDTO>> lista() {
        List<Bebida> listaBebidas = Service.listartudo();
        List<BebidaDTO> listaBebidasDTO = BebidaConverter.toDtoList(listaBebidas);
        return ResponseEntity.ok(listaBebidasDTO);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Bebida>> listas(){
        List<Bebida> listartudo = Service.listartudo();
        return ResponseEntity.ok(listartudo);
    }

    @GetMapping("/lista/id/{id}")
    public ResponseEntity<?> listaId(@PathVariable(value = "id") Long id) {
        Bebida bebida = Repository.findById(id).orElse(null);
        if (bebida == null) {
            return ResponseEntity.badRequest().body(" <<ERRO>>: valor não encontrado.");
        }
        BebidaDTO bebidaDTO = BebidaConverter.toDto(bebida);
        return ResponseEntity.ok(bebidaDTO);
    }

    @GetMapping("/lista/ativo/{ativo}")
    public ResponseEntity<List<BebidaDTO>> listaAtivo(@PathVariable boolean ativo) {
        List<Bebida> listaAtivo = Repository.findByAtivo(ativo);
        List<BebidaDTO> listaAtivoDTO = BebidaConverter.toDtoList(listaAtivo);

        return ResponseEntity.ok(listaAtivoDTO);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody BebidaDTO cadastroDTO) {
        try {
            Bebida bebida = BebidaConverter.toEntity(cadastroDTO);
            this.Service.cadastrar(bebida);
            return ResponseEntity.ok("Cadastro feito com sucesso");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("ERRO: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("ERRO: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        Optional<Bebida> deletarId = Repository.findById(id);
        if (deletarId.isPresent()) {
            Repository.deleteById(id);
            return ResponseEntity.ok("Apagado com sucesso");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/put/id/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody BebidaDTO dto) {
        try {
            Bebida bebidaAtualizada = BebidaConverter.toEntity(dto);
            this.Service.atualizar(id, bebidaAtualizada);
            return ResponseEntity.ok().body("Atualizado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
