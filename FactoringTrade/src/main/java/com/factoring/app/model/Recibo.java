package com.factoring.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "Recibos")
public class Recibo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "recibo_id")
	private Long id;
	
	@Column(name = "FK_usuario")
	private Account usuario;
	
	@NotEmpty(message = "Por favor ingrese la fecha de emisión del recibo.")
	@Column(name = "F_Emision")
	private Date fechaEmision;

	@NotEmpty(message = "Por favor ingrese la fecha de pago del recibo.")
	@Column(name = "F_Pago")	
	private Date fechaPago;

	@NotEmpty(message = "Por favor ingrese el monto total del recibo.")
	@Column(name = "total")	
	private double total;

	@Column(name = "retencion")	
	private double retencion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getRetencion() {
		return retencion;
	}

	public void setRetencion(double retencion) {
		this.retencion = retencion;
	}
	
	
	

}
