package com.factoring.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.factoring.app.dateAudit.DateAudit;


@Entity
@Table(name = "Bancos")
public class Banco extends DateAudit{
	
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "banco_id")
	private Long id;
	
	public Banco() {
		this.setCreatedAt(new Date());
		this.setUpdatedAt(new Date());
	}

	public Banco(Long id, @NotEmpty(message = "Por favor ingrese el nombre del banco.") String nombre, double tasa,
			@NotEmpty(message = "Por favor ingrese el tipo de tasa.") String tipoTasa) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.tasa = tasa;
		this.tipoTasa = tipoTasa;
	}

	@NotEmpty(message = "Por favor ingrese el nombre del banco.")
	@Column(name = "Nombre")
	private String nombre;

	
	@Column(name = "Tasa")	
	private double tasa;
	
	@NotEmpty(message = "Por favor ingrese el tipo de tasa.")
	@Column(name = "Tipo_de_Tasa")	
	private String tipoTasa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getTasa() {
		return tasa;
	}

	public void setTasa(double tasa) {
		this.tasa = tasa;
	}

	public String getTipoTasa() {
		return tipoTasa;
	}

	public void setTipoTasa(String tipoTasa) {
		this.tipoTasa = tipoTasa;
	}
	
	
	
}
