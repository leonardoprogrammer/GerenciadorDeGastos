package com.example.gerenciadordegastos.controller;

import com.example.gerenciadordegastos.business.SessionBeanAutenticacao;
import com.example.gerenciadordegastos.business.SessionBeanUsuario;
import com.example.gerenciadordegastos.entity.Usuario;
import com.example.gerenciadordegastos.vo.AutenticacaoVO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private SessionBeanAutenticacao sessionBeanAutenticacao;
    private SessionBeanUsuario sessionBeanUsuario;
    private AutenticacaoVO auth;
    private Usuario usuario;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnEntrar;

    @FXML
    private Button btnCriarConta;

    @FXML
    private Button btnRecuperarLogin;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnEntrar.setOnMouseClicked(event -> {
            if (isCamposValidos()) {
                auth = sessionBeanAutenticacao.checkLogin(txtUsername.getText(), txtPassword.getText());
                if (auth.isSucess()) {
                    usuario = sessionBeanUsuario.recuperarUsuarioPorId(auth.getIdRetorno());
                    //fecha tela de login e abre painel da aplicação
                } else {
                    //exibe msg
                }
            }
        });

        btnCriarConta.setOnMouseClicked(event -> {
            //
        });

        btnRecuperarLogin.setOnMouseClicked(event -> {
            //
        });
    }

    public boolean isCamposValidos() {
        if ((txtUsername.getText() == null || txtUsername.getText().trim().isEmpty())
                || (txtPassword.getText() == null || txtPassword.getText().trim().isEmpty())) {
            //exibe msg "Preencha todos os campos!"
            return false;
        }
        return true;
    }
}
