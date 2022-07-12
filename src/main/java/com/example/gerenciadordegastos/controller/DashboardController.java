package com.example.gerenciadordegastos.controller;

import com.example.gerenciadordegastos.business.SessionBeanGasto;
import com.example.gerenciadordegastos.business.SessionBeanPreferencias;
import com.example.gerenciadordegastos.business.SessionBeanRenda;
import com.example.gerenciadordegastos.model.entity.Gasto;
import com.example.gerenciadordegastos.model.entity.Renda;
import com.example.gerenciadordegastos.model.entity.Usuario;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //
    }

    public DashboardController(Usuario usuario) {
        this.usuario = usuario;
    }
}
