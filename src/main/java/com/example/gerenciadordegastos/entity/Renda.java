package com.example.gerenciadordegastos.entity;

import java.util.Calendar;

public class Renda {
    private long id;
    private long idUsuario;
    private long sequencia;
    private String titulo;
    private double valor;
    private Calendar data;
    private String descricao;
    private Calendar dtaAdd;
    private Calendar dtaAlt;

    public Renda() {

    }

    public Renda(long id, long idUsuario, long sequencia, String titulo, double valor, Calendar data, String descricao, Calendar dtaAdd, Calendar dtaAlt) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.sequencia = sequencia;
        this.titulo = titulo;
        this.valor = valor;
        this.data = data;
        this.descricao = descricao;
        this.dtaAdd = dtaAdd;
        this.dtaAlt = dtaAlt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public long getSequencia() {
        return sequencia;
    }

    public void setSequencia(long sequencia) {
        this.sequencia = sequencia;
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

    public Calendar getDtaAlt() {
        return dtaAlt;
    }

    public void setDtaAlt(Calendar dtaAlt) {
        this.dtaAlt = dtaAlt;
    }
}
