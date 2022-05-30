package com.example.gerenciadordegastos.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class Renda {
    private long id;
    private long idUsuario;
<<<<<<< Updated upstream
    private long sequencia;
=======
>>>>>>> Stashed changes
    private String titulo;
    private double valor;
    private Date data;
    private String descricao;
<<<<<<< Updated upstream
    private Calendar dtaAdd;
    private Calendar dtaAlt;
=======
    private Timestamp dtaAdd;
    private Timestamp dtaAlt;
>>>>>>> Stashed changes

    public Renda() {

    }

<<<<<<< Updated upstream
    public Renda(long id, long idUsuario, long sequencia, String titulo, double valor, Calendar data, String descricao, Calendar dtaAdd, Calendar dtaAlt) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.sequencia = sequencia;
=======
    public Renda(long id, long idUsuario, String titulo, double valor, Date data, String descricao, Timestamp dtaAdd, Timestamp dtaAlt) {
        this.id = id;
        this.idUsuario = idUsuario;
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
    public long getSequencia() {
        return sequencia;
    }

    public void setSequencia(long sequencia) {
        this.sequencia = sequencia;
    }

=======
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
    public Calendar getDtaAlt() {
        return dtaAlt;
    }

    public void setDtaAlt(Calendar dtaAlt) {
=======
    public Timestamp getDtaAlt() {
        return dtaAlt;
    }

    public void setDtaAlt(Timestamp dtaAlt) {
>>>>>>> Stashed changes
        this.dtaAlt = dtaAlt;
    }
}
