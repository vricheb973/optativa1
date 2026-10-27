package com.daw.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.daw.persistence.entities.PokemonEntity;
import com.daw.persistence.entities.enums.Capturado;
import com.daw.persistence.entities.enums.Tipo;
import com.daw.persistence.repositories.PokemonRepository;
import com.daw.services.exceptions.PokemonException;
import com.daw.services.exceptions.PokemonNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PokemonService {
	
	private final PokemonRepository pokemonRepository;
	
	public List<PokemonEntity> findAll(){
		return this.pokemonRepository.findAll();
	}
	
	public PokemonEntity findById(long idPokemon) {
		if(!this.pokemonRepository.existsById(idPokemon)) {
			throw new PokemonNotFoundException("El pokemon no existe. ");
		}
			
		return this.pokemonRepository.findById(idPokemon).get();
	}
	
	public PokemonEntity create(PokemonEntity pokemonEntity) {
		if(pokemonEntity.getFechaCaptura() != null) {
			throw new PokemonException("No se puede modificar la fecha de captura. ");
		}
		if(pokemonEntity.getTipo1() == null || pokemonEntity.getTipo1() == Tipo.NINGUNO) {
			throw new PokemonException("El tipo 1 no puede ser nulo o ninguno. ");
		}
		if(pokemonEntity.getTipo1().equals(pokemonEntity.getTipo2()) || pokemonEntity.getTipo2() == null) {
			pokemonEntity.setTipo2(Tipo.NINGUNO);
		}
		if(pokemonEntity.getCapturado() == null) {
			pokemonEntity.setCapturado(Capturado.POKEBALL);
		}
		
		pokemonEntity.setId(0L);
		pokemonEntity.setFechaCaptura(LocalDate.now());
		
		return this.pokemonRepository.save(pokemonEntity);
	}
	
	public PokemonEntity update(long idPokemon, PokemonEntity pokemonEntity) {
		if(idPokemon != pokemonEntity.getId()) {
			throw new PokemonException("El id del path y el del body deben de ser el mismo. ");
		}
		if(pokemonEntity.getFechaCaptura() != null) {
			throw new PokemonException("No se puede modificar la fecha de captura. ");
		}
		if(pokemonEntity.getTipo1() == null || pokemonEntity.getTipo1() == Tipo.NINGUNO) {
			throw new PokemonException("El tipo 1 no puede ser nulo o ninguno. ");
		}
		if(pokemonEntity.getTipo1().equals(pokemonEntity.getTipo2()) || pokemonEntity.getTipo2() == null) {
			pokemonEntity.setTipo2(Tipo.NINGUNO);
		}
		if(pokemonEntity.getCapturado() == null) {
			pokemonEntity.setCapturado(Capturado.POKEBALL);
		}
		
		PokemonEntity pokemonBD = this.findById(idPokemon);
		pokemonBD.setNumeroPokedex(pokemonEntity.getNumeroPokedex());
		pokemonBD.setTipo1(pokemonEntity.getTipo1());
		pokemonBD.setTipo2(pokemonEntity.getTipo2());
		pokemonBD.setCapturado(pokemonEntity.getCapturado());		
		
		return this.pokemonRepository.save(pokemonBD);
	}
	
	public void deleteById(long idPokemon) {
		if(!this.pokemonRepository.existsById(idPokemon)) {
			throw new PokemonNotFoundException("El pokemon no existe. ");
		}
		
		this.pokemonRepository.deleteById(idPokemon);
	}
	
	public List<PokemonEntity> buscarPokedex(long numeroPokedex){
		return this.pokemonRepository.findByNumeroPokedex(numeroPokedex);
	}
	
	public List<PokemonEntity> buscarPorFechas(LocalDate inicio, LocalDate fin){
		return this.pokemonRepository.findByFechaCapturaBetween(inicio, fin);
	}
	
	public List<PokemonEntity> buscarPorTipo(String tipo){
		Tipo tipoEnum = Tipo.valueOf(tipo);
		
		return this.pokemonRepository.findByTipo1OrTipo2(tipoEnum, tipoEnum);
	}
	
}
