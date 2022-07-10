package com.example.gerenciadordegastos;

import com.example.gerenciadordegastos.business.SessionBeanRenda;
import com.example.gerenciadordegastos.business.SessionBeanUsuario;
import com.example.gerenciadordegastos.controller.GastosController;
import com.example.gerenciadordegastos.controller.PerfilController;
import com.example.gerenciadordegastos.model.entity.Usuario;
import com.example.gerenciadordegastos.enums.ThemeMode;
import com.example.gerenciadordegastos.util.Constantes;
import com.example.gerenciadordegastos.util.LoadScreen;
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
import javafx.stage.Stage;
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

        lblTitulo.setText("Olá, " + usuario.getNome());

        carregarFragment("ui/frgTimeline.fxml", null);

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
            carregarFragment("ui/frgTimeline.fxml", null);
        });

        btnGastos.setOnMouseClicked(event -> {
            carregarFragment("ui/frgGastos.fxml", new GastosController());
        });

        btnRenda.setOnMouseClicked(event -> {
            carregarFragment("ui/frgRenda.fxml", null);
        });

        btnDashboard.setOnMouseClicked(event -> {
            carregarFragment("ui/frgDashboard.fxml", null);
        });

        btnRelatorios.setOnMouseClicked(event -> {
            carregarFragment("ui/frgRelatorios.fxml", null);
        });

        btnInicio.setOnMouseClicked(event -> {
            carregarFragment("ui/frgTimeline.fxml", null);
        });

        btnPerfil.setOnMouseClicked(event -> {
            carregarFragment("ui/frgPerfil.fxml", new PerfilController(usuario));
        });

        btnPreferencias.setOnMouseClicked(event -> {
            carregarFragment("ui/frgPreferencias.fxml", null);
        });

        btnSair.setOnMouseClicked(event -> {
            try {
                LoadScreen.openWindow("ui/login.fxml", new EntradaController(), Constantes.HEIGHT_WINDOW_LOGIN, Constantes.WIDTH_WINDOW_LOGIN);
                Stage thisWindow = (Stage) btnSair.getScene().getWindow();
                thisWindow.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    public PainelController(Usuario usuario) {
        this.usuario = usuario;
    }

    private void carregarFragment(String fragment, Object constructor) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fragment));
            if (constructor != null) {
                fxmlLoader.setController(constructor);
            }
            Parent fxml = fxmlLoader.load();
            stackPane.getChildren().removeAll();
            stackPane.getChildren().setAll(fxml);
        } catch (IOException ex) {
            Logger.getLogger(PainelController.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
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