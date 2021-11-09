/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.ResultSetMetaData;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import jdk.nashorn.internal.parser.JSONParser;

/**
 *
 * @author Ignacioogonz
 */
public class UsersDAO extends Conexion {

    public static boolean send(String user, String pass) throws IOException {
        URL url = new URL(
                "http://elrestaurant.com/admin/users/login");
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("USERNAME", user);
        params.put("PASSWORD", pass);
        params.put("is_api", true);

        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            if (postData.length() != 0) {
                postData.append('&');
            }
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()),
                    "UTF-8"));
        }
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length",
                String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes);

        Reader in = new BufferedReader(new InputStreamReader(
                conn.getInputStream(), "UTF-8"));
        String aux = "";
        for (int c = in.read(); c != -1; c = in.read()) {
            aux += (char) c;
        }
        return aux.contains("true");

        //System.out.println(aux);
    }

    public boolean login(Users usr) throws IOException {
        return send(usr.getUSERNAME(), usr.getPASSWORD());
    }

    public boolean registrar(Users user) throws IOException {
        URL url = new URL(
                "http://elrestaurant.com/admin/users/add/");
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("USERNAME", user.getUSERNAME());
        params.put("PASSWORD", user.getPASSWORD());
        params.put("NAME", user.getNAME());
        params.put("RUT", user.getRUT());
        params.put("EMAIL", user.getEMAIL());
        params.put("GROUP_ID", user.getGROUP_ID());

        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            if (postData.length() != 0) {
                postData.append('&');
            }
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()),
                    "UTF-8"));
        }
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length",
                String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes);

        Reader in = new BufferedReader(new InputStreamReader(
                conn.getInputStream(), "UTF-8"));
        String aux = "";
        for (int c = in.read(); c != -1; c = in.read()) {
            aux += (char) c;
        }
        System.out.println(aux);
        return aux.contains("true");
    }

    public boolean modificar(Users user) throws IOException {
        //PreparedStatement ps = null;
        //Connection con = getConexion();

        //String sql = "UPDATE USERS SET ID=?, USERNAME=?, PASSWORD=?, NAME=?, RUT=?, EMAIL=?, GROUP_ID=? where ID=?";
        URL url = new URL(
                "http://elrestaurant.com/admin/users/edit/" + user.getID());
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("USERNAME", user.getUSERNAME());
        params.put("PASSWORD", user.getPASSWORD());
        params.put("NAME", user.getNAME());
        params.put("RUT", user.getRUT());
        params.put("EMAIL", user.getEMAIL());
        params.put("GROUP_ID", user.getGROUP_ID());

        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String, Object> param : params.entrySet()) {
            if (postData.length() != 0) {
                postData.append('&');
            }
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()),
                    "UTF-8"));
        }
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length",
                String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes);

        Reader in = new BufferedReader(new InputStreamReader(
                conn.getInputStream(), "UTF-8"));
        String aux = "";
        for (int c = in.read(); c != -1; c = in.read()) {
            aux += (char) c;
        }
        System.out.println(aux);
        return aux.contains("true");
    }

    public boolean eliminar(Users user) {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "DELETE FROM USERS WHERE ID=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, user.getID());
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

    public boolean buscar(Users user) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT * FROM USERS WHERE ID=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, user.getID());
            rs = ps.executeQuery();

            if (rs.next()) {
                user.setID(Integer.parseInt(rs.getString("ID")));
                user.setUSERNAME(rs.getString("USERNAME"));
                user.setPASSWORD(rs.getString("PASSWORD"));
                user.setNAME(rs.getString("NAME"));
                user.setRUT(rs.getString("RUT"));
                user.setEMAIL(rs.getString("EMAIL"));
                user.setGROUP_ID(Integer.parseInt(rs.getString("GROUP_ID")));
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
        String sql = "SELECT USERS.ID, USERS.USERNAME, USERS.NAME, USERS.RUT, USERS.EMAIL, GROUPS.NAME AS GROUP_NAME FROM USERS INNER JOIN GROUPS ON USERS.GROUP_ID = GROUPS.ID ORDER BY USERS.ID";
        modelo.addColumn("Id");
        modelo.addColumn("Nombre de Usuario");
        modelo.addColumn("Nombre");
        modelo.addColumn("Rut");
        modelo.addColumn("Email");
        modelo.addColumn("Grupo");

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
