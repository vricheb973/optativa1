package com.daw.persistence.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.ListCrudRepository;

import com.daw.persistence.entities.Pokemon;
import com.daw.persistence.entities.enums.Tipo;

public interface PokemonRepository extends ListCrudRepository<Pokemon, Integer>{
	
	List<Pokemon> findByTipo1OrTipo2(Tipo tipo1, Tipo tipo2);
	
	// SELECT * FROM pokemon WHERE fecha_captura BETWEEN ?1 AND ?2
	// SELECT * FROM pokemon WHERE fecha_captura >= ?1 AND fecha_captura <= ?2
	List<Pokemon> findByFechaCapturaBetween(LocalDate inicio, LocalDate fin);
	

}
