/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.jayway.restassured.RestAssured;
import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.parsing.Parser;
import static org.hamcrest.Matchers.equalTo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import static com.jayway.restassured.RestAssured.given;
import enitity.Company;
import enitity.Person;
import enitity.Phone;
import static org.junit.Assert.assertEquals;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.given;
import enitity.Hobby;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static org.junit.Assert.assertEquals;


/**
 *
 * @author Cherry Rose Semeña
 */
public class PersonCompanyIntegrationTest {

    private static EntityManagerFactory emf;
    
    public PersonCompanyIntegrationTest() {
        
        emf = Persistence.createEntityManagerFactory("remote");
    }

    @BeforeClass
    public static void setUpBeforeAll() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/CA-2/";
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

    @Test
    public void serverIsRunning() {
        given().when().get("http://localhost:8080/CA-2/").then().statusCode(200);
    }

    /**
     * Test of getJSONPersons method, of class PersonFacadeREST.
     */
    @Test
    public void testGetJSONPersons() {
        Person[] persons = 
                given().
                when().get("/api/person/complete").as(Person[].class);
        assertEquals(4,persons.length);

    }

    /**
     * Test of getJSONPerson method, of class PersonFacadeREST.
     */
    @Test
    public void testGetJSONPerson() {
        given().
                pathParam("id", 1)
                .when()
                .get("/api/person/complete/{id}")
                .then()
                .statusCode(200)
                .body("firstName", equalTo("cherry"))
                .body("lastName", equalTo("aa"));
    }

    /**
     * Test of getJSONPersonContact method, of class PersonFacadeREST.
     */
    @Test
    public void testGetJSONPersonContact(){
        Person[] persons = 
                given().
                when().get("/api/person/contactinfo").as(Person[].class);
        assertEquals(4,persons.length);
    }

    /**
     * Test of getJSONPersonContactByPerson method, of class PersonFacadeREST.
     */
    @Test
    public void testGetJSONPersonContactByPerson(){
        given().
                pathParam("id", 1)
                .when()
                .get("/api/person/contactinfo/{id}")
                .then()
                .statusCode(200)
                .body("email", equalTo("cjs@email.dk"));
        
        //Trying to test the phone list as well but it encounters a gsonSyntaxException
        
//        Phone[] phones = 
//                given().pathParam("id", 1)
//                .when().get("/api/person/contactinfo/{id}").as(Phone[].class);
//        
//        assertEquals(1,phones.length);
        
    }
    
    /**
     * Test of getJSONPersons method, of class CompanyFacadeREST.
     */
    @Test
    public void testGetJSONCompanies() {
        Company[] companies = 
                given().
                when().get("/api/company/complete").as(Company[].class);
        assertEquals(6,companies.length); //size should be updated before running the test
    }

    /**
     * Test of getJSONPerson method, of class CompanyFacadeREST.
     */
    @Test
    public void testGetJSONCompany() {
        given().
                pathParam("id", 5)
                .when()
                .get("/api/company/complete/{id}")
                .then()
                .statusCode(200)
                .body("name", equalTo("Company A"))
                .body("cvr", equalTo("1234"));
    }

    /**
     * Test of getJSONPersonContact method, of class CompanyFacadeREST.
     */
    @Test
    public void testGetJSONCompanyContact(){
        Company[] companies = 
                given().
                when().get("/api/company/contactinfo").as(Company[].class);        
        assertEquals(6,companies.length);//size should be updated before running the test
    }

    /**
     * Test of getJSONPersonContactByPerson method, of class CompanyFacadeREST.
     */
    @Test
    public void testGetJSONCompanyContactById(){
        given().
                pathParam("id", 5)
                .when()
                .get("/api/company/contactinfo/{id}")
                .then()
                .statusCode(200)
                .body("email", equalTo("CompanyA@gmail.dk"));        
        //Trying to test the phone list as well but it encounters a gsonSyntaxException
        
//        Phone[] phones = 
//                given().pathParam("id", 5)
//                .when().get("/api/company/contactinfo/{id}").as(Phone[].class);
//        
//        assertEquals(1,phones.length);       
    }
    
    
    
    @Test
    @Ignore //as it will add a lot of hobbies everytime we try to test the file
    public void testAddHobby(){
        Map<String,String> hobby = new HashMap<>();
        hobby.put("name", "badminton");
        hobby.put("description", "outdoor/indoor game");
        
        given()
        .contentType("application/json")
        .body(hobby)
        .when().post("/api/hobby").then()
        .statusCode(200)
//        .body("id", equalTo(6)) //It is not good to test the id since it increments everytime we added new even though that id was removed
        .body("name", equalTo("badminton"));
        
        // Tear Down
//        EntityManager em = emf.createEntityManager();
//        em.getTransaction().begin();
//        Hobby hobbyToBeRemoved = em.find(Hobby.class, 7);//The id should be changed depends on the last id in the Hobby table
//        em.remove(hobbyToBeRemoved);
//        em.getTransaction().commit();
//        em.close();
        
    }
    
    @Test
    @Ignore //as it will add a lot of hobbies everytime we try to test the file
    public void testAddCompany(){
        Map<String,String> com = new HashMap<>();
        com.put("cvr", "1102");
        com.put("name", "pwc");
        com.put("description", "pwc");
        com.put("employees", "5000");
        com.put("marketValue", "154565");
        
        given()
        .contentType("application/json")
        .body(com)
        .when().post("/api/company").then()
        .statusCode(200);
    }
    
    @Test
    public void testInvalidIdEntryGetPerson(){
        given().
                pathParam("id", 500)
                .when()
                .get("/api/person/complete/{id}")
                .then()
                .statusCode(500);
    }
    
    @Test
    public void testInvalidPath(){
        given()
                .when()
                .get("/api/kgæeldskfæwkfhews")
                .then()
                .statusCode(404);
    }
    
}
