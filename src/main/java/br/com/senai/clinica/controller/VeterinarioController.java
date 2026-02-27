package br.com.senai.clinica.controller;

import org.springframework.web.bind.annotation.RestController;

import br.com.senai.clinica.entity.Veterinario;
import br.com.senai.clinica.exception.Response;
import br.com.senai.clinica.repository.VeterinarioRepository;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequestMapping
("/Veterinario")
public class VeterinarioController {
    @Autowired
    private VeterinarioRepository repository;

    @PostMapping
    public Response createVeterinario(@Valid @RequestBody Veterinario veterinario) {
        repository.save(veterinario);
        return new Response(201, "Veterinario criada com sucesso");

    }

    @GetMapping
    public List<Veterinario> getAllVeterinarios() {
        return repository.findAll();
    }

    @PutMapping("{/id}")
    public Response updateVeterinario(@PathVariable Long id, @RequestBody Veterinario updated) {
        if (!repository.existsById(id)) {
            return new Response(407, "Veterinario não encontrada");
        }

        Veterinario veterinario = repository.findById(id).get();

        if (updated.getCrmv() != null) {
            veterinario.setCrmv(updated.getCrmv());
        }

        repository.save(veterinario);

        return new Response(200, "Veterinario atualizado com sucesso");

    }

    @DeleteMapping("/{id}")
    public Response deleteVeterinario(@PathVariable Long id) {
        // Verifica existência para retornar um erro amigável se não encontrado.
        if (!repository.existsById(id)) {
            return new Response(404, "Veterinario não encontrado");
        }
        // Remove o registro; se ocorrer exceção (ex.: constraint), seria interessante
        // tratar.
        repository.deleteById(id);
        // 204 com mensagem textual aqui é informativo; em APIs REST reais 204 costuma
        // retornar sem corpo.
        return new Response(204, "Veterinario deletado com successo");
    }
}
