package br.com.senai.clinica.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.senai.clinica.entity.Animal;
import br.com.senai.clinica.exception.Response;
import br.com.senai.clinica.repository.AnimalRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/Animal")
public class AnimalController {
    @Autowired
    private AnimalRepository repository;

    @PostMapping
    public Response createAnimal(@RequestBody Animal animal) {
        repository.save(animal);
        return new Response(201, "Animal criado com sucesso");

    }

    @GetMapping
    public List<Animal> getAllAnimals() {
        return repository.findAll();

    }

    @PutMapping("{/id}")
    public Response updateAnimal(@PathVariable Long id, @RequestBody Animal updated) {
        if (!repository.existsById(id)) {
            return new Response(407, "Animal não encontrado");

        }
        Animal animal = repository.findById(id).get();

        if (updated.getNome() != null) {
            animal.setNome(updated.getNome());
        }
        if (updated.getEspecie() != null) {
            animal.setEspecie(updated.getEspecie());
        }
         if (updated.getRaca() != null) {
            animal.setRaca(updated.getRaca());
        }
         if (updated.getIdade() != null) {
            animal.setIdade(updated.getIdade());
        }
         if (updated.getInfos_medicas() != null) {
            animal.setInfos_medicas(updated.getInfos_medicas());
        }
         if (updated.getStatus() != null) {
            animal.setStatus(updated.getStatus());


        repository.save(animal);

        return new Response(200, "Animal atualizado com sucesso");}
         return null;

    }


    @DeleteMapping("/{id}")
    public Response deleteAnimal(@PathVariable Long id) {
        // Verifica existência para retornar um erro amigável se não encontrado.
        if (!repository.existsById(id)) {
            return new Response(404, "Animal não encontrado");
        }
        // Remove o registro; se ocorrer exceção (ex.: constraint), seria interessante
        // tratar.
        repository.deleteById(id);
        // 204 com mensagem textual aqui é informativo; em APIs REST reais 204 costuma
        // retornar sem corpo.
        return new Response(204, "Animal deletado com successo");
    }


}
