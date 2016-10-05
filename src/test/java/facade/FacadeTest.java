/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import enitity.Address;
import enitity.CityInfoNew;
import enitity.Company;
import enitity.Hobby;
import enitity.Person;
import enitity.Phone;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Daniel
 */
public class FacadeTest {
    
    private static EntityManagerFactory emf;
    private static Facade facade;
    
    public FacadeTest() {
        emf = Persistence.createEntityManagerFactory("pu_test");
        facade = new Facade(emf);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetPerson() {
        String phonenumber = "123456789"; // 
        Person p = facade.getPersonByPhone(phonenumber);
        Collection<Phone> l = p.getPhoneCollection();
        String foundPhonenumber="";
        for (Phone num : l) {
           foundPhonenumber = num.getNumer();
        }
        assertTrue(phonenumber.equalsIgnoreCase(foundPhonenumber));
    }

    @Test
    public void testGetZipCodes(){
        List<CityInfoNew> cityList = facade.getZipCodes();
        assertEquals(4,cityList.size());
    }
   
    @Test
    public void testGetPersonsByCity(){
        List<Person> pList = (List<Person>) facade.getPersonsByCity("Vedbæk");
        
        assertEquals(1,pList.size());
        
}
    
    @Test
    @Ignore
    public void testGetPersonsByHobby() {
        int expectedCount = 0; // Should change when I know whats in the database
        Hobby hobby = new Hobby("Basket", "Fun"); // this should change when I know whats in the database!!!
        Collection<Person> persons = facade.getPersonsByHobby(hobby);
        assertTrue(expectedCount == persons.size());
    }

    @Test
    @Ignore
    public void testAddPerson() {
        Person p = new Person("Daniel", "Hollmann", "danielhollmann@hotmail.com", null);
        Person found = facade.addPerson(p);
        assertTrue(found.getId() > 0);
    }
    
    @Test
    public void testCompanyAndAdresseAdd() {
        EntityManager em = emf.createEntityManager();
        Address d = em.find(Address.class, 1);
        Company c = new Company("Novo", "4040", "novo@gmail.com", "high", 520);
        c.setAdress(d);
        Company found = facade.addCompany(c);
        assertTrue(found.getId() > 0);
        assertTrue("Failed Found:  " + found.getAdress().getStreet(), d == found.getAdress());
    }
    
    @Test
    public void testGetPhonesByCompany(){
        EntityManager em = emf.createEntityManager();
        Company c = em.find(Company.class, 5); // Gets the first Company We know that has the id of 5!
        int numberOfPhoneEntitys = c.getPhoneCollection().size(); // returns the number of Phones for that company
        String[] result = facade.getPhonesByCompany(c);
        assertTrue(numberOfPhoneEntitys == result.length);
    }

    
}
