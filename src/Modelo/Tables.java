/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Ignacioogonz
 */
public class Tables {
    private int ID;
    private int PERSON_QUANTITY;
    private String NAME;
    private String DESCRIPTION;
    private int TABLE_STATUS_ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getPERSON_QUANTITY() {
        return PERSON_QUANTITY;
    }

    public void setPERSON_QUANTITY(int PERSON_QUANTITY) {
        this.PERSON_QUANTITY = PERSON_QUANTITY;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public int getTABLE_STATUS_ID() {
        return TABLE_STATUS_ID;
    }

    public void setTABLE_STATUS_ID(int TABLE_STATUS_ID) {
        this.TABLE_STATUS_ID = TABLE_STATUS_ID;
    }
    
    
}
