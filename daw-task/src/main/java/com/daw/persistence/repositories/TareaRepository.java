package com.daw.persistence.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daw.persistence.entities.TareaEntity;
import com.daw.persistence.entities.enums.Estado;

public interface TareaRepository extends JpaRepository<TareaEntity, Long> {
	
	// SELECT * FROM tarea WHERE estado = "PENDIENTE"
	List<TareaEntity> findByEstado(Estado estado);
	
	// SELECT * FROM tarea WHERE fecha_vencimiento < NOW()
	List<TareaEntity> findByFechaVencimientoBefore(LocalDate fecha);
	
	// SELECT * FROM tarea WHERE fecha_vencimiento > NOW()
	List<TareaEntity> findByFechaVencimientoAfter(LocalDate fecha);
	
	// SELECT * FROM tarea WHERE titulo LIKE %parametro%;
	List<TareaEntity> findByTituloContaining(String cadena);

}
