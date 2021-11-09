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
public class TablesDAO extends Conexion {

    public boolean registrar(Tables table) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "INSERT INTO TABLES (ID, PERSON_QUANTITY, NAME, DESCRIPTION, TABLE_STATUS_ID) VALUES (?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, table.getID());
            ps.setInt(2, table.getPERSON_QUANTITY());
            ps.setString(3, table.getNAME());
            ps.setString(4, table.getDESCRIPTION());
            ps.setInt(5, table.getTABLE_STATUS_ID());
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

    public boolean modificar(Tables table) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "UPDATE TABLES SET ID=?, PERSON_QUANTITY=?, NAME=?, DESCRIPTION=?, TABLE_STATUS_ID=? where ID=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, table.getID());
            ps.setInt(2, table.getPERSON_QUANTITY());
            ps.setString(3, table.getNAME());
            ps.setString(4, table.getDESCRIPTION());
            ps.setInt(5, table.getTABLE_STATUS_ID());
            ps.setInt(6, table.getID());
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

    public boolean eliminar(Tables table) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "DELETE FROM TABLES WHERE ID=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, table.getID());
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

    public boolean buscar(Tables table) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT * FROM TABLES WHERE ID=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, table.getID());
            rs = ps.executeQuery();

            if (rs.next()) {
                table.setID(Integer.parseInt(rs.getString("ID")));
                table.setPERSON_QUANTITY(Integer.parseInt(rs.getString("PERSON_QUANTITY")));
                table.setNAME(rs.getString("NAME"));
                table.setDESCRIPTION(rs.getString("DESCRIPTION"));
                table.setTABLE_STATUS_ID(Integer.parseInt(rs.getString("TABLE_STATUS_ID")));
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
        String sql = "SELECT TABLES.ID, PERSON_QUANTITY, TABLES.NAME, TABLE_STATUS.NAME AS STATUS_NAME FROM TABLES INNER JOIN TABLE_STATUS ON TABLES.TABLE_STATUS_ID = TABLE_STATUS.ID";
        modelo.addColumn("Id");
        modelo.addColumn("Cantidad de Personas");
        modelo.addColumn("Nombre");
        modelo.addColumn("Estado");
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
