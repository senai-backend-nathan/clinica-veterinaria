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

import br.com.senai.clinica.entity.Consulta;
import br.com.senai.clinica.exception.Response;

import br.com.senai.clinica.repository.ConsultaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/Consulta")
public class ConsultaController {

    @Autowired
    private ConsultaRepository repository;

    @PostMapping
    public Response createConsulta(@Valid @RequestBody Consulta consulta) {
        repository.save(consulta);
        return new Response(201, "Consulta criada com sucesso");

    }

    @GetMapping
    public List<Consulta> getAllConsultas() {
        return repository.findAll();
    }

    @PutMapping("{/id}")
    public Response updateConsulta(@PathVariable Long id, @RequestBody Consulta updated) {
        if (!repository.existsById(id)) {
            return new Response(407, "Consulta não encontrada");
        }

        Consulta consulta = repository.findById(id).get();

        if (updated.getData_hora() != null) {
            consulta.setData_hora(updated.getData_hora());
        }

        repository.save(consulta);

        return new Response(200, "Consulta atualizado com sucesso");

    }

    @DeleteMapping("/{id}")
    public Response deleteConsulta(@PathVariable Long id) {
        // Verifica existência para retornar um erro amigável se não encontrado.
        if (!repository.existsById(id)) {
            return new Response(404, "Consulta não encontrada");
        }
        // Remove o registro; se ocorrer exceção (ex.: constraint), seria interessante
        // tratar.
        repository.deleteById(id);
        // 204 com mensagem textual aqui é informativo; em APIs REST reais 204 costuma
        // retornar sem corpo.
        return new Response(204, "Consulta deletada com successo");
    }
}
