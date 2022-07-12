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

public class RelatoriosController implements Initializable {

    private SessionBeanPreferencias sessionBeanPreferencias = new SessionBeanPreferencias();
    private SessionBeanGasto sessionBeanGasto = new SessionBeanGasto();
    private SessionBeanRenda sessionBeanRenda = new SessionBeanRenda();
    private Usuario usuario;
    private List<Gasto> gastos = new ArrayList<>();
    private List<Renda> rendas = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //
    }

    public RelatoriosController(Usuario usuario) {
        this.usuario = usuario;
    }
}
