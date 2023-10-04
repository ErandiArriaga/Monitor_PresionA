package com.example.monitor_presion;

import com.example.monitor_presion.db.dao.MonitorDAO;
import com.example.monitor_presion.models.Monitor;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicLong;

public class HelloController implements Initializable {
    @FXML
    private Button btnSave, btnUpdate;
    @FXML
    TextField txtsistolica, txtdistolica, txtpulsos;
    @FXML
    TableView<Monitor> tblmonitorp;
    ObservableList<Monitor> monitorpList = FXCollections.observableArrayList();

    Boolean editMode = false;
    Monitor taskSelected = null;
    Random random = new Random();
    MonitorDAO monitorDAO = new MonitorDAO();


    @FXML
    protected void onSaveButtonClick() {
        if (editMode)
        {
            monitorpList.get(monitorpList.indexOf(taskSelected)).setP_sistolica(txtsistolica.getText());
            monitorpList.get(monitorpList.indexOf(taskSelected)).setP_distolica(txtdistolica.getText());
            monitorpList.get(monitorpList.indexOf(taskSelected)).setC_pulsos(txtpulsos.getText());
            editMode = false;
            btnSave.setText("Add");
        } else
        {
            Monitor newMonitor = new Monitor();
            newMonitor.setId(random.nextInt(1000));
            newMonitor.setP_sistolica(txtsistolica.getText());
            newMonitor.setP_distolica(txtdistolica.getText());
            newMonitor.setC_pulsos(txtpulsos.getText());
            monitorpList.add(newMonitor);
        }
        tblmonitorp.refresh();
        onUpdateButtonClick();
    }

    public void onUpdateButtonClick() {

        txtdistolica.setText("");
        txtdistolica.setText("");
        txtpulsos.setText("");
        txtdistolica.requestFocus();
        btnSave.setText("Save");
    }


    private void initTableInfo()
    {
        monitorpList = FXCollections.observableArrayList( monitorDAO.findAll());
        tblmonitorp.setItems(monitorpList);
        tblmonitorp.setColumnResizePolicy((param) -> true );
        Platform.runLater(() -> customResize(tblmonitorp));
        tblmonitorp.setOnMouseClicked((mouseEvent)->{
            if (mouseEvent.getClickCount() == 2)
            {
                //load task info in the form
                taskSelected = tblmonitorp.getSelectionModel().getSelectedItem();
                txtsistolica.setText(taskSelected.getP_sistolica());
                txtdistolica.setText(taskSelected.getP_distolica());
                txtpulsos.setText(taskSelected.getC_pulsos());
                editMode = true;
                btnSave.setText("Save");
            }
        });

    }

    public void customResize(TableView<?> view) {
        AtomicLong width = new AtomicLong();
        view.getColumns().forEach(col -> {
            width.addAndGet((long) col.getWidth());
        });
        double tableWidth = view.getWidth();

        if (tableWidth > width.get()) {
            view.getColumns().forEach(col -> {
                col.setPrefWidth(col.getWidth()+((tableWidth-width.get())/view.getColumns().size()));
            });
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}