package com.example.gerenciadordegastos.controller;

import com.example.gerenciadordegastos.business.SessionBeanGasto;
import com.example.gerenciadordegastos.business.SessionBeanRenda;
import com.example.gerenciadordegastos.enums.Meses;
import com.example.gerenciadordegastos.enums.TipoMovimento;
import com.example.gerenciadordegastos.enums.TipoPesquisa;
import com.example.gerenciadordegastos.model.entity.Gasto;
import com.example.gerenciadordegastos.model.entity.Preferencias;
import com.example.gerenciadordegastos.model.entity.Renda;
import com.example.gerenciadordegastos.model.entity.Usuario;
import com.example.gerenciadordegastos.util.Formatacao;
import com.example.gerenciadordegastos.util.GFAlert;
import com.example.gerenciadordegastos.util.Utils;
import com.example.gerenciadordegastos.vo.MovimentoFinanceiroVO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class TimelineController implements Initializable {

    private SessionBeanGasto sessionBeanGasto = new SessionBeanGasto();
    private SessionBeanRenda sessionBeanRenda = new SessionBeanRenda();
    private Preferencias preferencias;
    private Usuario usuario;
    private List<Gasto> gastos = new ArrayList<>();
    private List<Renda> rendas = new ArrayList<>();
    private LocalDate dataInicioAux;
    private LocalDate dataFinalAux;
    private List<MovimentoFinanceiroVO> movimentos = new ArrayList<>();

    @FXML
    private ChoiceBox<TipoPesquisa> choicePesquisa = new ChoiceBox<>();

    @FXML
    private ChoiceBox<Meses> choiceReferencia = new ChoiceBox<>();

    @FXML
    private DatePicker dtInicioPeriodo;

    @FXML
    private DatePicker dtFimPeriodo;

    @FXML
    private Button btnPesquisar;

    @FXML
    private ListView<HBox> listTimeline;

    @FXML
    private TextField txtTotalGastos;

    @FXML
    private TextField txtTotalRendas;

    @FXML
    private TextField txtRemanescente;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choicePesquisa.setItems(FXCollections.observableArrayList(TipoPesquisa.values()));
        choiceReferencia.setItems(FXCollections.observableArrayList(Meses.values()));

        // faz validações das preferências (cores...)
        definirDataMaxima();

        /*choiceReferencia.setVisible(false);
        dtInicioPeriodo.setVisible(false);
        dtFimPeriodo.setVisible(false);*/

        btnPesquisar.setOnMouseClicked(event -> {
            if (validacoesCampos())
                pesquisar();
        });
    }

    public TimelineController(Usuario usuario, Preferencias preferencias) {
        this.usuario = usuario;
        this.preferencias = preferencias;
    }

    public void pesquisar() {
        Date dataInicial;
        Date dataFinal;

        if (TipoPesquisa.REFERENCIA.equals(choicePesquisa.getValue())) {
            Calendar calendar = Calendar.getInstance();
            dataInicial = Date.valueOf(LocalDate.of(calendar.get(Calendar.YEAR), choiceReferencia.getValue().getId(), 1));
            dataFinal = Date.valueOf(LocalDate.of(calendar.get(Calendar.YEAR), choiceReferencia.getValue().getId(), 31));
        } else {
            dataInicial = Date.valueOf(dtInicioPeriodo.getValue());
            dataFinal = Date.valueOf(dtFimPeriodo.getValue());
        }

        gastos = sessionBeanGasto.recuperarGastosPorPeriodo(dataInicial, dataFinal, usuario.getId());
        rendas = sessionBeanRenda.recuperarRendasPorPeriodo(dataInicial, dataFinal, usuario.getId());

        if (!gastos.isEmpty() || !rendas.isEmpty()) {
            setDataInicioAux(dtInicioPeriodo.getValue());
            setDataFinalAux(dtFimPeriodo.getValue());

            double totalGastos = calcularTotalGastos();
            double totalRendas = calcularTotalRendas();
            double remanescente = totalRendas - totalGastos;

            txtTotalGastos.setText("R$ " + Formatacao.converterDoubleParaReal(totalGastos));
            txtTotalRendas.setText("R$ " + Formatacao.converterDoubleParaReal(totalRendas));
            txtRemanescente.setText("R$ " + Formatacao.converterDoubleParaReal(remanescente));

            organizarMovimentos();
            montarListar();
        } else {
            if (TipoPesquisa.REFERENCIA.equals(choicePesquisa.getValue()))
                GFAlert.makeAlertWarning("Não há gastos e rendas nesta referência.");
            else
                GFAlert.makeAlertWarning("Não há gastos e rendas neste período.");
            dtInicioPeriodo.setValue(getDataInicioAux());
            dtFimPeriodo.setValue(getDataFinalAux());
        }
    }

    public void montarListar() {
        listTimeline = new ListView<>();

        ArrayList<HBox> items = new ArrayList<>();

        for (MovimentoFinanceiroVO movimento : movimentos) {
            HBox hbox = new HBox(8);

            VBox vbLeft = new VBox(8);
            vbLeft.setAlignment(Pos.CENTER_LEFT);
            vbLeft.getChildren().addAll(new Label(movimento.getTipo().getDescricao()), new Label(movimento.getTitulo()));

            VBox vbRight = new VBox(8);
            vbRight.setAlignment(Pos.CENTER_RIGHT);
            SimpleDateFormat dataFormat = new SimpleDateFormat("dd-MM-yyyy");
            vbRight.getChildren().addAll(new Label(movimento.getTipo().getSimbolo() + " " + movimento.getValor()), new Label(dataFormat.format(movimento.getData())));

            hbox.getChildren().addAll(vbLeft, vbRight);

            items.add(hbox);
        }

        listTimeline.setItems(FXCollections.observableArrayList(items));
    }

    public void organizarMovimentos() {
        movimentos = new ArrayList<>();

        if (!rendas.isEmpty()) {
            for (Renda renda : rendas) {
                movimentos.add(new MovimentoFinanceiroVO(TipoMovimento.RENDA, renda.getTitulo(), renda.getValor(), renda.getData(), renda.getDescricao(), renda.getDtaAdd()));
            }
        }

        if (!gastos.isEmpty()) {
            for (Gasto gasto : gastos) {
                movimentos.add(new MovimentoFinanceiroVO(TipoMovimento.GASTO, gasto.getTitulo(), gasto.getValor(), gasto.getData(), gasto.getDescricao(), gasto.getDtaAdd()));
            }
        }

        Collections.sort(movimentos, new Comparator<MovimentoFinanceiroVO>() {
            @Override
            public int compare(MovimentoFinanceiroVO mv1, MovimentoFinanceiroVO mv2) {
                if (mv1.getData() == null || mv2.getData() == null)
                    return 0;
                return mv1.getData().compareTo(mv2.getData());
            }
        });
    }

    public double calcularTotalGastos() {
        double total = 0;

        if (!gastos.isEmpty()) {
            for (Gasto gasto : gastos) {
                total += gasto.getValor();
            }
        }

        return total;
    }

    public double calcularTotalRendas() {
        double total = 0;

        if (!rendas.isEmpty()) {
            for (Renda renda : rendas) {
                total += renda.getValor();
            }
        }

        return total;
    }

    public boolean validacoesCampos() {
        StringBuilder invalidos = new StringBuilder("");

        if (choicePesquisa.getValue() == null) {
            invalidos.append("\nSelecione o tipo da pesquisa.");
        }
        if (TipoPesquisa.REFERENCIA.equals(choicePesquisa.getValue()) && choiceReferencia.getValue() == null) {
            invalidos.append("\nSelecione a referência.");
        }
        if (TipoPesquisa.PERIODO.equals(choicePesquisa.getValue())) {
            if (dtInicioPeriodo.getValue() != null && dtFimPeriodo.getValue() != null) {
                if (dtInicioPeriodo.getValue().isAfter(dtFimPeriodo.getValue())) {
                    invalidos.append("\nO período inserido é inválido!\nA data inicial deve ser antes da data final.");
                }
            } else {
                if (dtInicioPeriodo.getValue() == null) {
                    invalidos.append("\nPreencha a data de início do período.");
                }
                if (dtFimPeriodo.getValue() == null) {
                    invalidos.append("\nPreencha a data de fim do período.");
                }
            }
        }

        if (!Utils.isNullOrEmpty(invalidos.toString())) {
            GFAlert.makeAlertInfo(invalidos.toString());
            return false;
        }
        return true;
    }

    public void definirDataMaxima() {
        Calendar hoje = Calendar.getInstance();
        LocalDate maxDate = LocalDate.of(hoje.get(Calendar.YEAR), hoje.get(Calendar.MONTH) + 1, hoje.get(Calendar.DAY_OF_MONTH));

        dtInicioPeriodo.setDayCellFactory(dia -> {
            return new DateCell() {
                @Override
                public void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);
                    setDisable(item.isAfter(maxDate));
                }
            };
        });

        dtFimPeriodo.setDayCellFactory(dia -> {
            return new DateCell() {
                @Override
                public void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);
                    setDisable(item.isAfter(maxDate));
                }
            };
        });

        choiceReferencia.setValue(Meses.get(hoje.get(Calendar.MONTH) + 1));

        LocalDate inicioDoMes = LocalDate.of(hoje.get(Calendar.YEAR), hoje.get(Calendar.MONTH) + 1, 1);
        dtInicioPeriodo.setValue(inicioDoMes);
        dtFimPeriodo.setValue(maxDate);
        setDataInicioAux(inicioDoMes);
        setDataFinalAux(maxDate);
    }

    public LocalDate getDataInicioAux() {
        return dataInicioAux;
    }

    public void setDataInicioAux(LocalDate dataInicioAux) {
        this.dataInicioAux = dataInicioAux;
    }

    public LocalDate getDataFinalAux() {
        return dataFinalAux;
    }

    public void setDataFinalAux(LocalDate dataFinalAux) {
        this.dataFinalAux = dataFinalAux;
    }
}
