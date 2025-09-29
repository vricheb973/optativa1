package com.daw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daw.persistence.entities.Tarea;
import com.daw.persistence.repositories.TareaRepository;

@Service
public class TareaService {
	
	// findAll
	// findById
	// save (crear y actualizar)
	// deleteById
	
	// existsById (nos devuelve true si existe la tarea con esa ID)
	
	@Autowired
	private TareaRepository tareaRepository;
	
	// findAll
	public List<Tarea> findAll() {
		return this.tareaRepository.findAll();
	}
	
	// findById
	public Tarea findById(int idTarea) {
		return this.tareaRepository.findById(idTarea).get();
	}
	
	
	// create
	
	// update
	
	// delete

}
