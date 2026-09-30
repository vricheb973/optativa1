package com.daw.web.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.daw.persistence.entities.TareaEntity;
import com.daw.services.TareaService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/tareas")
@RequiredArgsConstructor
public class TareaController {
	
	private final TareaService tareaService;
	
	@GetMapping
	public List<TareaEntity> list() {
		return this.tareaService.findAll();
	}

}
