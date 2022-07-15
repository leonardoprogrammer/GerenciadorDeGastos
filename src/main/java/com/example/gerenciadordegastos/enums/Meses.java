package com.example.gerenciadordegastos.enums;

public enum Meses {

    JANEIRO(1, "Janeiro", "Jan"),
    FEVEREIRO(2, "Fevereiro", "Fev"),
    MARCO(3, "Março", "Mar"),
    ABRIL(4, "Abril", "Abril"),
    MAIO(5, "Maio", "Maio"),
    JUNHO(6, "Junho", "Jun"),
    JULHO(7, "Julho", "Jul"),
    AGOSTO(8, "Agosto", "Ago"),
    SETEMBRO(9, "Setembro", "Set"),
    OUTRUBRO(10, "Outubro", "Out"),
    NOVEMBRO(11, "Novembro", "Nov"),
    DEZEMBRO(12, "Dezembro", "Dez");

    private final int id;
    private final String nome;
    private final String abreviacao;

    Meses(int id, String nome, String abreviacao) {
        this.id = id;
        this.nome = nome;
        this.abreviacao = abreviacao;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getAbreviacao() {
        return abreviacao;
    }

    public static Meses get(int id) {
        for (Meses t : Meses.values()) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }
}
