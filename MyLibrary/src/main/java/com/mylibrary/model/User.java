package com.mylibrary.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users") // user in postgres è una parola riservata
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nome;
	
	@OneToMany
	@JoinColumn(name = "film_id")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Film> filmPreferiti;
	
	@OneToMany
	@JoinColumn(name = "gioco_id")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Gioco> giochiPreferiti;
	
	@OneToMany
	@JoinColumn(name = "serie_tv_id")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<SerieTv> serieTvPreferite;
	
	@OneToMany
	@JoinColumn(name = "libro_id")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Libro> libriPreferiti;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Film> getFilmPreferiti() {
		return filmPreferiti;
	}

	public void setFilmPreferiti(List<Film> filmPreferiti) {
		this.filmPreferiti = filmPreferiti;
	}

	public List<Gioco> getGiochiPreferiti() {
		return giochiPreferiti;
	}

	public void setGiochiPreferiti(List<Gioco> giochiPreferiti) {
		this.giochiPreferiti = giochiPreferiti;
	}

	public List<SerieTv> getSerieTvPreferite() {
		return serieTvPreferite;
	}

	public void setSerieTvPreferite(List<SerieTv> serieTvPreferite) {
		this.serieTvPreferite = serieTvPreferite;
	}
	
	public List<Libro> getLibriPreferiti() {
		return libriPreferiti;
	}

	public void setLibriPreferiti(List<Libro> libriPreferiti) {
		this.libriPreferiti = libriPreferiti;
	}
	
	public void addGiocoToPreferiti(Gioco gioco) {
		this.giochiPreferiti.add(gioco);
	}
	
	public void addFilmToPreferiti(Film film) {
		this.filmPreferiti.add(film);
	}

	public void addSerieTvToPreferiti(SerieTv serieTv) {
		this.serieTvPreferite.add(serieTv);
	}
	
	public void addLibroToPreferiti(Libro libro) {
		this.libriPreferiti.add(libro);
	}
	
}

