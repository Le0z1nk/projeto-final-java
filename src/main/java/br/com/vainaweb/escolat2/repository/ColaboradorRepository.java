package br.com.vainaweb.escolat2.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.vainaweb.escolat2.model.ColaboradorModel;

@Repository //Trata a interface como a camada de persistencia
public interface ColaboradorRepository extends JpaRepository<ColaboradorModel, Long> {

	Optional<ColaboradorModel> findByCpf(String cpf);
	
}
