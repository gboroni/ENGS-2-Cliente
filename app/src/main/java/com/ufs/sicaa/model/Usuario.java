package com.ufs.sicaa.model;

/**
 * Created by guilhermeboroni on 16/04/17.
 */

public class Usuario {

    private int id = 0;

    private String nome;

    private String matricula;

    public Usuario(int id, String nome, String matricula) {
        this.id = id;
        this.nome = nome;
        this.matricula = matricula;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
