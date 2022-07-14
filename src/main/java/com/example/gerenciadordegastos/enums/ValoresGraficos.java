package com.example.gerenciadordegastos.enums;

public enum ValoresGraficos {

    GASTOS(1, "Gastos"),
    RENDAS(2, "Rendas"),
    GASTOS_E_RENDAS(3, "Gastos e Rendas");

    public final int id;
    public final String descricao;

    ValoresGraficos(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public static ValoresGraficos get(int id) {
        for (ValoresGraficos t : ValoresGraficos.values()) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }
}
