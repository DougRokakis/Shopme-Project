package com.shopme.common.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role {

	// ===================
	// ATTRIBUTES
	// ===================
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length =40, nullable= false, unique = true)
	private String name;

	@Column(length =150, nullable= false)
	private String description;
	
	
	// =================
	// CONSTRUCTOR
	// =================
	public Role() {}
	
	public Role(Integer id) {
		this.id = id;
	}
	
	public Role(String name) {
		this.name = name;
	}
	
	public Role(String name, String description) {
		this.name = name;
		this.description = description;
	}

//  =========================
//  GETTERS AND SETTERS
//  =========================
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return this.name;
	}
	
	
}
