package com.mylibrary.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class SerieTv {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank
    private String nome;

    private String descrizione;

    @NotNull
    @Min(0)
    private Integer numeroStagioni;


    private String genere;

    @OneToMany(cascade = CascadeType.REMOVE)
    @Fetch(FetchMode.SELECT)
    List<Episodio> episodi;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Commento> commenti;


    public List<Commento> getCommenti() {
        return commenti;
    }

    public void setCommenti(List<Commento> commenti) {
        this.commenti = commenti;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Integer getNumeroStagioni() {
        return numeroStagioni;
    }

    public void setNumeroStagioni(Integer numeroStagioni) {
        this.numeroStagioni = numeroStagioni;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public List<Episodio> getEpisodi() {
        return episodi;
    }

    public void setEpisodi(List<Episodio> episodi) {
        this.episodi = episodi;
    }
}
