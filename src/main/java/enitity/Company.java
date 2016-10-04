/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enitity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author rasmus
 */
@Entity
public class Company extends InfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    String name, description, cvr, marketValue;
    int NumEmployees;

    public Company() {
    }

    public Company(String name, String cvr, String email, String marketValue, int NumEmployees) {
        this.name = name;
        this.cvr = cvr;
        super.setEmail(email);
        this.marketValue = marketValue;
        this.NumEmployees = NumEmployees;
    }
    
    @Override
    public void setEmail(String email) {
        super.setEmail(email); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEmail() {
        return super.getEmail(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setId(Integer id) {
        super.setId(id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer getId() {
        return super.getId(); //To change body of generated methods, choose Tools | Templates.
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCvr() {
        return cvr;
    }

    public void setCvr(String cvr) {
        this.cvr = cvr;
    }

    public String getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(String marketValue) {
        this.marketValue = marketValue;
    }

    public int getNumEmployees() {
        return NumEmployees;
    }

    public void setNumEmployees(int NumEmployees) {
        this.NumEmployees = NumEmployees;
    }

    @Override
    public void setAdress(Address adress) {
        super.setAdress(adress); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Address getAdress() {
        return super.getAdress(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPhoneCollection(Collection<Phone> phoneCollection) {
        super.setPhoneCollection(phoneCollection); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Phone> getPhoneCollection() {
        return super.getPhoneCollection(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
}
