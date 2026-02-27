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

import br.com.senai.clinica.entity.Telefone;
import br.com.senai.clinica.exception.Response;
import br.com.senai.clinica.repository.TelefoneRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/Telefone")
public class TelefoneController {
    @Autowired
    private TelefoneRepository repository;

    @PostMapping
    public Response createTelefone(@Valid @RequestBody Telefone consulta) {
        repository.save(consulta);
        return new Response(201, "Telefone criada com sucesso");

    }

    @GetMapping
    public List<Telefone> getAllTelefones() {
        return repository.findAll();
    }

    @PutMapping("{/id}")
    public Response updateTelefone(@PathVariable Long id, @RequestBody Telefone updated) {
        if (!repository.existsById(id)) {
            return new Response(407, "Telefone não encontrada");
        }

        Telefone consulta = repository.findById(id).get();

        if (updated.getNumero() != null) {
            consulta.setNumero(updated.getNumero());
        }

        repository.save(consulta);

        return new Response(200, "Telefone atualizado com sucesso");

    }

    @DeleteMapping("/{id}")
    public Response deleteTelefone(@PathVariable Long id) {
        // Verifica existência para retornar um erro amigável se não encontrado.
        if (!repository.existsById(id)) {
            return new Response(404, "Telefone não encontrada");
        }
        // Remove o registro; se ocorrer exceção (ex.: constraint), seria interessante
        // tratar.
        repository.deleteById(id);
        // 204 com mensagem textual aqui é informativo; em APIs REST reais 204 costuma
        // retornar sem corpo.
        return new Response(204, "Telefone deletada com successo");
    }
    
}
