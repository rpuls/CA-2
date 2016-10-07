/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enitity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author rasmus
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@XmlRootElement
public class InfoEntity implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    
//    @ElementCollection
//    @OneToMany
//    private List<Person> persons = new ArrayList();
//    
//    @ElementCollection
//    @OneToMany
//    private List<Company> companies = new ArrayList();
    
    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "infoentity")
    private Collection<enitity.Phone> phoneCollection;
    
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST)
    private Address adress;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public Collection<Phone> getPhoneCollection() {
        return phoneCollection;
    }

    public void setPhoneCollection(Collection<Phone> phoneCollection) {
        this.phoneCollection = phoneCollection;
    }

    public Address getAdress() {
        return adress;
    }

    public void setAdress(Address adress) {
        this.adress = adress;
    }

//    public List<Person> getPersons() {
//        return persons;
//    }
    
    
    
    
    
    
    
}
