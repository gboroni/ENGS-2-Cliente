package com.ufs.sicaa.model;

/**
 * Created by guilhermeboroni on 09/04/17.
 */

public class Criterio {
    private Integer id;
    private Integer id_criterio;
    private Integer id_apresentacao;
    private String descricao_criterio;
    private Integer peso_criterio;


    public Criterio(Integer id, Integer id_criterio, Integer id_apresentacao, String descricao_criterio, Integer peso_criterio) {
        this.id = id;
        this.id_criterio = id_criterio;
        this.id_apresentacao = id_apresentacao;
        this.descricao_criterio = descricao_criterio;
        this.peso_criterio = peso_criterio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_criterio() {
        return id_criterio;
    }

    public void setId_criterio(Integer id_criterio) {
        this.id_criterio = id_criterio;
    }

    public Integer getId_apresentacao() {
        return id_apresentacao;
    }

    public void setId_apresentacao(Integer id_apresentacao) {
        this.id_apresentacao = id_apresentacao;
    }

    public String getDescricao_criterio() {
        return descricao_criterio;
    }

    public void setDescricao_criterio(String descricao_criterio) {
        this.descricao_criterio = descricao_criterio;
    }

    public Integer getPeso_criterio() {
        return peso_criterio;
    }

    public void setPeso_criterio(Integer peso_criterio) {
        this.peso_criterio = peso_criterio;
    }
}
