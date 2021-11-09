/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Groups;
import Modelo.Users;
import Modelo.UsersDAO;
import Vista.frmUsers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ignacioogonz
 */
public class CtrlUsers implements ActionListener {

    private Users mod;
    private UsersDAO modC;
    private frmUsers frm;

    public CtrlUsers(Users mod, UsersDAO modC, frmUsers frm) {
        this.mod = mod;
        this.modC = modC;
        this.frm = frm;
        this.frm.btnGuardar.addActionListener(this);
        this.frm.btnEliminar.addActionListener(this);
        this.frm.btnBuscar.addActionListener(this);
        this.frm.btnModificar.addActionListener(this);
        this.frm.btnLimpiar.addActionListener(this);
    }

    public void iniciar() {
        frm.setTitle("Usuarios");
        frm.setLocationRelativeTo(null);
        Groups cc = new Groups();
        DefaultComboBoxModel modeloE = new DefaultComboBoxModel(cc.mostrarGrupo());
        frm.cbxGroup.setModel(modeloE);
        DefaultTableModel newModel = modC.dataTables();

        frm.jtTable.setModel(newModel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Groups grp = (Groups) frm.cbxGroup.getSelectedItem();

        if (e.getSource() == frm.btnGuardar) {
            //mod.setID(Integer.parseInt(frm.txtId.getText()));
            mod.setUSERNAME(frm.txtUsername.getText());
            mod.setNAME(frm.txtName.getText());
            mod.setPASSWORD(frm.txtPassword.getText());
            mod.setEMAIL(frm.txtEmail.getText());
            mod.setGROUP_ID(grp.getID());
            mod.setRUT(frm.txtRut.getText());
            try {
                if (modC.registrar(mod)) {
                    JOptionPane.showConfirmDialog(null, "Registro Guardado");
                } else {
                    JOptionPane.showConfirmDialog(null, "Error al Guardado");
                }
            } catch (IOException ex) {
                Logger.getLogger(CtrlUsers.class.getName()).log(Level.SEVERE, null, ex);
            }
            limpiar();
        }
        if (e.getSource() == frm.btnModificar) {
            mod.setID(Integer.parseInt(frm.txtId.getText()));
            mod.setUSERNAME(frm.txtUsername.getText());
            mod.setNAME(frm.txtName.getText());
            mod.setPASSWORD(frm.txtPassword.getText());
            mod.setEMAIL(frm.txtEmail.getText());
            mod.setGROUP_ID(grp.getID());
            mod.setRUT(frm.txtRut.getText());
            try {
                if (modC.modificar(mod)) {
                    JOptionPane.showConfirmDialog(null, "Registro Modificado");
                } else {
                    JOptionPane.showConfirmDialog(null, "Error al Modificar");
                }
            } catch (IOException ex) {
                Logger.getLogger(CtrlUsers.class.getName()).log(Level.SEVERE, null, ex);
            }
            limpiar();
        }
        if (e.getSource() == frm.btnEliminar) {
            mod.setID(Integer.parseInt(frm.txtId.getText()));
            if (modC.eliminar(mod)) {
                JOptionPane.showConfirmDialog(null, "Registro Eliminado");
            } else {
                JOptionPane.showConfirmDialog(null, "Error al Eliminar");
            }
            limpiar();
        }
        if (e.getSource() == frm.btnBuscar) {
            mod.setID(Integer.parseInt(frm.txtId.getText()));

            if (modC.buscar(mod)) {
                frm.txtId.setText(String.valueOf(mod.getID()));
                frm.txtUsername.setText(mod.getUSERNAME());
                frm.txtName.setText(mod.getNAME());
                frm.txtPassword.setText(mod.getPASSWORD());
                frm.txtEmail.setText(mod.getEMAIL());
                frm.txtRut.setText(mod.getRUT());
                frm.cbxGroup.setSelectedIndex(mod.getGROUP_ID());
            } else {
                limpiar();
                JOptionPane.showConfirmDialog(null, "No se encontro registro");
            }
        }
        if (e.getSource() == frm.btnLimpiar) {
            limpiar();
        }
        DefaultTableModel newModel = modC.dataTables();

        frm.jtTable.setModel(newModel);
    }

    public void limpiar() {
        frm.txtId.setText(null);
        frm.txtUsername.setText(null);
        frm.txtName.setText(null);
        frm.txtPassword.setText(null);
        frm.txtEmail.setText(null);
        frm.txtRut.setText(null);
        frm.cbxGroup.setSelectedIndex(0);
    }
}
