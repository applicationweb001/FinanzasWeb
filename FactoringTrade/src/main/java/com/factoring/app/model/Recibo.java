package com.factoring.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.factoring.app.dateAudit.DateAudit;

@Entity
@Table(name = "recibos")
public class Recibo extends DateAudit {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "recibo_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private Account cuenta;
	
	@ManyToOne
	@JoinColumn(name = "banco_id")
	private Banco banco;
		
	@NotEmpty(message = "Por favor ingrese la fecha de emisión del recibo.")
	@Column(name = "F_Emision")
	private Date fechaEmision;

	@NotEmpty(message = "Por favor ingrese la fecha de pago del recibo.")
	@Column(name = "F_Pago")	
	private Date fechaPago;

	@NotEmpty(message = "Por favor ingrese el monto total del Total de pago.")
	@Column(name = "totalpago")	
	private String totalPago;

	@Column(name = "valorActual")	
	private String valorActual;

	
	@Column(name = "retencion")	
	private String retencion;
	
	public Recibo() {
    	this.setCreatedAt(new Date());
        this.setUpdatedAt(new Date());
    }
	
	public Recibo(Long id, Account cuenta, Banco banco,
			@NotEmpty(message = "Por favor ingrese la fecha de emisión del recibo.") Date fechaEmision,
			@NotEmpty(message = "Por favor ingrese la fecha de pago del recibo.") Date fechaPago,
			@NotEmpty(message = "Por favor ingrese el monto total del Total de pago.") String totalPago,
			String valorActual, String retencion) {
		super();
		this.id = id;
		this.cuenta = cuenta;
		this.banco = banco;
		this.fechaEmision = fechaEmision;
		this.fechaPago = fechaPago;
		this.totalPago = totalPago;
		this.valorActual = valorActual;
		this.retencion = retencion;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Account getCuenta() {
		return cuenta;
	}

	public void setCuenta(Account cuenta) {
		this.cuenta = cuenta;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
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



	public String getTotalPago() {
		return totalPago;
	}



	public void setTotalPago(String totalPago) {
		this.totalPago = totalPago;
	}



	public String getValorActual() {
		return valorActual;
	}



	public void setValorActual(String valorActual) {
		this.valorActual = valorActual;
	}



	public String getRetencion() {
		return retencion;
	}



	public void setRetencion(String retencion) {
		this.retencion = retencion;
	}

	


}
