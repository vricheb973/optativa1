package com.daw.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.persistence.entities.Pokemon;
import com.daw.persistence.entities.enums.Tipo;
import com.daw.persistence.repositories.PokemonRepository;
import com.daw.services.exceptions.PokemonException;
import com.daw.services.exceptions.PokemonNotFoundException;

@Service
public class PokemonService {

	@Autowired
	private PokemonRepository pokemonRepository;
	
	public Pokemon findById(int idPokemon) {
		if(!this.pokemonRepository.existsById(idPokemon)) {
			throw new PokemonNotFoundException("El ID no existe. ");
		}
		
		return this.pokemonRepository.findById(idPokemon).get();
	}
	
//	public List<Pokemon> buscarPorTipo(String tipo) {
//		try {
//			Tipo t = Tipo.valueOf(tipo);
//			return this.pokemonRepository.findByTipo1OrTipo2(t, t);
//			
//		}
//		catch(IllegalArgumentException e) {
//			throw new PokemonException("El tipo indicado no es válido. ");
//		}
//	}
	
	public Pokemon cambiarTipo(int idPokemon, String tipo1, String tipo2) {
		try {
			Tipo t1 = Tipo.valueOf(tipo1.toUpperCase());
			Tipo t2;
			
			if(tipo2 != null) {
				t2 = Tipo.valueOf(tipo2.toUpperCase());
			}
			else {
				t2 = Tipo.NINGUNO;
			}
			
			if(t1.equals(t2)) {
				throw new PokemonException("Los tipos no pueden coincidir. ");
			}
			
			
			Pokemon pokemon = this.findById(idPokemon);
			pokemon.setTipo1(t1);
			pokemon.setTipo2(t2);
			return this.pokemonRepository.save(pokemon);			
		}
		catch(IllegalArgumentException e) {
			throw new PokemonException("El tipo indicado no es válido. ");
		}
	}
	
	public List<Pokemon> buscarPorFechaCaptura(LocalDate inicio, LocalDate fin){
		if(fin.isBefore(inicio)) {
			throw new PokemonException("La fecha de fin no puede ser anterior a la de inicio. ");
		}
		if(inicio.isAfter(LocalDate.now())) {
			throw new PokemonException("La fecha de inicio debe ser anterior a la actual. ");
		}
		
		return this.buscarPorFechaCaptura(inicio, fin);
	}
	
	
}
