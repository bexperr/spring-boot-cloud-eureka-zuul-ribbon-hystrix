package com.bexperr.users.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import java.util.List;

@Entity
@Table(name="roles")
public class Role implements Serializable{

	private static final long serialVersionUID = -6069518343751790941L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true,length = 30)
	private String nombre;
	
//	Se usa solo para matear de muchos a muchos bidireccional
//	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
//	List<Usuario> usuarios;

}
