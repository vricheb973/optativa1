package com.daw.services.exceptions;

public class PokemonNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8473111632675389517L;

	public PokemonNotFoundException(String message) {
		super(message);
	}

}
