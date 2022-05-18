module com.example.gerenciadordegastos {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;
    requires java.desktop;

    opens com.example.gerenciadordegastos to javafx.fxml;
    exports com.example.gerenciadordegastos;
}