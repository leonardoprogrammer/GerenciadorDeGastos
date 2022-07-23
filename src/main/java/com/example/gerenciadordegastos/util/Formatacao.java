package com.example.gerenciadordegastos.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Formatacao {

    public static String converterDoubleParaReal(double valor) {
        DecimalFormat df = new DecimalFormat("###,##0.00");
        return df.format(valor);
    }

    public static String formatarData(Date data) {
        if (data == null)
            return null;

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(data);
    }
}
