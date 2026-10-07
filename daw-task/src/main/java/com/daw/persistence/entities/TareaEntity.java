package com.daw.persistence.entities;

import java.time.LocalDate;

import com.daw.persistence.entities.enums.Estado;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tarea")
@Getter
@Setter
@NoArgsConstructor
public class TareaEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String titulo;
	private String descripcion;
	
	@Column(name = "fecha_creacion")
	private LocalDate fechaCreacion;

	@Column(name = "fecha_vencimiento")
	private LocalDate fechaVencimiento;
	
	@Enumerated(EnumType.STRING)
	private Estado estado;
	
}
