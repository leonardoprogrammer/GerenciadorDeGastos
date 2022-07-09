package com.example.gerenciadordegastos.controller;

import com.example.gerenciadordegastos.business.SessionBeanUsuario;
import com.example.gerenciadordegastos.model.entity.Usuario;
import com.example.gerenciadordegastos.util.GFAlert;
import com.example.gerenciadordegastos.util.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (usuario.getDtaAlt() != null) {
            txtUltimaAtt.setText("Última atualização: " + usuario.getDtaAlt().toString());
            txtUltimaAtt.setVisible(true);
        } else {
            txtUltimaAtt.setVisible(false);
        }
        atualizarCampos();

        btnSalvar.setOnMouseClicked(event -> {
            salvar();
        });

        btnAlterarSenha.setOnMouseClicked(event -> {
            //
        });
    }

    public PerfilController(Usuario usuario) {
        this.usuario = usuario;
    }

    public void salvar() {
        populaVO();
        if (haAlteracao()) {
            if (validations())
                sessionBeanUsuario.alterarUsuario(usuarioVO);
        } else {
            GFAlert.makeAlertWarning("Não há alterações no cadastro!");
        }
    }

    public boolean validations() {
        StringBuilder invalid = new StringBuilder("");
        if (Utils.isNullOrEmpty(usuarioVO.getUsername()))
            invalid.append("\n- Nome de usuário");
        if (Utils.isNullOrEmpty(usuarioVO.getNome()))
            invalid.append("\n- Nome");

        if (!Utils.isNullOrEmpty(invalid.toString())) {
            GFAlert.makeAlertWarning("Estes campos devem ser preenchidos:\n" + invalid.toString());
            return false;
        }
        return true;
    }

    public boolean haAlteracao() {
        int alteracoes = 0;

        if (!usuarioVO.getUsername().equals(usuario.getUsername()))
            alteracoes++;
        if (!usuarioVO.getNome().equals(usuario.getNome()))
            alteracoes++;
        if (!usuarioVO.getCpf().equals(usuario.getCpf()))
            alteracoes++;
        if (!usuarioVO.getEmail().equals(usuario.getEmail()))
            alteracoes++;
        if (!usuarioVO.getTelefone().equals(usuario.getTelefone()))
            alteracoes++;
        if ((usuarioVO.getNascimento() != null && usuario.getNascimento() == null) || (usuarioVO.getNascimento() == null && usuario.getNascimento() != null)) {
            alteracoes++;
        } else if (usuarioVO.getNascimento() != null && usuario.getNascimento() != null) {
            if (usuarioVO.getNascimento().before(usuario.getNascimento()) || usuarioVO.getNascimento().after(usuario.getNascimento()))
                alteracoes++;
        }

        return alteracoes > 0;
    }

    public void populaVO() {
        usuarioVO = new Usuario();
        usuarioVO.setId(usuario.getId());
        usuarioVO.setUsername(txtUsername.getText().trim());
        usuarioVO.setNome(txtNome.getText().trim());
        usuarioVO.setCpf(!Utils.isNullOrEmpty(txtCpf.getText().trim()) ? txtCpf.getText().trim() : null);
        usuarioVO.setEmail(!Utils.isNullOrEmpty(txtEmail.getText().trim()) ? txtEmail.getText().trim() : null);
        usuarioVO.setTelefone(!Utils.isNullOrEmpty(txtTelefone.getText().trim()) ? txtTelefone.getText().trim() : null);
        usuarioVO.setNascimento(dtNascimento.getValue() != null ? Date.valueOf(dtNascimento.getValue()) : null);
    }

    public void atualizarCampos() {
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
}
