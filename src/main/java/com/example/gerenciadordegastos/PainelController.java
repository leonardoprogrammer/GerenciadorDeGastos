package com.example.gerenciadordegastos;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class PainelController implements Initializable {

    @FXML
    private ImageView imgClose;

    @FXML
    private Label lblMenu;

    @FXML
    private Label lblMenuClose;

    @FXML
    private Button btnGastos;

    @FXML
    private Button btnRenda;

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnRelatorios;

    //adicionar botões de cima

    @FXML
    private AnchorPane slider;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblMenuClose.setVisible(false);

        imgClose.setOnMouseClicked(event -> {
            System.exit(0);
        });

        slider.setTranslateX(-176);
        lblMenu.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(0);
            slide.play();

            slider.setTranslateX(-176);

            slide.setOnFinished((ActionEvent e) -> {
                lblMenu.setVisible(false);
                lblMenuClose.setVisible(true);
            });
        });

        lblMenuClose.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(slider);

            slide.setToX(-176);
            slide.play();

            slider.setTranslateX(0);

            slide.setOnFinished((ActionEvent e) -> {
                lblMenu.setVisible(true);
                lblMenuClose.setVisible(false);
            });
        });

        btnGastos.setOnMouseClicked(event -> {
            //
        });

        btnRenda.setOnMouseClicked(event -> {
            //
        });

        btnDashboard.setOnMouseClicked(event -> {
            //
        });

        btnRelatorios.setOnMouseClicked(event -> {
            //
        });
    }
}