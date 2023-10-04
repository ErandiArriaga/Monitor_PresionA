package com.example.monitor_presion.db.dao;

import com.example.monitor_presion.db.MySQLConnection;
import com.example.monitor_presion.models.Monitor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class MonitorDAO extends MySQLConnection implements Dao<Monitor>{
    Connection conn = getConnection();
    @Override
    public Optional<Monitor> findById(int id) {
        Optional<Monitor> optionalMonitor = Optional.empty();
        String query = "select * from tasks where id = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if ( rs.next() )
            {
                Monitor monitor = new Monitor();
                monitor.setId(rs.getInt("id"));
                monitor.setSistolica(rs.getString("sistolica"));
                monitor.setDistolica(rs.getString("distolica"));
                monitor.setPulsos(rs.getString("pulsos"));
                monitor.setDueDate(rs.getDate("dueDate"));
                optionalMonitor = Optional.of(monitor);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return optionalMonitor;
    }


    public List<Monitor> findAll() {
        List<Monitor> monitorList = FXCollections.observableArrayList();
        String query = "select * from monitorp";
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next())
            {
                Monitor monitor = new Monitor();
                monitor.setId(rs.getInt("id"));
                monitor.setSistolica(rs.getString("sistolica"));
                monitor.setDistolica(rs.getString("distolica"));
                monitor.setPulsos(rs.getString("pulsos"));
                monitor.setDueDate(rs.getDate("dueDate"));
                monitorList.add(monitor);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return monitorList;
    }

    @Override
    public boolean save(Monitor monitor) {
        String query = "insert into monitorp " +
                " (sistolica, distolica, pulsos, dueDate)" +
                " values (?, ?, ?, ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, monitor.getSistolica());
            ps.setString(2, monitor.getDistolica());
            ps.setString(3, monitor.getPulsos());
            ps.setDate(4, monitor.getDueDate());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Monitor monitor) {
        String query = "update tasks set sistolica=?, distolica=?, pulsos=?, dueDate=?  where id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, monitor.getSistolica());
            ps.setString(2, monitor.getDistolica());
            ps.setString(3, monitor.getPulsos());
            ps.setDate(4, monitor.getDueDate());
            ps.setInt(5, monitor.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int monitor_id) {
        String query = "delete from monitorp where id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, monitor_id);
            ps.execute();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    }

