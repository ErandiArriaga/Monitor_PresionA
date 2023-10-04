module com.example.monitor_presion {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.monitor_presion to javafx.fxml;
    exports com.example.monitor_presion;
    exports com.example.monitor_presion.db;
    opens com.example.monitor_presion.db to javafx.fxml;
}