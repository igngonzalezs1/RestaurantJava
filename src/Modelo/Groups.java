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
public class Groups {

    private int ID;
    private String NAME;

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

    public String toString() {
        return this.NAME;
    }

    public Vector<Groups> mostrarGrupo() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion conn = new Conexion();
        Connection con = conn.getConexion();

        String sql = "SELECT * FROM GROUPS";
        Vector<Groups> datos = new Vector<Groups>();
        Groups dat = null;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            dat = new Groups();
            //dat.setID("");
            //dat.setNAME("Seleccione Grupo");
            //datos.add(dat);
            while (rs.next()) {
                dat = new Groups();
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
