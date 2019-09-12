package com.example.recipe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.util.Date;

/**
 * 
 * @author shweta kamath
 *
 */
@Entity
@Table(name = "TBL_RECIPE")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = { "createdAt", "updatedAt" }, allowGetters = true)

public class RecipeEntity {
	/**
	 * Id is auto generated using GeneratedValue
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "dish")
	private String dish;

	@Column(name = "indicator")
	private String indicator;

	@Column(name = "servingCap")
	private long servingCap;

	@Column(name = "ingredients")
	private String ingredients;

	@Column(name = "instructions")
	private String instructions;

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedAt;

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	// Constructors
	public RecipeEntity(Long id, String dish, String indicator, long servingCap, String ingredients,
			String instructions, Date createdAt, Date updatedAt) {
		super();
		this.id = id;
		this.dish = dish;
		this.indicator = indicator;
		this.servingCap = servingCap;
		this.ingredients = ingredients;
		this.instructions = instructions;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public RecipeEntity() {
	}

	// Setters and getters
	public String getDish() {
		return dish;
	}

	public void setDish(String dish) {
		this.dish = dish;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIndicator() {
		return indicator;
	}

	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}

	public long getServingCap() {
		return servingCap;
	}

	public void setServingCap(long servingCap) {
		this.servingCap = servingCap;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;

	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	@Override
	public String toString() {
		return "RecipeEntity [id=" + id + ", dish=" + dish + ", indicator=" + indicator + ", servingCap=" + servingCap
				+ ", ingredients=" + ingredients + ", instructions=" + instructions + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + "]";
	}

}