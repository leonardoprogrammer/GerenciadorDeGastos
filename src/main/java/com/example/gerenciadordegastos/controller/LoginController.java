package com.example.gerenciadordegastos.controller;

import com.example.gerenciadordegastos.PainelController;
import com.example.gerenciadordegastos.business.SessionBeanAutenticacao;
import com.example.gerenciadordegastos.business.SessionBeanUsuario;
import com.example.gerenciadordegastos.model.entity.Usuario;
import com.example.gerenciadordegastos.util.GFAlert;
import com.example.gerenciadordegastos.util.LoadScreen;
import com.example.gerenciadordegastos.vo.AutenticacaoVO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private SessionBeanAutenticacao sessionBeanAutenticacao = new SessionBeanAutenticacao();
    private SessionBeanUsuario sessionBeanUsuario = new SessionBeanUsuario();
    private AutenticacaoVO auth;
    private Usuario usuario;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private VBox vBox;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Label lblRecuperarAcesso;

    @FXML
    private Button btnEntrar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        btnEntrar.setOnMouseClicked(event -> {
            entrar();
        });

        anchorPane.setOnKeyPressed((KeyEvent t) -> {
            if (t.getCode() == KeyCode.ENTER) {
                entrar();
            }
        });

        lblRecuperarAcesso.setOnMouseClicked(event -> {
            //
        });
    }

    public void entrar() {
        if (isCamposValidos()) {
            auth = sessionBeanAutenticacao.checkLogin(txtUsername.getText(), txtPassword.getText());
            if (auth.isSucess()) {
                usuario = sessionBeanUsuario.recuperarUsuarioPorId(auth.getIdRetorno());

                try {
                    LoadScreen.openWindow("ui/painel.fxml", new PainelController(usuario));

                    Stage thisWindow = (Stage) btnEntrar.getScene().getWindow();
                    thisWindow.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                GFAlert.makeAlertError("Login incorreto!");
            }
        }
    }

    public boolean isCamposValidos() {
        if ((txtUsername.getText() == null || txtUsername.getText().trim().isEmpty())
                || (txtPassword.getText() == null || txtPassword.getText().trim().isEmpty())) {
            GFAlert.makeAlertWarning("Preencha todos os campos!");
            return false;
        }
        return true;
    }
}
