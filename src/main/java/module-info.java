module com.example.gerenciadordegastos {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;
    requires java.desktop;

    opens com.example.gerenciadordegastos.model.entity to javafx.base;
    opens com.example.gerenciadordegastos to javafx.fxml;
    opens com.example.gerenciadordegastos.controller to javafx.fxml;
    exports com.example.gerenciadordegastos;
    opens com.example.gerenciadordegastos.vo to javafx.fxml;
    exports com.example.gerenciadordegastos.util;
    opens com.example.gerenciadordegastos.util to javafx.fxml;
    exports com.example.gerenciadordegastos.util.exception;
    opens com.example.gerenciadordegastos.util.exception to javafx.fxml;
    exports com.example.gerenciadordegastos.controller;
}