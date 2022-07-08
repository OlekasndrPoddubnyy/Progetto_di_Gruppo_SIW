package com.mylibrary.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Episodio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    @NotBlank
    private String nome;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String descrizione;

    @Column(nullable = false)
    @NotBlank
    private int stagione;


    @Column(nullable = false)
    @NotBlank
    private int durata;


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

    public int getDurata() {
        return durata;
    }

    public void setDurata(int durata) {
        this.durata = durata;
    }

    public int getStagione() {
        return stagione;
    }

    public void setStagione(int stagione) {
        this.stagione = stagione;
    }

}
