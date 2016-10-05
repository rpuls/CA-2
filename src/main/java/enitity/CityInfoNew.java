/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enitity;

import enitity.*;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author rasmus
 */
@Entity
@Table(name = "CITYINFO")
@NamedQueries({
    @NamedQuery(name = "Cityinfo.findAll", query = "SELECT c FROM CityInfoNew c"),
    @NamedQuery(name = "Cityinfo.findByZipCode", query = "SELECT c FROM CityInfoNew c WHERE c.zipCode = :zipCode"),
    @NamedQuery(name = "Cityinfo.findByCity", query = "SELECT c FROM CityInfoNew c WHERE c.city = :city")})
@XmlRootElement
public class CityInfoNew implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Size(max = 45)
    @Column(name = "ZIP")
    private String zipCode;
    @Size(max = 45)
    @Column(name = "CITY")
    private String city;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cityinfo")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cityInfoNew")
    private Collection<Address> addressCollection;

    public CityInfoNew() {
    }

    /**
     * This is only used for the test since there is already a script to populate the cityInfo
     * @param zipCode
     * @param city
     */
    public CityInfoNew(String zipCode, String city) {
        this.zipCode = zipCode;
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @XmlTransient
    public Collection<Address> getAddressCollection() {
        return addressCollection;
    }

    public void setAddressCollection(Collection<Address> addressCollection) {
        this.addressCollection = addressCollection;
    }

}
