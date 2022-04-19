package com.foodrecipe.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "recipe_details")
public class RecipeDetails implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int receipeId;
	
	@Column(name = "item")
	private String item;

	@Column(name = "name")
	private String name;

	@Column(name = "ingradiants")
	private String ingradiants;

	@Column(name = "steps")
	private String steps;

	public int getReceipeId() {
		return receipeId;
	}

	public void setReceipeId(int receipeId) {
		this.receipeId = receipeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIngradiants() {
		return ingradiants;
	}

	public void setIngradiants(String ingradiants) {
		this.ingradiants = ingradiants;
	}

	public String getSteps() {
		return steps;
	}

	public void setSteps(String steps) {
		this.steps = steps;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

}