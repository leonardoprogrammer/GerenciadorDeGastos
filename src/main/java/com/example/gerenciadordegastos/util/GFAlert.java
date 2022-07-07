package com.example.gerenciadordegastos.util;

import javafx.scene.control.Alert;

public class GFAlert {

    /**
     * Cria um dialog de INFORMAÇÃO com mensagem
     * @param message
     */
    public static void makeAlertInfo(String message) {
        new Alert(Alert.AlertType.INFORMATION, message).show();
    }

    /**
     * Cria um dialog de ATENÇÃO com mensagem
     * @param message
     */
    public static void makeAlertWarning(String message) {
        new Alert(Alert.AlertType.WARNING, message).show();
    }

    /**
     * Cria um dialog de ERRO com mensagem
     * @param message
     */
    public static void makeAlertError(String message) {
        new Alert(Alert.AlertType.ERROR, message).show();
    }
}
