package com.example.gerenciadordegastos.entity;

import java.util.Calendar;

public class Renda {
    private long id;
    private String titulo;
    private double valor;
    private Calendar data;
    private String descricao;
    private Calendar dtaAdd;

    public Renda() {

    }

    public Renda(long id, String titulo, double valor, Calendar data, String descricao, Calendar dtaInc) {
        this.id = id;
        this.titulo = titulo;
        this.valor = valor;
        this.data = data;
        this.descricao = descricao;
        this.dtaAdd = dtaInc;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Calendar getDtaAdd() {
        return dtaAdd;
    }

    public void setDtaAdd(Calendar dtaAdd) {
        this.dtaAdd = dtaAdd;
    }
}
