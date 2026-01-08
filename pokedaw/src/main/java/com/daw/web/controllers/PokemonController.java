package com.daw.web.controllers;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.daw.persistence.entities.Pokemon;
import com.daw.persistence.entities.enums.Tipo;
import com.daw.services.PokemonService;
import com.daw.services.exceptions.PokemonException;
import com.daw.services.exceptions.PokemonNotFoundException;

public class PokemonController {
	
	private PokemonService pokemonService;

//	public ResponseEntity<?> buscarPorTipo(@RequestParam String tipo){
//		try {
//			
//		}
//		catch(IllegalArgumentException ex) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
//		}
//		
//		
//	}
	
	@PutMapping("/{idPokemon}/tipo")
	public ResponseEntity<?> cambiarTipo(@PathVariable int idPokemon, @RequestParam String tipo1, @RequestParam(required = false) String tipo2){
		try {
			return ResponseEntity.ok(this.pokemonService.cambiarTipo(idPokemon, tipo1, tipo2));
		}
		catch(PokemonException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
		catch(PokemonNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}
	
	@GetMapping("/fecha")
	public ResponseEntity<?> buscarPorFecha(@RequestParam LocalDate inicio, @RequestParam LocalDate fin){
		try {
			return ResponseEntity.ok(this.pokemonService.buscarPorFechaCaptura(inicio, fin));
		}
		catch(PokemonException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
 	

}
