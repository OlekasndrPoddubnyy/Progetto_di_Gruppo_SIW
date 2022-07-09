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
    @Column(nullable = false)
    @NotBlank
    private String nome;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descrizione;

    @NotNull
    @Min(0)
    private Integer numeroStagioni;


    @Column(nullable = false)
    @NotBlank
    private String genere;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<Episodio> episodi;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
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

    public int getMediaVoti(){
        int somma = 0, diviso = 0;
        if(this.commenti.isEmpty())
            return 0;
        for (Commento commento : this.commenti){
            if(commento.getVoto() != null){
                somma += commento.getVoto();
                diviso++;
            }
        }
        return somma/diviso;
    }
}
