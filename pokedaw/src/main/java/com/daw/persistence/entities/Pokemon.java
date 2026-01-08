package com.daw.persistence.entities;

import java.time.LocalDate;

import com.daw.persistence.entities.enums.Pokeball;
import com.daw.persistence.entities.enums.Tipo;

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
@Table(name = "pokemon")
@Getter
@Setter
@NoArgsConstructor
public class Pokemon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "numero_pokedex")
	private int numeroPokedex;
	private String nombre;

	@Enumerated(value = EnumType.STRING)
	private Pokeball capturado;

	@Enumerated(value = EnumType.STRING)
	private Tipo tipo1;

	@Enumerated(value = EnumType.STRING)
	private Tipo tipo2;

	@Column(name = "fecha_captura")
	private LocalDate fechaCaptura;

}
