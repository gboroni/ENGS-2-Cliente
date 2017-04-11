package com.ufs.sicaa.model;

/**
 * Created by guilhermeboroni on 09/04/17.
 */

public class Aluno {
    private int id ;
    private int id_aluno;
    private int id_turma;
    private int id_apresentacao;
    private String matricula_aluno;
    private String nome_aluno;
    private String codigo_turma;


    public Aluno(int id, int id_aluno, int id_turma, int id_apresentacao, String matricula_aluno, String nome_aluno, String codigo_turma) {
        this.id = id;
        this.id_aluno = id_aluno;
        this.id_turma = id_turma;
        this.id_apresentacao = id_apresentacao;
        this.matricula_aluno = matricula_aluno;
        this.nome_aluno = nome_aluno;
        this.codigo_turma = codigo_turma;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_aluno() {
        return id_aluno;
    }

    public void setId_aluno(int id_aluno) {
        this.id_aluno = id_aluno;
    }

    public int getId_turma() {
        return id_turma;
    }

    public void setId_turma(int id_turma) {
        this.id_turma = id_turma;
    }

    public int getId_apresentacao() {
        return id_apresentacao;
    }

    public void setId_apresentacao(int id_apresentacao) {
        this.id_apresentacao = id_apresentacao;
    }

    public String getMatricula_aluno() {
        return matricula_aluno;
    }

    public void setMatricula_aluno(String matricula_aluno) {
        this.matricula_aluno = matricula_aluno;
    }

    public String getNome_aluno() {
        return nome_aluno;
    }

    public void setNome_aluno(String nome_aluno) {
        this.nome_aluno = nome_aluno;
    }

    public String getCodigo_turma() {
        return codigo_turma;
    }

    public void setCodigo_turma(String codigo_turma) {
        this.codigo_turma = codigo_turma;
    }
}
