/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlawyer.control;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import jlawyer.db.JDBDatabase;
import jlawyer.model.LegalFile;

/**
 *
 * @author anowak
 */
public class LegalFileController {

        /**
         * Parse and construct LegalFule data model list from the database
         * and return the list   
         * 
         * @return list of legal files used by JavaFX table properties
         */
       public static List<LegalFile> parseLegalFileTable() {
      
            ObservableList<LegalFile> legalFileTableData = FXCollections.observableArrayList();
            try {
                PreparedStatement lookupAllRecords = JDBDatabase.getInstance().getConnection().prepareStatement("select * from LegalFile order by Id");
                ResultSet rs = lookupAllRecords.executeQuery();
                
                int n=0;
                while (rs.next()) {
                    n++;
                    LegalFile file = new LegalFile(
                            rs.getInt("ID"),
                            rs.getString("MANDATE"),
                            rs.getString("CLIENT"),
                            rs.getString("FILE"),
                            rs.getString("FILENUMBER"),
                            rs.getString("RECOMMENDEDBY"),
                            rs.getString("CASEDESC"),
                            "123.123"
                            );
                    System.out.println("["+n+"] "+file);
                    legalFileTableData.add(file);
                }
                System.out.println("Found "+n+" LegalFiles.");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Error retrieving LegalFiles from db:\n" + e,
                    "MyError", JOptionPane.ERROR_MESSAGE); 
            }

       
            /****
        ObservableList<LegalFile> legalFileTableData = FXCollections.observableArrayList(
            new LegalFile(1, "Mandate 1", "Client 1", "File 1","#1","Recommended By","Case Desc 1"),
            new LegalFile(2, "Mandate 2", "Client 2", "File 2","#2","Recommended By","Case Desc 2"),
            new LegalFile(3, "Mandate 3", "Client 3", "File 3","#3","Recommended By","Case Desc 3"),
            new LegalFile(4, "Mandate 4", "Client 4", "File 4","#4","Recommended By","Case Desc 4")
           );
           */
        return legalFileTableData;
    }

       public static int getNextId() throws SQLException {
           PreparedStatement lastRec = JDBDatabase.getInstance().getConnection().prepareStatement("select ID from LegalFile order by ID desc limit 1;");
           ResultSet rs = lastRec.executeQuery();
           if (!rs.last()) {
               JOptionPane.showMessageDialog(null,"Error retrieving last row of LegalFiles table:\n",
                       "MyError", JOptionPane.ERROR_MESSAGE);
               throw new SQLException("Error retrieving last row of LegalFiles table");
           }
           int lastId = rs.getInt("ID");
           System.out.println("Last ID in LegalFile tab: "+lastId);
           return lastId+1;
       }       
}
