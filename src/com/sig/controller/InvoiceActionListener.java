/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sig.controller;

import com.sig.model.InvoiceHeader;
import com.sig.model.InvoiceHeaderTableModel;
import com.sig.model.InvoiceLine;
import com.sig.view.InvoiceFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


/**
 *
 * @author HP
 */
public class InvoiceActionListener implements ActionListener{
    private InvoiceFrame frame ;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy") ;
    
    public InvoiceActionListener (InvoiceFrame frame){
        this.frame=frame; 
    }
    @Override
    public void actionPerformed(ActionEvent e) {
   switch(e.getActionCommand()){
       case "Save Files" : 
           saveFiles();
           break;
           
       case "Load Files" : 
   {
       try {
           loadFiles();
       } catch (IOException ex) {
           
       }
   }
           break;
           
       case "New Invoice" : 
           createNewInvoice();
           break;
           
       case "Delete Invoice": 
           deleteInvoice();
           break;
           
              case "New Line" : 
                  createNewLine();
           break;
           
       case "Delete Line":
           deleteLine();
           break;
       
   }
    }

    private void deleteLine() {
        
    }

    private void createNewLine() {

    }

    private void deleteInvoice() {

    }
// this is a comment
    private void loadFiles() throws FileNotFoundException, IOException {
JFileChooser fileChooser = new JFileChooser ();
try{
int result = fileChooser.showOpenDialog(frame);
    if (result == JFileChooser.APPROVE_OPTION){
      File headerFile =  fileChooser.getSelectedFile();
      Path headerPath = Paths.get(headerFile.getAbsolutePath());
      List<String> headerLines = Files.readAllLines(headerPath);
      ArrayList<InvoiceHeader> invoiceHeaders = new ArrayList<>();
      
      
      for ( String headerLine : headerLines){
          String[] arr = headerLine.split(",");
          String str1 = arr[0];
          String str2 = arr[1];
          String str3 = arr[2];
          int code = Integer.parseInt(str1);
          Date invoiceDate = dateFormat.parse(str2);
          InvoiceHeader header = new InvoiceHeader (code , str3 , invoiceDate);
          invoiceHeaders.add(header);
    }
     frame.setInvoicesArray(invoiceHeaders);
      result= fileChooser.showOpenDialog(frame);
      if( result == JFileChooser.APPROVE_OPTION)
      {
          File lineFile = fileChooser.getSelectedFile();
          Path linePath = Paths.get(lineFile.getAbsolutePath());
          List<String> lineLines = Files.readAllLines(linePath);
          for (String lineLine : lineLines)
          {
          String[] arr= lineLine.split(",");   
          String str1 = arr[0]; 
          String str2 = arr[1]; 
          String str3 = arr[2]; 
          String str4 = arr[3];
          int invCode = Integer.parseInt(str1);
          double price = Double.parseDouble(str3);
          int count = Integer.parseInt(str4);
          InvoiceHeader inv = frame.getInvObject(invCode);
          InvoiceLine line = new InvoiceLine(str2 , price , count , inv);
          inv.getLines().add(line);
          }
      }
      InvoiceHeaderTableModel headerTableModel = new InvoiceHeaderTableModel(invoiceHeaders);
      frame.setHeaderTableModel(headerTableModel);
      frame.getInvHTbl().setModel(headerTableModel);
      System.out.println("files read");
    }
      
}catch(IOException ex){
JOptionPane.showMessageDialog(frame , ex.getMessage(),"Error", JOptionPane.ERROR_MESSAGE );
} catch (ParseException ex){
        JOptionPane.showMessageDialog(frame , ex.getMessage(),"Error", JOptionPane.ERROR_MESSAGE );
        }
}

    private void saveFiles() {
       }

    private void createNewInvoice() {
    }

  
}


  
