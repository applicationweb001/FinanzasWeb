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
@Table(name = "Compras")
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "compra_id")
	private Long id;
	
	//@Column(name = "FK_Recibo")
	//private Recibo recibo;

	@NotEmpty(message = "Por favor ingrese la fecha de descuento de la compra.")
	@Column(name = "F_Descuento")
	private Date fechaDescuento;

	@NotEmpty(message = "Por favor ingrese la tasa de interes")
	@Column(name = "Tep")	
	private Double tep;

	@NotEmpty(message = "Por favor ingrese el periodo de la compra.")
	@Column(name = "Periodo")	
	private String periodo;

	@NotEmpty(message = "Por favor ingrese el tipo de moneda.")
	@Column(name = "Tipo_Moneda")	
	private String tipoMoneda;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
/*
	public Recibo getRecibo() {
		return recibo;
	}

	public void setRecibo(Recibo recibo) {
		this.recibo = recibo;
	}
*/
	public Date getFechaDescuento() {
		return fechaDescuento;
	}

	public void setFechaDescuento(Date fechaDescuento) {
		this.fechaDescuento = fechaDescuento;
	}

	public Double getTep() {
		return tep;
	}

	public void setTep(Double tep) {
		this.tep = tep;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getTipoMoneda() {
		return tipoMoneda;
	}

	public void setTipoMoneda(String tipoMoneda) {
		this.tipoMoneda = tipoMoneda;
	}

	
	
}
