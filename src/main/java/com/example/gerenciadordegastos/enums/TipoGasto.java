package com.example.gerenciadordegastos.enums;

public enum TipoGasto {

    OUTROS(0, "Outros", "more.png"),
    REFEICAO(1, "Refeição", "lunchbox.png"),
    COMBUSTIVEL(2, "Combustível", "gas_station.png"),
    CONTAS_DA_CASA(3, "Contas da casa", "document.png"),
    REFORMAS_OU_CONSERTOS(4, "Reformas ou consertos", "wrench.png"),
    INVESTIMENTO(5, "Investimento de negócio", "shop.png"),
    VIAGEM(6, "Viagem", "airplane.png"),
    DIVIDA(7, "Dívida", "document.png"),
    DESPESAS_MEDICAS(8, "Despesas médicas", "hospital.png"),
    EDUCACAO(9, "Educação", "graduation_cap.png"),
    COMPRAS(10, "Compras", "shopping_bag.png"),
    CREDITOS(11, "Créditos da operadora", "mobile_payment.png"),
    FATURA_CARTAO(12, "Fatura de cartão", "debit_card.png");

    private final int id;
    private final String descricao;
    private final String srcImage;

    TipoGasto(int id, String descricao, String srcImage) {
        this.id = id;
        this.descricao = descricao;
        this.srcImage = srcImage;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getSrcImage() {
        return srcImage;
    }

    public static TipoGasto get(int id) {
        for (TipoGasto t : TipoGasto.values()) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }
}
