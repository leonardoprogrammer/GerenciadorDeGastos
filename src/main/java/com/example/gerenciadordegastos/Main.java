package com.example.gerenciadordegastos;

import com.example.gerenciadordegastos.controller.EntradaController;
import com.example.gerenciadordegastos.util.Constantes;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ui/login.fxml"));
        fxmlLoader.setController(new EntradaController());
        Scene scene = new Scene(fxmlLoader.load(), Constantes.HEIGHT_WINDOW_LOGIN, Constantes.WIDTH_WINDOW_LOGIN);
        stage.setTitle("Gerenciador Financeiro");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}