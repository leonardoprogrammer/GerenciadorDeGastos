package com.example.gerenciadordegastos.controller;

import com.example.gerenciadordegastos.business.SessionBeanRenda;
import com.example.gerenciadordegastos.model.entity.Renda;
import com.example.gerenciadordegastos.model.entity.Usuario;
import com.example.gerenciadordegastos.util.GFAlert;
import com.example.gerenciadordegastos.util.Utils;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class RendaController implements Initializable {

    private SessionBeanRenda sessionBeanRenda = new SessionBeanRenda();
    private Usuario usuario;
    private List<Renda> rendas = new ArrayList<>();;
    public Renda renda;

    @FXML
    private TextField txtTitulo;

    @FXML
    private TextField txtValor;

    @FXML
    private DatePicker dtData;

    @FXML
    private TextArea txtDescricao;

    @FXML
    private Button btnLimparCampos;

    @FXML
    private Button btnAdicionar;

    @FXML
    private TableView tableRendas = new TableView<Renda>();

    //<editor-fold desc="Tabela de Rendas">
    @FXML
    private TableColumn<Renda, String> columnTitulo;

    @FXML
    private TableColumn<Renda, Double> columnValor;

    @FXML
    private TableColumn<Renda, String> columnDescricao;

    @FXML
    private TableColumn<Renda, Date> columnData;
    //</editor-fold>

    @FXML
    private Button btnAlterarRenda;

    @FXML
    private Button btnExcluirRenda;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        desabilitarBotoesTabela(true);

        definirDataMaximaData();

        atualizarTabela();

        btnLimparCampos.setOnMouseClicked(event -> {
            limparCampos();
        });

        btnAdicionar.setOnMouseClicked(event -> {
            adicionar();
        });

        tableRendas.setOnMouseClicked(event -> {
            desabilitarBotoesTabela(false);
        });

        btnAlterarRenda.setOnMouseClicked(event -> {
            alterarRenda();
        });

        btnExcluirRenda.setOnMouseClicked(event -> {
            excluirRenda();
        });
    }

    public RendaController(Usuario usuario) {
        this.usuario = usuario;
    }

    public void adicionar() {
        populaRenda();
        if (validationsRenda()) {
            try {
                sessionBeanRenda.registrarRenda(renda);
                GFAlert.makeAlertInfo("Nova renda adicionada!");
                limparCampos();
                renda = new Renda();
                atualizarTabela();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void alterarRenda() {
        //
    }

    public void excluirRenda() {
        try {
            Renda rendaDelete = (Renda) tableRendas.getSelectionModel().getSelectedItem();
            sessionBeanRenda.deletarRenda(rendaDelete.getId());
            desabilitarBotoesTabela(true);
            atualizarTabela();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean validationsRenda() {
        StringBuilder invalid = new StringBuilder("");

        if (Utils.isNullOrEmpty(txtTitulo.getText().trim())) {
            invalid.append("\nInsira o título");
        }
        if (Utils.isNullOrEmpty(txtValor.getText().trim())) {
            invalid.append("\nInsira o valor");
        }
        if (dtData == null) {
            invalid.append("\nInsira a data");
        }

        if (!Utils.isNullOrEmpty(invalid.toString())) {
            GFAlert.makeAlertWarning(invalid.toString());
            return false;
        }
        return true;
    }

    public void atualizarTabela() {
        rendas = sessionBeanRenda.recuperarRendasPorUsuario(usuario.getId());

        columnTitulo.setCellValueFactory(new PropertyValueFactory<Renda, String>("titulo"));
        columnValor.setCellValueFactory(new PropertyValueFactory<Renda, Double>("valor"));
        columnDescricao.setCellValueFactory(new PropertyValueFactory<Renda, String>("descricao"));
        columnData.setCellValueFactory(new PropertyValueFactory<Renda, Date>("data"));

        tableRendas.setItems(FXCollections.observableArrayList(rendas));
    }

    public void populaRenda() {
        renda = new Renda();
        renda.setIdUsuario(usuario.getId());
        renda.setTitulo(txtTitulo.getText().trim());
        renda.setValor(!Utils.isNullOrEmpty(txtValor.getText()) ? Double.parseDouble(txtValor.getText().trim()) : 0);
        renda.setData(dtData.getValue() != null ? java.sql.Date.valueOf(dtData.getValue()) : null);
        renda.setDescricao(txtDescricao.getText());
    }

    public void definirDataMaximaData() {
        Calendar now = Calendar.getInstance();
        LocalDate maxDate = LocalDate.of(now.get(Calendar.YEAR), now.get(Calendar.MONTH) + 1, now.get(Calendar.DAY_OF_MONTH));
        dtData.setDayCellFactory(day -> {
            return new DateCell() {
                @Override
                public void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);
                    setDisable(item.isAfter(maxDate));
                }
            };
        });
        dtData.setValue(maxDate);
    }

    public void desabilitarBotoesTabela(boolean logica) {
        btnAlterarRenda.setDisable(logica);
        btnExcluirRenda.setDisable(logica);
    }

    public void limparCampos() {
        txtTitulo.clear();
        txtValor.clear();
        txtDescricao.clear();
    }
}
