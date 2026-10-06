package com.daw.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.daw.persistence.entities.TareaEntity;
import com.daw.persistence.entities.enums.Estado;
import com.daw.persistence.repositories.TareaRepository;
import com.daw.services.exceptions.TareaException;
import com.daw.services.exceptions.TareaNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TareaService {
	
	private final TareaRepository tareaRepository;
	
	public List<TareaEntity> findAll() {
		return this.tareaRepository.findAll();
	}
	
	public TareaEntity findById(long idTarea) {
		if(!this.tareaRepository.existsById(idTarea)) {
			throw new TareaNotFoundException(String.format("La tarea con id %d no existe. ", idTarea));
		}
		
		return this.tareaRepository.findById(idTarea).get();
	}
	
	public TareaEntity create(TareaEntity tareaEntity) {
		if(tareaEntity.getFechaCreacion() != null) {
			throw new TareaException("La fecha de creación no se puede modificar. ");
		}
		if(tareaEntity.getEstado() != null) {
			throw new TareaException("El estado no se puede modificar. ");
		}
		if(tareaEntity.getFechaVencimiento().isBefore(LocalDate.now())) {
			throw new TareaException("La fecha de vencimiento debe ser posterior a la fecha de creación.");
		}
		
		// La tarea debe llevar un id que no existe
		tareaEntity.setId(0L);
		tareaEntity.setEstado(Estado.PENDIENTE);
		tareaEntity.setFechaCreacion(LocalDate.now());		
		
		return this.tareaRepository.save(tareaEntity);
	}
	
	public TareaEntity update(long idTarea, TareaEntity tareaEntity) {
		if(tareaEntity.getId() != idTarea) {
			throw new TareaException("El id del body y el id del path no coinciden. ");
		}
		if(tareaEntity.getFechaCreacion() != null) {
			throw new TareaException("La fecha de creación no se puede modificar. ");
		}
		if(tareaEntity.getEstado() != null) {
			throw new TareaException("El estado no se puede modificar. ");
		}
		if(tareaEntity.getFechaVencimiento().isBefore(LocalDate.now())) {
			throw new TareaException("La fecha de vencimiento debe ser posterior a la fecha de creación.");
		}
		
		return this.tareaRepository.save(tareaEntity);
	}
	
	public void deleteById(long idTarea) {
		if(!this.tareaRepository.existsById(idTarea)) {
			throw new TareaNotFoundException(String.format("La tarea con id %d no existe. ", idTarea));
		}
		
		this.tareaRepository.deleteById(idTarea);
	}

}
