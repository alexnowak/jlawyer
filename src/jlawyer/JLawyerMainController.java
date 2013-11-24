/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlawyer;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import jlawyer.control.LegalFileController;
import jlawyer.model.LegalFile;

/**
 *
 * @author anowak
 */
public class JLawyerMainController implements Initializable {
    @FXML //  fx:id="akte"
    private ComboBox<String> akte; // Value injected by FXMLLoader

    @FXML //  fx:id="mandant"
    private ComboBox<String> mandant; // Value injected by FXMLLoader

    @FXML //  fx:id="mandat"
    private ComboBox<String> mandat; // Value injected by FXMLLoader

    @FXML //  fx:id="Vermittlung"
    private ComboBox<String> vermittlung; // Value injected by FXMLLoader

    @FXML //  fx:id="addButton"
    private Button addButton; // Value injected by FXMLLoader

    @FXML //  fx:id="label"
    private Label label; // Value injected by FXMLLoader

    @FXML //  fx:id="legalFileTable"
    private TableView legalFileTable; // Value injected by FXMLLoader

    @FXML //  fx:id="colMandate"
    private TableColumn<LegalFile,String> colMandate; // Value injected by FXMLLoader
    @FXML //  fx:id="colClient"
    private TableColumn<LegalFile,String> colClient; // Value injected by FXMLLoader
    @FXML //  fx:id="colFile"
    private TableColumn<LegalFile,String> colFile; // Value injected by FXMLLoader
    @FXML //  fx:id="colRecommndedBy"
    private TableColumn<LegalFile,String> colRecommndedBy; // Value injected by FXMLLoader
    @FXML //  fx:id="colCaseDesc"
    private TableColumn<LegalFile,String> colCaseDesc; // Value injected by FXMLLoader
    @FXML //  fx:id="colFileNumber"
    private TableColumn<LegalFile,String> colFileNumber; // Value injected by FXMLLoader
   
    @FXML //  fx:id="aktenzeichen"
    private TextField aktenzeichen; // Value injected by FXMLLoader

    @FXML //  fx:id="value"
    private TextField value;    // Value injected by FXMLLoader

    // Handler for Button[fx:id="addButton"] onAction
    public void handleButtonAction(ActionEvent event) {
        System.out.println("Button clicked.");
        
        String valMandat = mandat.getValue();
        System.out.println("Mandat: " + ((valMandat == null || valMandat.isEmpty()) ? "NOT SET" : valMandat));
        String valMandant = mandant.getValue();
        System.out.println("Mandant: " + ((valMandant == null || valMandant.isEmpty()) ? "NOT SET" : valMandant));
        String valVermittlung = vermittlung.getValue();
        System.out.println("Vermittlung: " + ((valVermittlung == null || valVermittlung.isEmpty()) ? "NOT SET" : valVermittlung));
        String valAkte = akte.getValue();
        System.out.println("Akte: " + ((valAkte == null || valAkte.isEmpty()) ? "NOT SET" : valAkte));
        String valValue = value.getText().trim();
        System.out.println("Gegenstandswert: " + ((valValue == null || valValue.isEmpty()) ? "NOT SET" : valValue));
        String valAktenZeichen = aktenzeichen.getText().trim();
        System.out.println("AktenZeichen: " + ((valAktenZeichen == null || valAktenZeichen.isEmpty()) ? "NOT SET" : valAktenZeichen));

        if ( (valMandat == null || valMandat.isEmpty()) ||
             (valMandant == null || valMandant.isEmpty()) ||
             (valVermittlung == null || valVermittlung.isEmpty()) ||
             (valAkte == null || valAkte.isEmpty()) ||
             (valValue == null || valValue.isEmpty()) ||
             (valAktenZeichen == null || valAktenZeichen.isEmpty())
             ) {
            System.out.print("Duh, all fields must have values...!!!");
            return;
        } 
    
        System.out.println("Adding new record...");
        
        try {
            LegalFile legalFile = new LegalFile(LegalFileController.getNextId(),valMandat,valMandant,"File 123","File123",valVermittlung,valAkte,valValue);
        } catch (SQLException e) {
            System.out.println("Exception: "+e.getMessage());
            e.printStackTrace();
        }

     /* @param mandate Mandate
     * @param client Client
     * @param file Document file
     * @param fileNumber Document file number
     * @param recommendedBy Name of referrer
     * @param caseDesc Case description
     * @param caseValue Value of the case
     * */

    
    }

    // Handler for ComboBox[fx:id="mandant"] onAction
    public void handleMandateDropdown(ActionEvent event) {
        System.out.println("Mandate has been selected. (eventType="+event.getEventType() + " target="+event.getTarget()+")");
        
        String value = mandat.getValue();
        if (value == null || value.isEmpty()) {
            System.out.println("Selected item is empty.");
            return;
        }
        System.out.println("Selected Mandate: "+value);
    }

    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        assert akte != null : "fx:id=\"akte\" was not injected: check your FXML file 'JLawyerMain.fxml'.";
        assert mandant != null : "fx:id=\"mandant\" was not injected: check your FXML file 'JLawyerMain.fxml'.";
        assert mandat != null : "fx:id=\"mandat\" was not injected: check your FXML file 'JLawyerMain.fxml'.";
        assert vermittlung != null : "fx:id=\"vermittlung\" was not injected: check your FXML file 'JLawyerMain.fxml'.";
        assert addButton != null : "fx:id=\"addButton\" was not injected: check your FXML file 'JLawyerMain.fxml'.";
        assert value != null : "fx:id=\"value\" was not injected: check your FXML file 'JLawyerMain.fxml'.";
        assert aktenzeichen != null : "fx:id=\"aktenzeichen\" was not injected: check your FXML file 'JLawyerMain.fxml'.";
        
        ObservableList<String> mandatList = FXCollections.observableArrayList(
                "Mandat 1",
                "Mandat 2",
                "Mandat 3");
        mandat.setItems(mandatList);

        ObservableList<String> mandantList = FXCollections.observableArrayList(
                "Mandant 1",
                "Mandant 2",
                "Mandant 3");
        mandant.setItems(mandantList);

        ObservableList<String> vermittelungList = FXCollections.observableArrayList(
                "Vermittelung 1",
                "Vermittelung 2",
                "Vermittelung 3");
        vermittlung.setItems(vermittelungList);
 
        initializeLegalFileTable();
    }


    void initializeLegalFileTable() {
        // how to popuate a table defined in fxml see 
        // http://stackoverflow.com/questions/11180884/how-to-populate-a-tableview-that-is-defined-in-an-fxml-file-that-is-designed-in
        colClient.setCellValueFactory(new PropertyValueFactory<LegalFile,String>("client"));
        colMandate.setCellValueFactory(new PropertyValueFactory<LegalFile,String>("mandate"));
        colFile.setCellValueFactory(new PropertyValueFactory<LegalFile,String>("file"));
        colRecommndedBy.setCellValueFactory(new PropertyValueFactory<LegalFile,String>("recommndedBy"));
        colFileNumber.setCellValueFactory(new PropertyValueFactory<LegalFile,String>("fileNumber"));
        colCaseDesc.setCellValueFactory(new PropertyValueFactory<LegalFile,String>("caseDesc"));
        
        legalFileTable.getItems().setAll(LegalFileController.parseLegalFileTable());
    }
    
     
}
    
    