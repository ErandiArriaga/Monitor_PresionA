package com.example.monitor_presion.models;

import java.sql.Date;

public class Monitor {

    private int id;
    private String sistolica;
    private String distolica;
    private String pulsos;
    private Date dueDate;


    public Monitor() {
    }

    public Monitor(int id, String sistolica, String distolica, String pulsos, Date dueDate) {
        this.id = id;
        this.sistolica = sistolica;
        this.distolica = distolica;
        this.pulsos = pulsos;
        this.dueDate = dueDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSistolica() {
        return sistolica;
    }

    public void setSistolica(String sistolica) {
        this.sistolica = sistolica;
    }

    public String getDistolica() {
        return distolica;
    }

    public void setDistolica(String distolica) {
        this.distolica = distolica;
    }

    public String getPulsos() {
        return pulsos;
    }

    public void setPulsos(String pulsos) {
        this.pulsos = pulsos;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}


