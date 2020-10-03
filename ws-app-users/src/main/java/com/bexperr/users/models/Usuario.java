package com.bexperr.users.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.checkerframework.common.aliasing.qual.Unique;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Entity
@Table(name="usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Usuario implements Serializable{

	private static final long serialVersionUID = -3720238940792458652L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true,length = 20)
	private String username;
	
	@Column(length = 60)
	private String password;
	
	private Boolean enabled;
	private String nombre;
	private String apellido;
	
	@Column(unique=true,length = 100)
	private String email;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "usuarios_roles",
	joinColumns = @JoinColumn(name="user_id"), 
	inverseJoinColumns = @JoinColumn(name="role_id"),
	uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id","role_id"})})
	private List<Role> roles;

}
