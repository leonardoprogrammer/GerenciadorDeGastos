package com.example.gerenciadordegastos.vo;

import com.example.gerenciadordegastos.enums.TipoMovimento;

import java.sql.Timestamp;
import java.util.Date;

public class MovimentoFinanceiroVO {
    private TipoMovimento tipo;
    private String titulo;
    private double valor;
    private Date data;
    private String descricao;
    private Timestamp dataAdd;

    public MovimentoFinanceiroVO() {

    }

    public MovimentoFinanceiroVO(TipoMovimento tipo, String titulo, double valor, Date data, String descricao, Timestamp dataAdd) {
        this.tipo = tipo;
        this.titulo = titulo;
        this.valor = valor;
        this.data = data;
        this.descricao = descricao;
        this.dataAdd = dataAdd;
    }

    public TipoMovimento getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimento tipo) {
        this.tipo = tipo;
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

    public Timestamp getDataAdd() {
        return dataAdd;
    }

    public void setDataAdd(Timestamp dataAdd) {
        this.dataAdd = dataAdd;
    }
}
