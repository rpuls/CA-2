/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import enitity.Person;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import java.util.List;
import javax.ejb.embeddable.EJBContainer;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Cherry Rose Semeña
 */
public class PersonFacadeRESTTest {
    
    public PersonFacadeRESTTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/Test1";
        RestAssured.defaultParser = Parser.JSON;
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

    /**
     * Test of edit method, of class PersonFacadeREST.
     */
    @Test
    @Ignore
    public void testEdit() throws Exception {
        
    }

    /**
     * Test of remove method, of class PersonFacadeREST.
     */
    @Test
    @Ignore
    public void testRemove() throws Exception {
        
    }

    /**
     * Test of find method, of class PersonFacadeREST.
     */
    @Test
    @Ignore
    public void testFind() throws Exception {
        
    }

    /**
     * Test of findRange method, of class PersonFacadeREST.
     */
    @Test
    @Ignore
    public void testFindRange() throws Exception {
        
    }

    /**
     * Test of count method, of class PersonFacadeREST.
     */
    @Test
    @Ignore
    public void testCount() throws Exception {
       
    }

    /**
     * Test of findAll method, of class PersonFacadeREST.
     */
    @Test
    @Ignore
    public void testFindAll() throws Exception {
        
    }

    /**
     * Test of getJSONPersons method, of class PersonFacadeREST.
     */
    @Test
    @Ignore
    public void testGetJSONPersons() throws Exception {
        
    }

    /**
     * Test of getJSONPerson method, of class PersonFacadeREST.
     */
    @Test
    @Ignore
    public void testGetJSONPerson() throws Exception {
        
    }

    /**
     * Test of getJSONPersonContact method, of class PersonFacadeREST.
     */
    @Test
    @Ignore
    public void testGetJSONPersonContact() throws Exception {
        
    }

    /**
     * Test of getJSONPersonContactByPerson method, of class PersonFacadeREST.
     */
    @Test
    @Ignore
    public void testGetJSONPersonContactByPerson() throws Exception {
        
    }

    /**
     * Test of getJSONPersonByPhone method, of class PersonFacadeREST.
     */
    @Test
    @Ignore
    public void testGetJSONPersonByPhone() throws Exception {
       
    }

    /**
     * Test of create method, of class PersonFacadeREST.
     */
    @Test
    @Ignore
    public void testCreate() throws Exception {
        
    }

    /**
     * Test of edit method, of class PersonFacadeREST.
     */
    @Test
    @Ignore
    public void testEdit_Integer_Person() throws Exception {
        
    }

    /**
     * Test of remove method, of class PersonFacadeREST.
     */
    @Test
    @Ignore
    public void testRemove_Integer() throws Exception {
        
    }

    /**
     * Test of find method, of class PersonFacadeREST.
     */
    @Test
    @Ignore
    public void testFind_Integer() throws Exception {
        
    }

    /**
     * Test of findRange method, of class PersonFacadeREST.
     */
    @Test
    @Ignore
    public void testFindRange_Integer_Integer() throws Exception {
       
    }

    /**
     * Test of countREST method, of class PersonFacadeREST.
     */
    @Test
    @Ignore
    public void testCountREST() throws Exception {
        
    }
    
}
