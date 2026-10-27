package com.daw.persistence.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daw.persistence.entities.PokemonEntity;
import com.daw.persistence.entities.enums.Tipo;

import java.time.LocalDate;


public interface PokemonRepository extends JpaRepository<PokemonEntity, Long> {
	
	/*
	 * Buscar un pokemon según su número de la pokédex. GET /pokemon/pokedex
	 * Buscar los pokemon capturados en un rango de fechas. GET /pokemon/fecha
	 * Buscar pokemon de un tipo determinado (da igual que sea tipo1 o tipo2). GET /pokemon/tipo
	 */
	
	List<PokemonEntity> findByNumeroPokedex(long numeroPokemon);
	List<PokemonEntity> findByFechaCapturaBetween(LocalDate inicio, LocalDate fin);
	List<PokemonEntity> findByTipo1OrTipo2(Tipo tipo1, Tipo tipo2);
	
}
