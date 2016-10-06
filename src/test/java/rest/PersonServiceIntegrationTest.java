/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.jayway.restassured.RestAssured;
import static com.jayway.restassured.RestAssured.given;
import com.jayway.restassured.parsing.Parser;
import javax.persistence.Persistence;
import static org.hamcrest.Matchers.equalTo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import static com.jayway.restassured.RestAssured.given;
import enitity.Person;


/**
 *
 * @author Cherry Rose Semeña
 */
public class PersonServiceIntegrationTest {

    public PersonServiceIntegrationTest() {
    }

    @BeforeClass
    public static void setUpBeforeAll() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "/CA-2/";
        RestAssured.defaultParser = Parser.JSON;

        Persistence.generateSchema("pu_test", null);
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
//        given().
//                when().get().
//                then().
//                statusCode(200);
    }

    /**
     * Test of getJSONPersons method, of class PersonFacadeREST.
     */
    @Test
    @Ignore
    public void testGetJSONPersons() {
//        given().
//                when()
//                .get("/api/person/complete")
//                .then()
//                .statusCode(200);

    Person[] persons = given().when().get("/api/person/complete").as(Person[].class);
    
        for (Person person : persons) {
            System.out.println("Person: " + person.getFirstName());
        }

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
}
