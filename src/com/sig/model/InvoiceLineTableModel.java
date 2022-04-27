/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sig.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author HP
 */
public class InvoiceLineTableModel extends AbstractTableModel {
    private ArrayList<InvoiceLine> invoiceLines;
    private String[] tableLabels = {"Item Name", "Item Price", "Count", "Item Total"};

    public InvoiceLineTableModel(ArrayList<InvoiceLine> invoiceLines) {
        this.invoiceLines = invoiceLines;
    }


    @Override
    public int getRowCount() {
        return invoiceLines == null ? 0 : invoiceLines.size();
    }
    @Override
    public int getColumnCount() {
        return tableLabels.length;
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (invoiceLines == null) {
            return null;
        } else {
            InvoiceLine line = invoiceLines.get(rowIndex);
            
            switch (columnIndex) {
                case 0:
                    return   line.getItem();
                case 1:
                    return  line.getPrice();
                case 2:
                    return line.getCount();
                case 3:
                    return line.getLineTotal();
            };
        } return null;
    }
    @Override
    public String getColumnName(int column) {
        return tableLabels[column];
    }

}
