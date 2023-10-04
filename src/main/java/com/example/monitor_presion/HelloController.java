package com.example.monitor_presion;

import com.example.monitor_presion.db.dao.MonitorDAO;
import com.example.monitor_presion.models.Monitor;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicLong;

public class HelloController {
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


    @FXML
    protected void onSaveButtonClick() {
        if (editMode)
        {
            monitorpList.get(monitorpList.indexOf(taskSelected)).setSistolica(txtsistolica.getText());
            monitorpList.get(monitorpList.indexOf(taskSelected)).setDistolica(txtdistolica.getText());
            monitorpList.get(monitorpList.indexOf(taskSelected)).setPulsos(txtpulsos.getText());
            editMode = false;
            btnSave.setText("Add");
        } else
        {
            Monitor newMonitor = new Monitor();
            newMonitor.setId(random.nextInt(1000));
            newMonitor.setSistolica(txtsistolica.getText());
            newMonitor.setDistolica(txtdistolica.getText());
            newMonitor.setPulsos(txtpulsos.getText());
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTableInfo();
    }

    private void initTableInfo()
    {
        monitorpList = FXCollections.observableArrayList( MonitorDAO.findAll());
        tblmonitorp.setItems(monitorpList);
        tblmonitorp.setColumnResizePolicy((param) -> true );
        Platform.runLater(() -> customResize(tblmonitorp));
        tblmonitorp.setOnMouseClicked((mouseEvent)->{
            if (mouseEvent.getClickCount() == 2)
            {
                //load task info in the form
                taskSelected = tblmonitorp.getSelectionModel().getSelectedItem();
                txtsistolica.setText(taskSelected.getSistolica());
                txtdistolica.setText(taskSelected.getDistolica());
                txtpulsos.setText(taskSelected.getPulsos());
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

}