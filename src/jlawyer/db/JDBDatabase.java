/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlawyer.db;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author anowak
 */
public class JDBDatabase {

    private static final String DBNAME = "lawyerDB.jdb";
    private static final String PASSWORD = "secret123";

    // Db state
    private String dbFileName;
    private Connection conn=null;
    private PreparedStatement lookupAllIds;
    private PreparedStatement insertFile;
    private PreparedStatement lookupFile;
    private PreparedStatement deleteFile;
    private PreparedStatement searchFile;
    
    private JDBDatabase() {}  // Singleton
        private static JDBDatabase instance;
        
    public static JDBDatabase getInstance() {
        if (instance==null) {
            instance = new JDBDatabase(DBNAME,PASSWORD);
        }
        return instance;
    }
    
    public Connection getConnection() {
        return conn;
    }
    
    private JDBDatabase(String dbFileName, String pw ) {
        System.out.println("loadDatabase - dbFileName="+dbFileName+", pw="+pw);
        File db = new File(dbFileName);

        String dbURL;
        try {
            if (db.isDirectory()) {
                System.out.println("Connecting to existing database " + dbFileName);
                dbURL = "jdbc:derby:" + dbFileName + ";bootPassword=" + pw;
                System.out.println("DerbyDB Connection: "+dbURL);
                conn = DriverManager.getConnection(dbURL);
            } else {
                System.out.println("Creating new database " + dbFileName + " ...");
                dbURL = "jdbc:derby:" + dbFileName + ";create=true;dataEncryption=true;bootPassword=" + pw;
                System.out.println("DerbyDB Connection: "+dbURL);
                conn = DriverManager.getConnection(dbURL);
                try (Statement s = conn.createStatement()) {
                    s.executeUpdate("create table LegalFile("
                            + "Id INTEGER PRIMARY KEY, "
                            + "Mandate VARCHAR(1024), "
                            + "Client VARCHAR(1024), "
                            + "File VARCHAR(1024), "
                            + "FileNumber VARCHAR(512) not null, "
                            + "RecommendedBy VARCHAR(1024), "
                            + "CaseDesc VARCHAR(2048)"
                            + ")");
                    s.close();
                }
            }

            lookupAllIds = conn.prepareStatement("select id from LegalFile order by Id");
            lookupFile = conn.prepareStatement("select * from LegalFile where Id=?");
            deleteFile = conn.prepareStatement("delete from LegalFile where Id=?");
            insertFile = conn.prepareStatement("insert into LegalFile values (?,?,?,?,?,?,?)");
            searchFile = conn.prepareStatement("select * from LegalFile");
        } catch (SQLException e) {
            e.printStackTrace();
            if (causedBy(e, "XBM06")) {
                JOptionPane.showMessageDialog(null,
                        "Wrong master password",
                        "MyError",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                String err = collectErrorString(e);
                JOptionPane.showMessageDialog(null,
                        "Could not create or boot lawyer database: \n"
                        + err,
                        "MyError",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
   
    private String collectErrorString(SQLException e) {
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        for (SQLException err = e; err != null; err = err.getNextException()) {
            sb.append(err.getSQLState());
            sb.append(' ');
            sb.append(err.toString());
            idx = sb.length();
            sb.append(" caused by \n");
        }
        return sb.substring(0, idx);
    }
    
    private boolean causedBy(SQLException e, String s) {
        boolean result = false;
        for (SQLException ee=e; ee != null; ee = ee.getNextException()) {
            if (s.equals(ee.getSQLState())) {
                result = true;
                break;
            }
        }
        return result;
    }    
}
