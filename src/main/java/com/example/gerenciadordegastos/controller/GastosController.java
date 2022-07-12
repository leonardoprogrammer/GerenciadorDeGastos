package com.example.gerenciadordegastos.controller;

import com.example.gerenciadordegastos.business.SessionBeanGasto;
import com.example.gerenciadordegastos.model.entity.Gasto;
import com.example.gerenciadordegastos.model.entity.Usuario;
import com.example.gerenciadordegastos.util.GFAlert;
import com.example.gerenciadordegastos.util.Utils;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

public class GastosController implements Initializable {

    private SessionBeanGasto sessionBeanGasto = new SessionBeanGasto();
    private Usuario usuario;
    private List<Gasto> gastos = new ArrayList<>();
    private Gasto gasto;

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
    private TableView tableGastos = new TableView<Gasto>();

    //<editor-fold desc="Tabela de Gastos">
    @FXML
    private TableColumn<Gasto, String> columnTitulo;

    @FXML
    private TableColumn<Gasto, Double> columnValor;

    @FXML
    private TableColumn<Gasto, String> columnDescricao;

    @FXML
    private TableColumn<Gasto, Date> columnData;
    //</editor-fold>

    @FXML
    private Button btnAlterarGasto;

    @FXML
    private Button btnExcluirGasto;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnAlterarGasto.setDisable(true);
        btnExcluirGasto.setDisable(true);

        definirDataMaximaData();

        atualizarTabela();

        btnLimparCampos.setOnMouseClicked(event -> {
            limparCampos();
        });

        btnAdicionar.setOnMouseClicked(event -> {
            adicionar();
        });

        tableGastos.setOnMouseClicked(event -> {
            btnAlterarGasto.setDisable(false);
            btnExcluirGasto.setDisable(false);
        });

        btnAlterarGasto.setOnMouseClicked(event -> {
            alterarGasto();
        });

        btnExcluirGasto.setOnMouseClicked(event -> {
            excluirGasto();
        });
    }

    public GastosController(Usuario usuario) {
        this.usuario = usuario;
    }

    public void adicionar() {
        populaGasto();
        if (validationsGasto()) {
            try {
                sessionBeanGasto.registrarGasto(gasto);
                GFAlert.makeAlertInfo("Novo gasto adicionado!");
                limparCampos();
                gasto = new Gasto();
                atualizarTabela();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void alterarGasto() {
        //
    }

    public void excluirGasto() {
        try {
            Gasto gastoDelete = (Gasto) tableGastos.getSelectionModel().getSelectedItem();
            sessionBeanGasto.deletarGasto(gastoDelete.getId());
            btnAlterarGasto.setDisable(true);
            btnExcluirGasto.setDisable(true);
            atualizarTabela();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean validationsGasto() {
        StringBuilder invalid = new StringBuilder("");

        if (Utils.isNullOrEmpty(txtTitulo.getText().trim())) {
            invalid.append("\nInsira o título");
        }
        if (Utils.isNullOrEmpty(txtValor.getText().trim())) {
            invalid.append("\nInsira o valor");
        }
        if (dtData.getValue() == null) {
            invalid.append("\nInsira a data");
        }

        if (!Utils.isNullOrEmpty(invalid.toString())) {
            GFAlert.makeAlertWarning(invalid.toString());
            return false;
        }
        return true;
    }

    public void atualizarTabela() {
        gastos = sessionBeanGasto.recuperarGastosPorUsuario(usuario.getId());

        columnTitulo.setCellValueFactory(new PropertyValueFactory<Gasto, String>("titulo"));
        columnValor.setCellValueFactory(new PropertyValueFactory<Gasto, Double>("valor"));
        columnDescricao.setCellValueFactory(new PropertyValueFactory<Gasto, String>("descricao"));
        columnData.setCellValueFactory(new PropertyValueFactory<Gasto, Date>("data"));

        tableGastos.setItems(FXCollections.observableArrayList(gastos));
    }

    public void populaGasto() {
        gasto = new Gasto();
        gasto.setIdUsuario(usuario.getId());
        gasto.setTitulo(txtTitulo.getText().trim());
        gasto.setValor(!Utils.isNullOrEmpty(txtValor.getText()) ? Double.parseDouble(txtValor.getText().trim()) : 0);
        gasto.setData(dtData.getValue() != null ? Date.valueOf(dtData.getValue()) : null);
        gasto.setDescricao(txtDescricao.getText());
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

    public void limparCampos() {
        txtTitulo.clear();
        txtValor.clear();
        txtDescricao.clear();
    }
}
