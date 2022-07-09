package com.mylibrary.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Commento {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	@NotBlank
	private String username;
	
	@Column(nullable = false)
	@NotBlank
	private String descrizione;
	
	@Column(nullable = false)
	@NotBlank
	private Integer voto;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Integer getVoto() {
		return voto;
	}

	public void setVoto(Integer voto) {
		this.voto = voto;
	}
	
}
