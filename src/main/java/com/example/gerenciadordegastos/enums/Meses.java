package com.example.gerenciadordegastos.enums;

public enum Meses {

    JANEIRO(1, "Janeiro", "JAN"),
    FEVEREIRO(2, "Fevereiro", "FEV"),
    MARCO(3, "Março", "MAR"),
    ABRIL(4, "Abril", "ABR"),
    MAIO(5, "Maio", "MAI"),
    JUNHO(6, "Junho", "JUN"),
    JULHO(7, "Julho", "JUL"),
    AGOSTO(8, "Agosto", "AGO"),
    SETEMBRO(9, "Setembro", "SET"),
    OUTRUBRO(10, "Outubro", "OUT"),
    NOVEMBRO(11, "Novembro", "NOV"),
    DEZEMBRO(12, "Dezembro", "DEZ");

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
