package com.daw.persistence.repositories;

import org.springframework.data.repository.ListCrudRepository;

import com.daw.persistence.entities.Tarea;

public interface TareaRepository extends ListCrudRepository<Tarea, Integer> {

}
