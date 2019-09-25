package com.bbeauvais.hibernateexample.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Preparation")
public class Preparation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idPreparation;

	@Column
	private String description;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "preparation", orphanRemoval = true)
	private List<PreparationLigne> preparationLignes = new ArrayList<>();

	public int getIdPreparation() {
		return idPreparation;
	}

	public void setIdPreparation(int id) {
		this.idPreparation = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<PreparationLigne> getPreparationLignes() {
		return preparationLignes;
	}

	public void setPreparationLignes(List<PreparationLigne> preparationLignes) {
		this.preparationLignes = preparationLignes;
	}

	@Override
	public String toString() {
		return "Preparation [id=" + idPreparation + ", description=" + description + "]";
	}
}
