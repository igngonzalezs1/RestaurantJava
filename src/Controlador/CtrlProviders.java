/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Providers;
import Modelo.ProvidersDAO;
import Vista.frmProviders;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ignacioogonz
 */
public class CtrlProviders implements ActionListener{

    private Providers mod;
    private ProvidersDAO modC;
    private frmProviders frm;

    public CtrlProviders(Providers mod, ProvidersDAO modC, frmProviders frm) {
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
        frm.setTitle("Proveedor");
        frm.setLocationRelativeTo(null);
        DefaultTableModel newModel = modC.dataTables();

        frm.jtTable.setModel(newModel);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == frm.btnGuardar) {
            mod.setID(Integer.parseInt(frm.txtId.getText()));
            mod.setNAME(frm.txtName.getText());
            if (modC.registrar(mod)) {
                JOptionPane.showConfirmDialog(null, "Registro Guardado");
            } else {
                JOptionPane.showConfirmDialog(null, "Error al Guardado");
            }
            limpiar();
        }
        if (e.getSource() == frm.btnModificar) {
            mod.setID(Integer.parseInt(frm.txtId.getText()));
            mod.setNAME(frm.txtName.getText());
            if (modC.modificar(mod)) {
                JOptionPane.showConfirmDialog(null, "Registro Modificado");
            } else {
                JOptionPane.showConfirmDialog(null, "Error al Modificar");
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
                frm.txtName.setText(mod.getNAME());
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
        frm.txtName.setText(null);
    }
}
