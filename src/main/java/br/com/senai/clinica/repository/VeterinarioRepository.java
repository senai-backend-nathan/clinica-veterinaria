package br.com.senai.clinica.repository;
import br.com.senai.clinica.entity.Veterinario;
import org.springframework.data.jpa.repository.JpaRepository;



public interface VeterinarioRepository extends JpaRepository<Veterinario, Long> {
     boolean existsByCrmv(String crmv);
}
