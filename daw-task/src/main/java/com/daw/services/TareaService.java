package com.daw.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.daw.persistence.entities.TareaEntity;
import com.daw.persistence.repositories.TareaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TareaService {
	
	private final TareaRepository tareaRepository;
	
	public List<TareaEntity> findAll() {
		return this.tareaRepository.findAll();
	}
	
	

}
