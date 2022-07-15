package com.example.gerenciadordegastos.controller;

import com.example.gerenciadordegastos.util.Constantes;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class SobreController implements Initializable {

    private static String linkGithub = "https://github.com/leonardoprogrammer";
    private static String linkLinkedin = "https://www.linkedin.com/in/leonardocesarsilva/";

    @FXML
    private Label lblNomeAplicacao;

    @FXML
    private Label lblVersaoAplicacao;

    @FXML
    private Label lblGithub;

    @FXML
    private Label lblLinkedin;

    @FXML
    private Label lblLocation;

    @FXML
    private Label lblTextDescricao;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblNomeAplicacao.setText(Constantes.NAME_APPLICATION);
        lblVersaoAplicacao.setText("Versão: " + Constantes.VERSION_APPLICATION);

        lblTextDescricao.setText("Desenvolvedor Java\n" +
                "Formado em Análise e Desenvolvimento de Sistemas\n" +
                "Criei este software para fins de prática e estudo\n" +
                "Sinta-se a vontade para usá-lo da forma que melhor lhe agradar");

        lblGithub.setOnMouseClicked(event -> {
            abrirLink(linkGithub);
        });

        lblLinkedin.setOnMouseClicked(event -> {
            abrirLink(linkLinkedin);
        });
    }

    public SobreController() {

    }

    public void abrirLink(String link) {
        try {
            Desktop.getDesktop().browse(new URL(link).toURI());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
