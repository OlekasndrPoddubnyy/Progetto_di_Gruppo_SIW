package com.mylibrary.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
public class Libro {

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
    private String autore;
    @Column(nullable = false)
    @NotBlank
    private String genere;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    List<Commento> commenti;

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

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
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
