/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.TableStatus;
import Modelo.Tables;
import Modelo.TablesDAO;
import Vista.frmTables;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ignacioogonz
 */
public class CtrlTables implements ActionListener {

    private Tables mod;
    private TablesDAO modC;
    private frmTables frm;

    public CtrlTables(Tables mod, TablesDAO modC, frmTables frm) {
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
        frm.setTitle("Mesas");
        frm.setLocationRelativeTo(null);
        TableStatus cc = new TableStatus();
        DefaultComboBoxModel modeloE = new DefaultComboBoxModel(cc.mostrarGrupo());
        frm.cbxStatus.setModel(modeloE);
        DefaultTableModel newModel = modC.dataTables();
        
        frm.jtTable.setModel(newModel);
    }

    public void actionPerformed(ActionEvent e) {
        //int TableStatus = frm.cbxStatus.getSelectedIndex();
        TableStatus est = (TableStatus) frm.cbxStatus.getSelectedItem();
        if (e.getSource() == frm.btnGuardar) {
            mod.setID(Integer.parseInt(frm.txtId.getText()));
            mod.setNAME(frm.txtName.getText());
            mod.setDESCRIPTION(frm.txtDescription.getText());
            mod.setPERSON_QUANTITY(Integer.parseInt(frm.txtPersonQuantity.getText()));
            mod.setTABLE_STATUS_ID(est.getID());
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
            mod.setDESCRIPTION(frm.txtDescription.getText());
            mod.setPERSON_QUANTITY(Integer.parseInt(frm.txtPersonQuantity.getText()));
            mod.setTABLE_STATUS_ID(est.getID());
            System.out.println(mod.getTABLE_STATUS_ID());
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
                frm.txtDescription.setText(mod.getDESCRIPTION());
                frm.txtPersonQuantity.setText(String.valueOf(mod.getPERSON_QUANTITY()));
                frm.cbxStatus.setSelectedIndex(mod.getTABLE_STATUS_ID());
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
        frm.txtDescription.setText(null);
        frm.txtPersonQuantity.setText(null);
        frm.cbxStatus.setSelectedIndex(0);
    }
}
