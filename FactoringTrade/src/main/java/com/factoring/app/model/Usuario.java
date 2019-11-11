package com.factoring.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.factoring.app.dateAudit.DateAudit;

@Entity
@Table(name = "Usuarios")
public class Usuario extends DateAudit {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Usuario_id")
	private Long id;

	@Min(value = 11)
	@Max(value = 11)
	@Column(name = "ruc")
	private int RUC;

	@NotEmpty(message = "Ingrese su nombre")
	@Column(name = "nombre")
	private String nombre;

	@NotEmpty(message = "Ingrese sus apellidos")
	@Column(name = "Apellido")
	private String Apellido;

	public Usuario() {
		this.setCreatedAt(new Date());
		this.setUpdatedAt(new Date());
	}

	public Usuario(@Max(value = 11) @Min(value = 11) int RUC, @NotEmpty String nombre, @NotEmpty String Apellido) {

		this.RUC = RUC;
		this.Apellido = Apellido;
		this.nombre = nombre;

		this.setCreatedAt(new Date());
		this.setUpdatedAt(new Date());

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getRUC() {
		return RUC;
	}

	public void setRUC(int rUC) {
		RUC = rUC;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return Apellido;
	}

	public void setApellido(String apellido) {
		Apellido = apellido;
	}

}
