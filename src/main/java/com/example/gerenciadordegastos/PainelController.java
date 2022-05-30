package com.example.gerenciadordegastos;

import com.example.gerenciadordegastos.business.SessionBeanRenda;
import com.example.gerenciadordegastos.business.SessionBeanUsuario;
import com.example.gerenciadordegastos.entity.Usuario;
import com.example.gerenciadordegastos.enums.ThemeMode;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PainelController implements Initializable {

    @FXML
    private Label lblTitulo;

    @FXML
    private ImageView imgLightMode;

    @FXML
    private ImageView imgClose;

    @FXML
    private Label lblMenu;

    @FXML
    private Label lblMenuClose;

    @FXML
    private Button btnTimeline;

    @FXML
    private Button btnGastos;

    @FXML
    private Button btnRenda;

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnRelatorios;

    @FXML
    private AnchorPane sidebar;

    // ELEMENTOS DO SIDEBAR

    @FXML
    private Button btnInicio;

    @FXML
    private Button btnPerfil;

    @FXML
    private Button btnPreferencias;

    @FXML
    private Button btnSair;

    @FXML
    private StackPane stackPane;

    private SessionBeanUsuario sessionBeanUsuario = new SessionBeanUsuario();
    private SessionBeanRenda sessionBeanRenda = new SessionBeanRenda();
    private ThemeMode themeMode;
    private Usuario usuario;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblMenuClose.setVisible(false);

        carregarFragment("ui/frgTimeline.fxml");

        alterarTema(ThemeMode.LIGHT_MODE);

        imgClose.setOnMouseClicked(event -> {
            System.exit(0);
        });

        imgLightMode.setOnMouseClicked(event -> {
            alterarTema(null);
        });

        sidebar.setTranslateX(-176);
        lblMenu.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(sidebar);

            slide.setToX(0);
            slide.play();

            sidebar.setTranslateX(-176);

            slide.setOnFinished((ActionEvent e) -> {
                lblMenu.setVisible(false);
                lblMenuClose.setVisible(true);
            });
        });

        lblMenuClose.setOnMouseClicked(event -> {
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(0.4));
            slide.setNode(sidebar);

            slide.setToX(-176);
            slide.play();

            sidebar.setTranslateX(0);

            slide.setOnFinished((ActionEvent e) -> {
                lblMenu.setVisible(true);
                lblMenuClose.setVisible(false);
            });
        });

        btnTimeline.setOnMouseClicked(event -> {
            carregarFragment("ui/frgTimeline.fxml");
        });

        btnGastos.setOnMouseClicked(event -> {
            carregarFragment("ui/frgGastos.fxml");
        });

        btnRenda.setOnMouseClicked(event -> {
            carregarFragment("ui/frgRenda.fxml");
        });

        btnDashboard.setOnMouseClicked(event -> {
            carregarFragment("ui/frgDashboard.fxml");
        });

        btnRelatorios.setOnMouseClicked(event -> {
            carregarFragment("ui/frgRelatorios.fxml");
        });

        btnInicio.setOnMouseClicked(event -> {
            carregarFragment("ui/frgTimeline.fxml");
        });

        btnPerfil.setOnMouseClicked(event -> {
            carregarFragment("ui/frgPerfil.fxml");
        });

        btnPreferencias.setOnMouseClicked(event -> {
            carregarFragment("ui/frgPreferencias.fxml");
        });

        btnSair.setOnMouseClicked(event -> {
            // DESLOGA O USUÁRIO E VOLTA PARA A TELA DE LOGIN
        });
    }

    private void carregarFragment(String fragment) {
        try {
            Parent fxml = FXMLLoader.load(getClass().getResource(fragment));
            stackPane.getChildren().removeAll();
            stackPane.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(PainelController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void alterarTema(ThemeMode tema) {
        if (tema != null) {
            themeMode = tema;
        } else {
            switch (themeMode) {
                case LIGHT_MODE:
                    imgLightMode.setImage(new Image(getClass().getResource("icons/moon_symbol_30px.png").toString()));
                    themeMode = ThemeMode.DARK_MODE;
                    break;
                case DARK_MODE:
                    imgLightMode.setImage(new Image(getClass().getResource("icons/sun_30px.png").toString()));
                    themeMode = ThemeMode.LIGHT_MODE;
                    break;
            }
        }
    }
}