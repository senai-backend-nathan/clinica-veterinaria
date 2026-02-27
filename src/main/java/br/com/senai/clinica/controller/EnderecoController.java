package br.com.senai.clinica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.senai.clinica.entity.Endereco;
import br.com.senai.clinica.exception.Response;
import br.com.senai.clinica.repository.EnderecoRepository;

@RestController
@RequestMapping("/Endereco")
public class EnderecoController {
    @Autowired
    private EnderecoRepository repository;

    @PostMapping
    public Response createEndereco(@RequestBody Endereco endereco) {
        repository.save(endereco);
        return new Response(201, "Endereco criado com sucesso");

    }

    @GetMapping
    public List<Endereco> getAllEnderecos() {
        return repository.findAll();
    }

    @PutMapping("{/id}")
    public Response updateEndereco(@PathVariable Long id, @RequestBody Endereco updated) {
        if (!repository.existsById(id)) {
            return new Response(407, "Endereco não encontrado");
        }

        Endereco endereco = repository.findById(id).get();

        if (updated.getCep() != null) {
            endereco.setCep(updated.getCep());
        }
         if (updated.getLogradouro() != null) {
            endereco.setLogradouro(updated.getLogradouro());
        }
         if (updated.getUf() != null) {
            endereco.setUf(updated.getUf());
        }
         if (updated.getBairro() != null) {
            endereco.setBairro(updated.getBairro());
        }
         if (updated.getNumero() != null) {
            endereco.setNumero(updated.getNumero());
        }
         if (updated.getComplemento() != null) {
            endereco.setComplemento(updated.getComplemento());
        }
         if (updated.getReferencia() != null) {
            endereco.setReferencia(updated.getReferencia());
        
        }
         if (updated.getPrincipal() != null) {
            endereco.setPrincipal(updated.getPrincipal());
        }

        repository.save(endereco);

        return new Response(200, "Endereco atualizado com sucesso");

    }

    @DeleteMapping("/{id}")
    public Response deleteEndereco(@PathVariable Long id) {
        // Verifica existência para retornar um erro amigável se não encontrado.
        if (!repository.existsById(id)) {
            return new Response(404, "Endereco não encontrado");
        }
        // Remove o registro; se ocorrer exceção (ex.: constraint), seria interessante
        // tratar.
        repository.deleteById(id);
        // 204 com mensagem textual aqui é informativo; em APIs REST reais 204 costuma
        // retornar sem corpo.
        return new Response(204, "Endereco deletada com successo");
    }
}
