/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import enitity.Person;
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
    public void testEdit_GenericType() throws Exception {
        System.out.println("edit");
        Person entity = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        PersonFacadeREST instance = (PersonFacadeREST)container.getContext().lookup("java:global/classes/PersonFacadeREST");
        instance.edit(entity);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class PersonFacadeREST.
     */
    @Test
    @Ignore
    public void testRemove_GenericType() throws Exception {
        System.out.println("remove");
        Person entity = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        PersonFacadeREST instance = (PersonFacadeREST)container.getContext().lookup("java:global/classes/PersonFacadeREST");
        instance.remove(entity);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of find method, of class PersonFacadeREST.
     */
    @Test
    @Ignore
    public void testFind_Object() throws Exception {
        System.out.println("find");
        Object id = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        PersonFacadeREST instance = (PersonFacadeREST)container.getContext().lookup("java:global/classes/PersonFacadeREST");
        Person expResult = null;
        Person result = instance.find(id);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findRange method, of class PersonFacadeREST.
     */
    @Test
    @Ignore
    public void testFindRange_intArr() throws Exception {
        System.out.println("findRange");
        int[] range = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        PersonFacadeREST instance = (PersonFacadeREST)container.getContext().lookup("java:global/classes/PersonFacadeREST");
        List<Person> expResult = null;
        List<Person> result = instance.findRange(range);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of count method, of class PersonFacadeREST.
     */
    @Test
    @Ignore
    public void testCount() throws Exception {
        System.out.println("count");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        PersonFacadeREST instance = (PersonFacadeREST)container.getContext().lookup("java:global/classes/PersonFacadeREST");
        int expResult = 0;
        int result = instance.count();
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAll method, of class PersonFacadeREST.
     */
    @Test
    @Ignore
    public void testFindAll() throws Exception {
        System.out.println("findAll");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        PersonFacadeREST instance = (PersonFacadeREST)container.getContext().lookup("java:global/classes/PersonFacadeREST");
        List<Person> expResult = null;
        List<Person> result = instance.findAll();
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getJSONPersons method, of class PersonFacadeREST.
     */
    @Test
    @Ignore
    public void testGetJSONPersons() throws Exception {
        System.out.println("getJSONPersons");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        PersonFacadeREST instance = (PersonFacadeREST)container.getContext().lookup("java:global/classes/PersonFacadeREST");
        String expResult = "";
        String result = instance.getJSONPersons();
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getJSONPerson method, of class PersonFacadeREST.
     */
    @Test
    @Ignore
    public void testGetJSONPerson() throws Exception {
        System.out.println("getJSONPerson");
        Integer id = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        PersonFacadeREST instance = (PersonFacadeREST)container.getContext().lookup("java:global/classes/PersonFacadeREST");
        String expResult = "";
        String result = instance.getJSONPerson(id);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getJSONPersonContact method, of class PersonFacadeREST.
     */
    @Test
    @Ignore
    public void testGetJSONPersonContact() throws Exception {
        System.out.println("getJSONPersonContact");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        PersonFacadeREST instance = (PersonFacadeREST)container.getContext().lookup("java:global/classes/PersonFacadeREST");
        String expResult = "";
        String result = instance.getJSONPersonContact();
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getJSONPersonContactByPerson method, of class PersonFacadeREST.
     */
    @Test
    @Ignore
    public void testGetJSONPersonContactByPerson() throws Exception {
        System.out.println("getJSONPersonContactByPerson");
        Integer id = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        PersonFacadeREST instance = (PersonFacadeREST)container.getContext().lookup("java:global/classes/PersonFacadeREST");
        String expResult = "";
        String result = instance.getJSONPersonContactByPerson(id);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getJSONPersonByPhone method, of class PersonFacadeREST.
     */
    @Test
    @Ignore
    public void testGetJSONPersonByPhone() throws Exception {
        System.out.println("getJSONPersonByPhone");
        String number = "";
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        PersonFacadeREST instance = (PersonFacadeREST)container.getContext().lookup("java:global/classes/PersonFacadeREST");
        String expResult = "";
        String result = instance.getJSONPersonByPhone(number);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of create method, of class PersonFacadeREST.
     */
    @Test
    @Ignore
    public void testCreate() throws Exception {
        System.out.println("create");
        Person entity = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        PersonFacadeREST instance = (PersonFacadeREST)container.getContext().lookup("java:global/classes/PersonFacadeREST");
        instance.create(entity);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of edit method, of class PersonFacadeREST.
     */
    @Test
    @Ignore
    public void testEdit_Integer_Person() throws Exception {
        System.out.println("edit");
        Integer id = null;
        Person entity = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        PersonFacadeREST instance = (PersonFacadeREST)container.getContext().lookup("java:global/classes/PersonFacadeREST");
        instance.edit(id, entity);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class PersonFacadeREST.
     */
    @Test
    @Ignore
    public void testRemove_Integer() throws Exception {
        System.out.println("remove");
        Integer id = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        PersonFacadeREST instance = (PersonFacadeREST)container.getContext().lookup("java:global/classes/PersonFacadeREST");
        instance.remove(id);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of find method, of class PersonFacadeREST.
     */
    @Test
    @Ignore
    public void testFind_Integer() throws Exception {
        System.out.println("find");
        Integer id = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        PersonFacadeREST instance = (PersonFacadeREST)container.getContext().lookup("java:global/classes/PersonFacadeREST");
        Person expResult = null;
        Person result = instance.find(id);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findRange method, of class PersonFacadeREST.
     */
    @Test
    @Ignore
    public void testFindRange_Integer_Integer() throws Exception {
        System.out.println("findRange");
        Integer from = null;
        Integer to = null;
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        PersonFacadeREST instance = (PersonFacadeREST)container.getContext().lookup("java:global/classes/PersonFacadeREST");
        List<Person> expResult = null;
        List<Person> result = instance.findRange(from, to);
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of countREST method, of class PersonFacadeREST.
     */
    @Test
    @Ignore
    public void testCountREST() throws Exception {
        System.out.println("countREST");
        EJBContainer container = javax.ejb.embeddable.EJBContainer.createEJBContainer();
        PersonFacadeREST instance = (PersonFacadeREST)container.getContext().lookup("java:global/classes/PersonFacadeREST");
        String expResult = "";
        String result = instance.countREST();
        assertEquals(expResult, result);
        container.close();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
