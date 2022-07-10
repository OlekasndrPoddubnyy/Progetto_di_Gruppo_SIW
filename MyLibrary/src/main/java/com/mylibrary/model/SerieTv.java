package com.mylibrary.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SerieTv {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
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

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(name = "serie_tv_id")
    private List<Episodio> episodi;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Commento> commenti;


    public List<Commento> getCommenti() {
        return commenti;
    }

    public void setCommenti(List<Commento> commenti) {
        this.commenti = commenti;
    }

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

    public List<Integer> getStagioni(){
        List<Integer> stagioni = new ArrayList<>();
        for ( int i=1; i<=this.numeroStagioni; i++){
            stagioni.add(i);
        }
        return stagioni;
    }

    public void addEpisodio(Episodio episodio){
        if(getEpisodi().isEmpty())
            this.episodi = new ArrayList<>();
        if(getEpisodi().contains(episodio))
            return;
        getEpisodi().add(episodio);
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
    
    @Override
	public int hashCode() {
		return this.getId().hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		SerieTv serieTv = (SerieTv)obj;
		return this.getId() == serieTv.getId();
	}
	
}
