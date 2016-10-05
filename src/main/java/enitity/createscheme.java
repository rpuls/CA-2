/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enitity;

import facade.Facade;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Persistence;

/**
 *
 * @author rasmus
 */
public class createscheme {
    public static void main(String[] args) {
        
        Persistence.generateSchema("remote", null);
        
//        Facade f = new Facade(Persistence.createEntityManagerFactory("remote"));
//        List<Company> cList = f.getCompanies();
//        for (Company company : cList) {
//            System.out.println("Company:" + company.getName());
//        }
    }
    
}
