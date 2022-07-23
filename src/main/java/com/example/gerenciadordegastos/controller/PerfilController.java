package com.example.gerenciadordegastos.controller;

import com.example.gerenciadordegastos.business.SessionBeanUsuario;
import com.example.gerenciadordegastos.model.entity.Usuario;
import com.example.gerenciadordegastos.util.GFAlert;
import com.example.gerenciadordegastos.util.MascarasFX;
import com.example.gerenciadordegastos.util.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;

public class PerfilController implements Initializable {

    private SessionBeanUsuario sessionBeanUsuario = new SessionBeanUsuario();
    private Usuario usuario;
    private Usuario usuarioVO;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCpf;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtTelefone;

    @FXML
    private DatePicker dtNascimento;

    @FXML
    private Button btnAlterarSenha;

    @FXML
    private Button btnSalvar;

    @FXML
    private Label txtUltimaAtt;

    // <editor-fold desc="Pane Alterar Senha">
    @FXML
    private Pane paneAltPassword;

    @FXML
    private PasswordField txtSenhaAtual;

    @FXML
    private PasswordField txtSenhaNova;

    @FXML
    private PasswordField txtSenhaNovaValid;

    @FXML
    private Button btnCancelarSenha;

    @FXML
    private Button btnAlterar;
    // </editor-fold>

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        atualizarCampos();
        desativarPaneSenha();

        if (usuario.getDtaAlt() != null) {
            atualizarDataAtt();
        } else {
            txtUltimaAtt.setVisible(false);
        }
        definirDataMaximaDtNascimento();

        btnSalvar.setOnMouseClicked(event -> {
            salvar();
        });

        btnAlterarSenha.setOnMouseClicked(event -> {
            ativarPaneSenha();
        });

        btnCancelarSenha.setOnMouseClicked(event -> {
            desativarPaneSenha();
        });

        btnAlterar.setOnMouseClicked(event -> {
            alterarSenha();
        });

        MascarasFX.mascaraData(dtNascimento);
    }

    public PerfilController(Usuario usuario) {
        this.usuario = usuario;
    }

    public void salvar() {
        populaVO();
        if (validationsPerfil()) {
            try {
                sessionBeanUsuario.alterarUsuario(usuarioVO);
                GFAlert.makeAlertInfo("Suas informações pessoais foram alteradas!");

                atualizarCampos();
                atualizarDataAtt();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void alterarSenha() {
        populaVO();
        if (validationsSenha()) {
            try {
                sessionBeanUsuario.alterarSenha(usuarioVO);
                desativarPaneSenha();
                GFAlert.makeAlertInfo("Sua senha foi alterada!");

                txtSenhaAtual.clear();
                txtSenhaNova.clear();
                txtSenhaNovaValid.clear();
                atualizarCampos();
                atualizarDataAtt();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean validationsPerfil() {
        StringBuilder invalid = new StringBuilder("");
        if (Utils.isNullOrEmpty(usuarioVO.getNome()))
            invalid.append("\n- Nome");
        if (Utils.isNullOrEmpty(usuarioVO.getEmail()))
            invalid.append("\n- Email");

        if (!Utils.isNullOrEmpty(invalid.toString())) {
            GFAlert.makeAlertWarning("Estes campos devem ser preenchidos:\n" + invalid.toString());
            return false;
        }
        return true;
    }

    public boolean validationsSenha() {
        StringBuilder invalid = new StringBuilder("");
        if (Utils.isNullOrEmpty(txtSenhaAtual.getText())) {
            invalid.append("\nPreencha o campo Senha atual");
        } else if (!usuario.getPassword().equals(txtSenhaAtual.getText())) {
            invalid.append("\nSenha atual está incorreta");
        }
        if (!Utils.isNullOrEmpty(txtSenhaNova.getText()) && !Utils.isNullOrEmpty(txtSenhaNovaValid.getText())) {
            if (!txtSenhaNova.getText().equals(txtSenhaNovaValid.getText())) {
                invalid.append("\nRepita a nova senha corretamente");
            }
        } else {
            if (Utils.isNullOrEmpty(txtSenhaNova.getText())) {
                invalid.append("\nPrencha o campo Nova senha");
            }
            if (Utils.isNullOrEmpty(txtSenhaNovaValid.getText())) {
                invalid.append("\nRepita a nova senha");
            }
        }

        if (!Utils.isNullOrEmpty(invalid.toString())) {
            GFAlert.makeAlertWarning(invalid.toString());
            return false;
        }
        return true;
    }

    public void populaVO() {
        usuarioVO = new Usuario();
        usuarioVO.setId(usuario.getId());
        usuarioVO.setUsername(txtUsername.getText().trim());
        usuarioVO.setPassword(txtSenhaNova.getText());
        usuarioVO.setNome(txtNome.getText().trim());
        usuarioVO.setCpf(!Utils.isNullOrEmpty(txtCpf.getText().trim()) ? txtCpf.getText().trim() : null);
        usuarioVO.setEmail(!Utils.isNullOrEmpty(txtEmail.getText().trim()) ? txtEmail.getText().trim() : null);
        usuarioVO.setTelefone(!Utils.isNullOrEmpty(txtTelefone.getText().trim()) ? txtTelefone.getText().trim() : null);
        usuarioVO.setNascimento(dtNascimento.getValue() != null ? Date.valueOf(dtNascimento.getValue()) : null);
    }

    public void atualizarCampos() {
        usuario = sessionBeanUsuario.recuperarUsuarioPorId(usuario.getId());
        if (!Utils.isNullOrEmpty(usuario.getUsername()))
            txtUsername.setText(usuario.getUsername());
        if (!Utils.isNullOrEmpty(usuario.getNome()))
            txtNome.setText(usuario.getNome());
        if (!Utils.isNullOrEmpty(usuario.getCpf()))
            txtCpf.setText(usuario.getCpf());
        if (!Utils.isNullOrEmpty(usuario.getEmail()))
            txtEmail.setText(usuario.getEmail());
        if (!Utils.isNullOrEmpty(usuario.getTelefone()))
            txtTelefone.setText(usuario.getTelefone());
        if (usuario.getNascimento() != null)
            dtNascimento.setValue(usuario.getNascimento().toLocalDate());
    }

    public void atualizarDataAtt() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        txtUltimaAtt.setText("Última atualização: " + sdf.format(usuario.getDtaAlt()));
        txtUltimaAtt.setVisible(true);
    }

    public void definirDataMaximaDtNascimento() {
        Calendar now = Calendar.getInstance();
        LocalDate maxDate = LocalDate.of(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
        dtNascimento.setDayCellFactory(day -> {
            return new DateCell() {
                @Override
                public void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);
                    setDisable(item.isAfter(maxDate));
                }
            };
        });
    }

    public void ativarPaneSenha() {
        paneAltPassword.setVisible(true);
        paneAltPassword.setDisable(false);
    }

    public void desativarPaneSenha() {
        paneAltPassword.setVisible(false);
        paneAltPassword.setDisable(true);
    }
}
