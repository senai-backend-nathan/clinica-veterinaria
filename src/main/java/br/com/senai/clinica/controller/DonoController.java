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

import br.com.senai.clinica.entity.Dono;

import br.com.senai.clinica.exception.Response;
import br.com.senai.clinica.repository.DonoRepository;

@RestController
@RequestMapping("/Dono")
public class DonoController {
     @Autowired
    private DonoRepository repository;

    @PostMapping
    public Response createDono(@RequestBody Dono dono) {
        repository.save(dono);
        return new Response(201, "Dono criado com sucesso");

    }

    @GetMapping
    public List<Dono> getAllDonos() {
        return repository.findAll();
    }

    @PutMapping("{/id}")
    public Response updateDono(@PathVariable Long id, @RequestBody Dono updated) {
        if (!repository.existsById(id)) {
            return new Response(407, "Dono não encontrado");
        }

        Dono dono = repository.findById(id).get();

        if (updated.getNome() != null) {
            dono.setNome(updated.getNome());
        }
         if (updated.getCpf() != null) {
            dono.setCpf(updated.getCpf());
        }
         if (updated.getNome() != null) {
            dono.setStatus(updated.getStatus());
        }

        repository.save(dono);

        return new Response(200, "Dono atualizado com sucesso");

    }

    @DeleteMapping("/{id}")
    public Response deleteDono(@PathVariable Long id) {
        // Verifica existência para retornar um erro amigável se não encontrado.
        if (!repository.existsById(id)) {
            return new Response(404, "Dono não encontrado");
        }
        // Remove o registro; se ocorrer exceção (ex.: constraint), seria interessante
        // tratar.
        repository.deleteById(id);
        // 204 com mensagem textual aqui é informativo; em APIs REST reais 204 costuma
        // retornar sem corpo.
        return new Response(204, "Dono deletado com successo");
    }
}
