package com.example.gerenciadordegastos.controller;

import com.example.gerenciadordegastos.model.entity.Usuario;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class PreferenciasController implements Initializable {

    private Usuario usuario;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //
    }

    public PreferenciasController(Usuario usuario) {
        this.usuario = usuario;
    }
}
