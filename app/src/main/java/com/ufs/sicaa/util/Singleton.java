package com.ufs.sicaa.util;

import com.ufs.sicaa.model.Aluno;
import com.ufs.sicaa.model.Criterio;
import com.ufs.sicaa.model.Usuario;

import java.util.List;

/**
 * Created by guilhermeboroni on 15/04/17.
 */

public class Singleton {

    private static Singleton instance;

    private Usuario usuario;

    private Aluno alunoAvaliado;

    private List<Aluno> alunos;

    private List<Criterio> criterios;

    public static Singleton getInstance() {
        if (instance == null)
            instance = new Singleton();
        return instance;
    }

    public static void setInstance(Singleton instance) {
        Singleton.instance = instance;
    }


    public List<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public List<Criterio> getCriterios() {
        return criterios;
    }

    public void setCriterios(List<Criterio> criterios) {
        this.criterios = criterios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Aluno getAlunoAvaliado() {
        return alunoAvaliado;
    }

    public void setAlunoAvaliado(Aluno alunoAvaliado) {
        this.alunoAvaliado = alunoAvaliado;
    }
}