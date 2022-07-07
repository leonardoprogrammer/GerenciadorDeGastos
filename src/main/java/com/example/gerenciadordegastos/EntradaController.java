package com.example.gerenciadordegastos;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EntradaController implements Initializable {

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnCadastro;

    @FXML
    private StackPane stackPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        carregarFragment("ui/spLogin.fxml");
        btnLogin.setStyle("-fx-background-color: #19d181;");

        btnLogin.setOnMouseClicked(event -> {
            carregarFragment("ui/spLogin.fxml");
            btnLogin.setStyle("-fx-background-color: #19d181;");
            btnCadastro.setStyle("-fx-background-color: #0c2b54;");
        });

        btnCadastro.setOnMouseClicked(event -> {
            carregarFragment("ui/spCadastro.fxml");
            btnCadastro.setStyle("-fx-background-color: #19d181;");
            btnLogin.setStyle("-fx-background-color: #0c2b54;");
        });

    }

    public EntradaController() {

    }

    private void carregarFragment(String fragment) {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource(fragment));
            stackPane.getChildren().removeAll();
            stackPane.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(EntradaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
