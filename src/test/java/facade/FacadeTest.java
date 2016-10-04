/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import enitity.Person;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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
        
    }

    @Test
    public void testGetCompany() {
    }

    @Test
    public void testGetPersonsByHobby() {
    }

    @Test
    public void testGetPersonsByCity() {
    }

    @Test
    public void testGetCountOfPeopleByHobby() {
    }

    @Test
    public void testGetZipCodes() {
    }

    @Test
    public void testGetCompaniesByEmpAmount() {
    }

    @Test
    public void testAddPerson() {
        Person p = new Person();
        facade.addPerson(p);
    }

    @Test
    public void testAddCompany() {
    }

    @Test
    public void testAddHobby() {
    }

    @Test
    public void testAddInfoEntity() {
    }

    @Test
    public void testAddPhone() {
    }

    @Test
    public void testAddAddress() {
    }

    @Test
    public void testAddCityInfo() {
    }
    
}
