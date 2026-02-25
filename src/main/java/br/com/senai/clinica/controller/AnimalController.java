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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/Animal")
public class AnimalController {
    @Autowired
    private AnimalRepository repository;
    @PostMapping
    public Response createAnimal(@RequestBody Animal animal){
        repository.save(animal);
        return new Response(201, "Animal criado com sucesso");

    }
    @GetMapping
    public List<Animal> getAllAnimals() {
        return repository.findAll();
       
    }

    @PutMapping("/id")
    public Response updateAnimal(@PathVariable Long id, @RequestBody Animal updated){
        if (!repository.existsById(id)) {
            return new Response(407, "Animal não encontrado");

        }
    Animal animal = repository.findById(id).get();

    if (updated.getNome() != null) {
        animal.setNome(updated.getNome());
    }
    }
    
    
}
