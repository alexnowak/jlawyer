/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jlawyer.model;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Legal file data. Stored in database
 * 
 * @author anowak
 */
public class LegalFile {
    private SimpleLongProperty id;
    private SimpleStringProperty mandate;
    private SimpleStringProperty client;
    private SimpleStringProperty file;
    private SimpleStringProperty fileNumber;
    private SimpleStringProperty recommendedBy;
    private SimpleStringProperty caseDesc;

    /**
     * Constructor for LegalFile will all data base data
     * @param id ID
     * @param mandate Mandate
     * @param client Client
     * @param file Document file
     * @param fileNumber Document file number
     * @param recommendedBy Name of referrer
     * @param caseDesc Case description
     */
    public LegalFile(long id, String mandate,String client,String file,String fileNumber,
            String recommendedBy, String caseDesc) {
        this.id             = new SimpleLongProperty(id);
        this.mandate        = new SimpleStringProperty(mandate);
        this.client         = new SimpleStringProperty(client);
        this.file           = new SimpleStringProperty(file);
        this.fileNumber     = new SimpleStringProperty(fileNumber);
        this.recommendedBy  = new SimpleStringProperty(recommendedBy);
        this.caseDesc       = new SimpleStringProperty(caseDesc);
    }
    
    public long getId() {
        return id.get();
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public String getMandate() {
        return mandate.get();
    }

    public void setMandate(String mandate) {
        this.mandate.set(mandate);
    }

    public String getClient() {
        return client.get();
    }

    public void setClient(String client) {
        this.client.set(client);
    }

    public String getFile() {
        return file.get();
    }

    public void setFile(String file) {
        this.file.set(file);
    }

    public String getFileNumber() {
        return fileNumber.get();
    }

    public void setFileNumber(String fileNumber) {
        this.fileNumber.set(fileNumber);
    }

    public String getRecommendedBy() {
        return recommendedBy.get();
    }

    public void setRecommendedBy(String recommendedBy) {
        this.recommendedBy.set(recommendedBy);
    }

    public String getCaseDesc() {
        return caseDesc.get();
    }

    public void setCaseDesc(String caseDesc) {
        this.caseDesc.set(caseDesc);
    }
 
    public String toString() {
        return "("+getId() + "," +
            "\"" + getMandate() + "\", " +
            "\"" + getClient() + "\", " +
            "\"" + getFile() + "\", " +
            "\"" + getFileNumber() + "\", " +
            "\"" + getRecommendedBy() + "\", " +
            "\"" + getCaseDesc()+"\")";
    }
}
