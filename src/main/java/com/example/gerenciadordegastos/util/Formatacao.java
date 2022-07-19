package com.example.gerenciadordegastos.util;

import java.text.DecimalFormat;

public class Formatacao {

    public static String converterDoubleParaReal(double valor) {
        DecimalFormat df = new DecimalFormat("###,###.00");
        return df.format(valor);
    }
}
