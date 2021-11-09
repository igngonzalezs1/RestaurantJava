/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

/**
 *
 * @author Ignacioogonz
 */
public class TableStatus {

    private int ID;
    private String NAME;
    private String CODE;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getCODE() {
        return CODE;
    }

    public void setCODE(String CODE) {
        this.CODE = CODE;
    }

    public String toString() {
        return this.NAME;
    }

    public Vector<TableStatus> mostrarGrupo() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion conn = new Conexion();
        Connection con = conn.getConexion();

        String sql = "SELECT * FROM TABLE_STATUS";
        Vector<TableStatus> datos = new Vector<TableStatus>();
        TableStatus dat = null;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            dat = new TableStatus();
            //dat.setID("");
            //dat.setNAME("Seleccione Grupo");
            //datos.add(dat);
            while (rs.next()) {
                dat = new TableStatus();
                dat.setID(rs.getInt("ID"));
                dat.setNAME(rs.getString("NAME"));
                datos.add(dat);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return datos;
    }
}
