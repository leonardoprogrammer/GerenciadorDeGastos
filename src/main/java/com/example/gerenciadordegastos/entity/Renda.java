package com.example.gerenciadordegastos.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class Renda {
    private long id;
    private long idUsuario;
    private String titulo;
    private double valor;
    private Date data;
    private String descricao;
    private Timestamp dtaAdd;
    private Timestamp dtaAlt;

    public Renda() {

    }

    public Renda(long id, long idUsuario, String titulo, double valor, Date data, String descricao, Timestamp dtaAdd, Timestamp dtaAlt) {
        this.id = id;
        this.idUsuario = idUsuario;
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Timestamp getDtaAdd() {
        return dtaAdd;
    }

    public void setDtaAdd(Timestamp dtaAdd) {
        this.dtaAdd = dtaAdd;
    }

    public Timestamp getDtaAlt() {
        return dtaAlt;
    }

    public void setDtaAlt(Timestamp dtaAlt) {
        this.dtaAlt = dtaAlt;
    }
}
