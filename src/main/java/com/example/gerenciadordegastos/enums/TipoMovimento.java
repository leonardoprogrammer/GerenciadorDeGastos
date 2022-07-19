package com.example.gerenciadordegastos.enums;

public enum TipoMovimento {

    RENDA(1, "Renda", "Nova renda", "+"),
    GASTO(2, "Gasto", "Novo gasto", "-");

    public final int id;
    public final String titulo;
    public final String descricao;
    public final String simbolo;

    TipoMovimento(int id, String titulo, String descricao, String simbolo) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.simbolo = simbolo;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public static TipoMovimento get(int id) {
        for (TipoMovimento t : TipoMovimento.values()) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }
}
