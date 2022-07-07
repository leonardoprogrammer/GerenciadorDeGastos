package com.example.gerenciadordegastos.util;

import com.example.gerenciadordegastos.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoadScreen {

    public static void openWindow(String resourceWindow, Object constructor) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(resourceWindow));
        fxmlLoader.setController(constructor);

        Scene scene = new Scene(fxmlLoader.load(), Constantes.HEIGHT_WINDOW_APPLICATION, Constantes.WIDTH_WINDOW_APPLICATION);
        Stage stage = new Stage();
        stage.setTitle(Constantes.NAME_APPLICATION);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void openWindow(String resourceWindow, Object constructor, double height, double width) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(resourceWindow));
        fxmlLoader.setController(constructor);

        Scene scene = new Scene(fxmlLoader.load(), height, width);
        Stage stage = new Stage();
        stage.setTitle(Constantes.NAME_APPLICATION);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}