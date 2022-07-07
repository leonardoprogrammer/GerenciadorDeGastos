package com.example.gerenciadordegastos.util.exception;

import javafx.scene.control.Alert;

public class GFException extends Exception {

    /**
     * Cria um dialog de ERRO com título e mensagem
     * @param title
     * @param message
     */
    public GFException(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.show();
    }
}
