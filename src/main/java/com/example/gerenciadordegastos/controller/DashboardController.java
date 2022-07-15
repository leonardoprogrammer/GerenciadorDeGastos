package com.example.gerenciadordegastos.controller;

import com.example.gerenciadordegastos.business.SessionBeanGasto;
import com.example.gerenciadordegastos.business.SessionBeanPreferencias;
import com.example.gerenciadordegastos.business.SessionBeanRenda;
import com.example.gerenciadordegastos.enums.Meses;
import com.example.gerenciadordegastos.enums.ValoresGraficos;
import com.example.gerenciadordegastos.model.entity.Gasto;
import com.example.gerenciadordegastos.model.entity.Renda;
import com.example.gerenciadordegastos.model.entity.Usuario;
import com.example.gerenciadordegastos.util.GFAlert;
import com.example.gerenciadordegastos.util.Utils;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    private SessionBeanPreferencias sessionBeanPreferencias = new SessionBeanPreferencias();
    private SessionBeanGasto sessionBeanGasto = new SessionBeanGasto();
    private SessionBeanRenda sessionBeanRenda = new SessionBeanRenda();
    private Usuario usuario;
    private List<Gasto> gastos = new ArrayList<>();
    private List<Renda> rendas = new ArrayList<>();

    // IDEIAS:
    // Gráfico pizza de gastos
    // Gráfico pizza de ganhos
    // Gráfico torre de meses mais gastos
    // Gráfico torre de meses mais ganhos
    // Gráfico torre de meses mais poupados (quantia que sobra = ganhos - gastos)
    // exibir campo com total já gasto
    // exibir campo com total já poupado

    @FXML
    private ChoiceBox<ValoresGraficos> choiceValores = new ChoiceBox<>();

    @FXML
    private DatePicker dtDataInicial;

    @FXML
    private DatePicker dtDataFinal;

    @FXML
    private Button btnCalcular;

    @FXML
    private PieChart chartPie;

    @FXML
    private BarChart chartBar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceValores.setItems(FXCollections.observableArrayList(ValoresGraficos.values()));

        definirDataMaxima();

        habilitarGraficos(false);

        btnCalcular.setOnMouseClicked(event -> {
            if (validationsData())
                calcular();
        });
    }

    public DashboardController(Usuario usuario) {
        this.usuario = usuario;
    }

    public void calcular() {
        chartPie.setTitle("% de " + choiceValores.getValue().getDescricao());
        chartBar.setTitle(choiceValores.getValue().getDescricao() + " em R$");

        if (choiceValores.getValue().equals(ValoresGraficos.GASTOS) || choiceValores.getValue().equals(ValoresGraficos.GASTOS_E_RENDAS)) {
            gastos = sessionBeanGasto.recuperarGastosPorPeriodo(Date.valueOf(dtDataInicial.getValue()), Date.valueOf(dtDataFinal.getValue()), usuario.getId());
        }
        if (choiceValores.getValue().equals(ValoresGraficos.RENDAS) || choiceValores.getValue().equals(ValoresGraficos.GASTOS_E_RENDAS)) {
            rendas = sessionBeanRenda.recuperarRendasPorPeriodo(Date.valueOf(dtDataInicial.getValue()), Date.valueOf(dtDataFinal.getValue()), usuario.getId());
        }

        chartPie.setData(FXCollections.observableArrayList());
        chartBar.setData(FXCollections.observableArrayList());

        if (choiceValores.getValue().equals(ValoresGraficos.GASTOS)) {
            if (!gastos.isEmpty()) {
                List<PieChart.Data> gastosPorcentagens = calcularPorcentagensGastos();
                chartPie.setData(FXCollections.observableArrayList(gastosPorcentagens));

                XYChart.Series gastosEmReais = organizarGastosEmReais();
                gastosEmReais.setName(definirNameChartBar());
                chartBar.getData().addAll(gastosEmReais);
                habilitarGraficos(true);
            } else {
                GFAlert.makeAlertInfo("Não há gastos neste período.");
                habilitarGraficos(false);
            }
        } else if (choiceValores.getValue().equals(ValoresGraficos.RENDAS)) {
            if (!rendas.isEmpty()) {
                List<PieChart.Data> rendasPorcentagens = calcularPorcentagensRendas();
                chartPie.setData(FXCollections.observableArrayList(rendasPorcentagens));

                XYChart.Series rendasEmReais = organizarRendasEmReais();
                rendasEmReais.setName(definirNameChartBar());
                chartBar.getData().addAll(rendasEmReais);
                habilitarGraficos(true);
            } else {
                GFAlert.makeAlertInfo("Não há rendas neste período.");
                habilitarGraficos(false);
            }
        } else if (choiceValores.getValue().equals(ValoresGraficos.GASTOS_E_RENDAS)) {
            if (!gastos.isEmpty() || !rendas.isEmpty()) {
                List<PieChart.Data> porcentagens = calcularPorcentagensRendasEGastos();
                chartPie.setData(FXCollections.observableArrayList(porcentagens));

                XYChart.Series gastosERendasEmReais = organizarGastosERendasEmReais();
                gastosERendasEmReais.setName(definirNameChartBar());
                chartBar.getData().addAll(gastosERendasEmReais);
                habilitarGraficos(true);
            } else {
                GFAlert.makeAlertInfo("Não há gastos e rendas neste período.");
                habilitarGraficos(false);
            }
        }
    }

    public List<PieChart.Data> calcularPorcentagensGastos() {
        double total = 0;
        List<PieChart.Data> gastosPorcentagens = new ArrayList<>();

        for (Gasto gasto : gastos) {
            total += gasto.getValor();
        }

        for (Gasto gasto : gastos) {
            double porcentagem = gasto.getValor() / total;
            porcentagem *= 100;
            gastosPorcentagens.add(new PieChart.Data(gasto.getTitulo(), porcentagem));
        }

        return gastosPorcentagens;
    }

    public List<PieChart.Data> calcularPorcentagensRendas() {
        double total = 0;
        List<PieChart.Data> rendasPorcentagens = new ArrayList<>();

        for (Renda renda : rendas) {
            total += renda.getValor();
        }

        for (Renda renda : rendas) {
            double porcentagem = renda.getValor() / total;
            porcentagem *= 100;
            rendasPorcentagens.add(new PieChart.Data(renda.getTitulo(), porcentagem));
        }

        return rendasPorcentagens;
    }

    public List<PieChart.Data> calcularPorcentagensRendasEGastos() {
        double totalGastos = 0;
        double totalRendas = 0;
        double total = 0;
        List<PieChart.Data> porcentagens = new ArrayList<>();

        for (Gasto gasto : gastos) {
            totalGastos += gasto.getValor();
        }

        for (Renda renda : rendas) {
            totalRendas += renda.getValor();
        }

        total = totalGastos + totalRendas;

        double porcentagemGastos = totalGastos / total;
        porcentagemGastos *= 100;
        porcentagens.add(new PieChart.Data("Gastos", porcentagemGastos));

        double porcentagemRendas = totalRendas / total;
        porcentagemRendas *= 100;
        porcentagens.add(new PieChart.Data("Rendas", porcentagemRendas));

        return porcentagens;
    }

    public XYChart.Series organizarGastosEmReais() {
        XYChart.Series gastosSeries = new XYChart.Series();

        for (Gasto gasto : gastos) {
            gastosSeries.getData().add(new XYChart.Data(gasto.getTitulo(), gasto.getValor()));
        }

        return gastosSeries;
    }

    public XYChart.Series organizarRendasEmReais() {
        XYChart.Series rendasSeries = new XYChart.Series();

        for (Renda renda : rendas) {
            rendasSeries.getData().add(new XYChart.Data(renda.getTitulo(), renda.getValor()));
        }

        return rendasSeries;
    }

    public XYChart.Series organizarGastosERendasEmReais() {
        XYChart.Series gastosERendasSeries = new XYChart.Series();
        double totalGastos = 0;
        double totalRendas = 0;

        for (Gasto gasto : gastos) {
            totalGastos += gasto.getValor();
        }

        for (Renda renda : rendas) {
            totalRendas += renda.getValor();
        }

        gastosERendasSeries.getData().add(new XYChart.Data("Gastos", totalGastos));
        gastosERendasSeries.getData().add(new XYChart.Data("Rendas", totalRendas));

        return gastosERendasSeries;
    }

    public String definirNameChartBar() {
        Calendar inicioPeriodo = Utils.converterLocalDateParaCalendar(dtDataInicial.getValue());
        Calendar fimPeriodo = Utils.converterLocalDateParaCalendar(dtDataFinal.getValue());
        String name;

        if ((inicioPeriodo.get(Calendar.MONTH) == fimPeriodo.get(Calendar.MONTH)) && (inicioPeriodo.get(Calendar.YEAR) == fimPeriodo.get(Calendar.YEAR))) {
            name = Meses.get(inicioPeriodo.get(Calendar.MONTH) + 1).getNome() + "/" + inicioPeriodo.get(Calendar.YEAR);
        } else {
            name = Meses.get(inicioPeriodo.get(Calendar.MONTH) + 1).getAbreviacao() + "/" + inicioPeriodo.get(Calendar.YEAR) + " - " + Meses.get(fimPeriodo.get(Calendar.MONTH) + 1).getAbreviacao() + "/" + fimPeriodo.get(Calendar.YEAR);
        }

        return name;
    }

    public boolean validationsData() {
        StringBuilder invalid = new StringBuilder("");
        if (choiceValores.getValue() == null) {
            invalid.append("\nSelecione um valor para os gráficos.");
        }
        if (dtDataInicial.getValue() == null) {
            invalid.append("\nInsira a data inicial.");
        }
        if (dtDataFinal.getValue() == null) {
            invalid.append("\nInsira a data final.");
        }
        if (dtDataInicial.getValue() != null && dtDataFinal.getValue() != null) {
            if (dtDataInicial.getValue().isAfter(dtDataFinal.getValue())) {
                invalid.append("\nO período inserido é inválido!\nA data inicial deve ser antes da data final.");
            }
        }

        if (!Utils.isNullOrEmpty(invalid.toString())) {
            GFAlert.makeAlertWarning(invalid.toString());
            return false;
        }
        return true;
    }

    public void definirDataMaxima() {
        Calendar today = Calendar.getInstance();
        LocalDate maxDate = LocalDate.of(today.get(Calendar.YEAR), today.get(Calendar.MONTH) + 1, today.get(Calendar.DAY_OF_MONTH));

        dtDataInicial.setDayCellFactory(day -> {
            return new DateCell() {
                @Override
                public void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);
                    setDisable(item.isAfter(maxDate));
                }
            };
        });

        dtDataFinal.setDayCellFactory(day -> {
            return new DateCell() {
                @Override
                public void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);
                    setDisable(item.isAfter(maxDate));
                }
            };
        });

        LocalDate firstDayMonth = LocalDate.of(today.get(Calendar.YEAR), today.get(Calendar.MONTH) + 1, 1);
        dtDataInicial.setValue(firstDayMonth);
        dtDataFinal.setValue(maxDate);
    }

    public void habilitarGraficos(boolean logica) {
        chartPie.setVisible(logica);
        chartBar.setVisible(logica);
    }
}
