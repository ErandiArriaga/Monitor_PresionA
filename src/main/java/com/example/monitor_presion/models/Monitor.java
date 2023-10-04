package com.example.monitor_presion.models;

import java.sql.Date;

public class Monitor {

    private int id;
    private String p_sistolica;
    private String p_distolica;
    private String c_pulsos;
    private Date dueDate;


    public Monitor() {
    }

    public Monitor(int id, String p_sistolica, String p_distolica, String c_pulsos, Date dueDate) {
        this.id = id;
        this.p_distolica = p_distolica;
        this.p_distolica = p_distolica;
        this.c_pulsos = c_pulsos;
        this.dueDate = dueDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getP_sistolica() {
        return p_sistolica;
    }

    public void setP_sistolica(String p_sistolica) {
        this.p_sistolica = p_sistolica;
    }

    public String getP_distolica() {
        return p_distolica;
    }

    public void setP_distolica(String p_distolica) {
        this.p_distolica = p_distolica;
    }

    public String getC_pulsos() {
        return c_pulsos;
    }

    public void setC_pulsos(String c_pulsos) {
        this.c_pulsos = c_pulsos;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}


