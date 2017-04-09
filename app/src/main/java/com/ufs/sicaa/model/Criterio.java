package com.ufs.sicaa.model;

/**
 * Created by guilhermeboroni on 09/04/17.
 */

public class Criterio {
    private String nome;
    private Integer peso;
    private Double nota;


    public Criterio(String nome, Integer peso, Double nota) {
        this.nome = nome;
        this.peso = peso;
        this.nota = nota;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getPeso() {
        return peso;
    }

    public void setPeso(Integer peso) {
        this.peso = peso;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }
}
