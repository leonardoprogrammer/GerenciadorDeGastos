package com.example.gerenciadordegastos.controller;

import com.example.gerenciadordegastos.model.entity.Usuario;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class TimelineController implements Initializable {

    private Usuario usuario;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //
    }

    public TimelineController(Usuario usuario) {
        this.usuario = usuario;
    }
}
