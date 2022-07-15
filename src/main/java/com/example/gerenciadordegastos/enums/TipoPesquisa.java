package com.example.gerenciadordegastos.enums;

public enum TipoPesquisa {

    REFERENCIA(1, "Referência"),
    PERIODO(2, "Período");

    public final int id;
    public final String descricao;

    TipoPesquisa(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoPesquisa get(int id) {
        for (TipoPesquisa t : TipoPesquisa.values()) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }
}
