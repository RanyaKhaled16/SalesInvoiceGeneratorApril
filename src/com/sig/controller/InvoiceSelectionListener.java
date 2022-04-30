/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sig.controller;

import com.sig.model.InvoiceHeader;
import com.sig.model.InvoiceLine;
import com.sig.model.InvoiceLineTableModel;
import com.sig.view.InvoiceFrame;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author HP
 */
public class InvoiceSelectionListener implements ListSelectionListener{
    private InvoiceFrame invoiceFrame;
private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy") ;
    public InvoiceSelectionListener(InvoiceFrame invoiceFrame) {
        this.invoiceFrame = invoiceFrame;
    }
 

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int selectedInvoiceIndex = invoiceFrame.getInvHTbl().getSelectedRow();
        if (selectedInvoiceIndex != -1) {
            InvoiceHeader selectedInv = invoiceFrame.getInvoicesArray().get(selectedInvoiceIndex);
            ArrayList<InvoiceLine> lines = selectedInv.getLines();
            InvoiceLineTableModel invoiceLineTableModel = new InvoiceLineTableModel(lines);
            invoiceFrame.setInvoiceLines(lines);
            invoiceFrame.getInvLTbl().setModel(invoiceLineTableModel);
            invoiceFrame.getCustNameLbl().setText(selectedInv.getCustomer());
            invoiceFrame.getInvNumLbl().setText("" + selectedInv.getNum());
            invoiceFrame.getInvTotalLbl().setText("" + selectedInv.getInvoiceTotal());
            invoiceFrame.getInvDateLbl().setText(dateFormat.format(selectedInv.getInvDate()));
        }
    }

   
}
