package com.example.gerenciadordegastos.controller;

import com.example.gerenciadordegastos.util.GFAlert;
import com.example.gerenciadordegastos.business.SessionBeanAutenticacao;
import com.example.gerenciadordegastos.business.SessionBeanUsuario;
import com.example.gerenciadordegastos.model.entity.Usuario;
import com.example.gerenciadordegastos.vo.AutenticacaoVO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class NovaContaController implements Initializable {

    private SessionBeanAutenticacao sessionBeanAutenticacao = new SessionBeanAutenticacao();
    private SessionBeanUsuario sessionBeanUsuario = new SessionBeanUsuario();
    private AutenticacaoVO auth;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private VBox vBox;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private PasswordField txtPasswordValid;

    @FXML
    private Button btnCriarConta;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        btnCriarConta.setOnMouseClicked(event -> {
            cadastrar();
        });

        anchorPane.setOnKeyPressed((KeyEvent t) -> {
            if (t.getCode() == KeyCode.ENTER) {
                cadastrar();
            }
        });
    }

    public void cadastrar() {
        if (isCamposValidos()) {
            auth = sessionBeanAutenticacao.checkUserExists(txtUsername.getText().trim());
            if (auth.isSucess()) {
                Usuario usuario = new Usuario(txtUsername.getText().trim(), txtPassword.getText().trim(), txtName.getText().trim(), txtEmail.getText().trim());
                sessionBeanUsuario.registrarUsuario(usuario);
                GFAlert.makeAlertInfo("Parabéns!\nSeu cadastro foi realizado!");
            } else {
                GFAlert.makeAlertWarning(auth.getMsgRetorno());
            }
        }
    }

    public boolean isCamposValidos() {
        if ((txtUsername.getText() == null || txtUsername.getText().trim().isEmpty())
                || (txtName.getText() == null || txtName.getText().trim().isEmpty())
                || (txtEmail.getText() == null || txtEmail.getText().trim().isEmpty())
                || (txtPassword.getText() == null || txtPassword.getText().trim().isEmpty()
                || (txtPasswordValid.getText() == null || txtPasswordValid.getText().trim().isEmpty()))) {
            GFAlert.makeAlertWarning("Preencha todos campos!");
            return false;
        }
        if (!txtPassword.getText().trim().equals(txtPasswordValid.getText().trim())) {
            GFAlert.makeAlertWarning("As senhas devem ser iguais!");
            return false;
        }
        return true;
    }
}
