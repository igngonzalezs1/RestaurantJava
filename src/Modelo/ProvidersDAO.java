/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ignacioogonz
 */
public class ProvidersDAO extends Conexion {

    public boolean registrar(Providers prov) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "INSERT INTO PROVIDERS (ID, NAME) VALUES (?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, prov.getID());
            ps.setString(2, prov.getNAME());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.err.println(e);
                return false;
            }
        }
    }

    public boolean modificar(Providers prov) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "UPDATE PROVIDERS SET ID=?, NAME=? where ID=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, prov.getID());
            ps.setString(2, prov.getNAME());
            ps.setInt(3, prov.getID());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.err.println(e);
                return false;
            }
        }
    }

    public boolean eliminar(Providers prov) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "DELETE FROM PROVIDERS WHERE ID=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, prov.getID());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.err.println(e);
                return false;
            }
        }
    }

    public boolean buscar(Providers prov) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT * FROM PROVIDERS WHERE ID=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, prov.getID());
            rs = ps.executeQuery();

            if (rs.next()) {
                prov.setID(Integer.parseInt(rs.getString("ID")));
                prov.setNAME(rs.getString("NAME"));
                return true;
            }
            return false;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.err.println(e);
                return false;
            }
        }
    }

    public DefaultTableModel dataTables() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Conexion conn = new Conexion();
        Connection con = conn.getConexion();
        DefaultTableModel modelo = new DefaultTableModel();
        String sql = "SELECT ID, NAME FROM PROVIDERS";
        modelo.addColumn("Id");
        modelo.addColumn("Nombre");
        TableStatus dat = null;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            while (rs.next()) {
                Object[] filas = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                modelo.addRow(filas);
            }
            //dat.setID("");
            //dat.setNAME("Seleccione Grupo");
            //datos.add(dat);
        } catch (Exception e) {
            System.err.println(e);
        }
        return modelo;
    }
}
