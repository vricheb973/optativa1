package com.daw.web.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.daw.persistence.entities.PokemonEntity;
import com.daw.services.PokemonService;
import com.daw.services.exceptions.PokemonException;
import com.daw.services.exceptions.PokemonNotFoundException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/pokemon")
@RequiredArgsConstructor
public class PokemonController {
	
	private final PokemonService pokemonService;
	
	@GetMapping
	public  ResponseEntity<List<PokemonEntity>> list() {
		return ResponseEntity.ok(this.pokemonService.findAll());
	}
	
	@GetMapping("/{idPokemon}")
	public ResponseEntity<?> findById(@PathVariable long idPokemon){
		try {
			return ResponseEntity.ok(this.pokemonService.findById(idPokemon));
		}
		catch(PokemonNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody PokemonEntity pokemonEntity){
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(this.pokemonService.create(pokemonEntity));
		}
		catch(PokemonException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	@PutMapping("/{idPokemon}")
	public ResponseEntity<?> update(@PathVariable long idPokemon, @RequestBody PokemonEntity pokemonEntity){
		try {
			return ResponseEntity.ok(this.pokemonService.update(idPokemon, pokemonEntity));
		}
		catch(PokemonException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	
	@DeleteMapping("/{idPokemon}")
	public ResponseEntity<?> deleteById(@PathVariable long idPokemon){
		try {
			this.pokemonService.deleteById(idPokemon);
			return ResponseEntity.ok().build();
		}
		catch(PokemonNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}
	
	@GetMapping("/pokedex")
	public  ResponseEntity<List<PokemonEntity>> buscarPokedex(@RequestParam long numeroPokedex) {
		return ResponseEntity.ok(this.pokemonService.buscarPokedex(numeroPokedex));
	}
	
	@GetMapping("/tipo")
	public  ResponseEntity<?> buscarTipo(@RequestParam String tipo) {
		try {
			return ResponseEntity.ok(this.pokemonService.buscarPorTipo(tipo));
		}
		catch(IllegalArgumentException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El tipo indicado no existe. ");
		}
	}
	
	@GetMapping("/fecha")
	public  ResponseEntity<List<PokemonEntity>> buscarPorFechas(@RequestParam LocalDate inicio, @RequestParam LocalDate fin) {
		return ResponseEntity.ok(this.pokemonService.buscarPorFechas(inicio, fin));
	}

}
