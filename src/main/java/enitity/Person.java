/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enitity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

/**
 *
 * @author rasmus
 */
@Entity
public class Person extends InfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    String firstName, lastName;
    @ManyToMany(mappedBy = "personCollection", cascade = CascadeType.MERGE)
    private Collection<enitity.Hobby> hobbyCollection;

    public Person() {
    }

    public Person(String firstName, String lastName, String email, Collection<Hobby> hobbyCollection) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.hobbyCollection = hobbyCollection;
        super.setEmail(email);
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
    
    

    @Override
    public void setId(Integer id) {
        super.setId(id); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Integer getId() {
        return super.getId(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEmail() {
        return super.getEmail(); //To change body of generated methods, choose Tools | Templates.
    }
    

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Collection<Hobby> getHobbyCollection() {
        return hobbyCollection;
    }

    public void setHobbyCollection(Collection<Hobby> hobbyCollection) {
        this.hobbyCollection = hobbyCollection;
    }
    
    

    
    
}
