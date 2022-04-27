/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sig.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;


public class InvoiceHeaderTableModel extends AbstractTableModel {
    private ArrayList<InvoiceHeader> invoiceHeaders;
    private String[] tableHeaders = {"No.", "Date", "Customer", "Total"};
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy") ;

    public InvoiceHeaderTableModel(ArrayList<InvoiceHeader> invoiceHeaders) {
        this.invoiceHeaders = invoiceHeaders;
    }
    @Override
    public int getRowCount() {
        return invoiceHeaders.size();
    }
    @Override
    public int getColumnCount() {
        return tableHeaders.length;
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceHeader invoiceHeader = invoiceHeaders.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return invoiceHeader.getNum();
            case 1:
                return dateFormat.format(invoiceHeader.getInvDate());
            case 2:
                return invoiceHeader.getCustomer();
            case 3:
                return invoiceHeader.getInvoiceTotal();
        }
        return null;
    }
    @Override
    public String getColumnName(int label) {
        return tableHeaders[label];
    }

}
