package com.bbeauvais.hibernateexample.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PreparationLigne")
public class PreparationLigne {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idPreparationLigne;

	@Column
	private BigDecimal quantite;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idPreparation", nullable = false)
	private Preparation preparation;

	public int getIdPreparationLigne() {
		return idPreparationLigne;
	}

	public void setIdPreparationLigne(int idPreparationLigne) {
		this.idPreparationLigne = idPreparationLigne;
	}

	public BigDecimal getQuantite() {
		return quantite;
	}

	public void setQuantite(BigDecimal quantite) {
		this.quantite = quantite;
	}

	public Preparation getPreparation() {
		return preparation;
	}

	public void setPreparation(Preparation preparation) {
		this.preparation = preparation;
	}

	@Override
	public String toString() {
		return "PreparationLigne [idPreparationLigne=" + idPreparationLigne + ", quantite=" + quantite
				+ ", preparation=" + preparation.getIdPreparation() + "]";
	}

}
