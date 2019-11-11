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
@Table(name="bancos")
public class Banco extends DateAudit{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "banco_id")
	private Long id;
	
	@NotEmpty(message = "Por favor ingrese el nombre del banco.")
	@Column(name = "nombre")
	private String nombre;

	@NotEmpty(message = "Por favor ingrese la tasa del banco.")
	@Column(name = "tasa")	
	private double tasa;
	
	@NotEmpty(message = "Por favor ingrese el tipo de tasa.")
	@Column(name = "tipo_de_tasa")	
	private double tipoTasa;

	public Banco() {
    	this.setCreatedAt(new Date());
        this.setUpdatedAt(new Date());
    }
	
	
	
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

	public double getTipoTasa() {
		return tipoTasa;
	}

	public void setTipoTasa(double tipoTasa) {
		this.tipoTasa = tipoTasa;
	}
	
	
	
}
