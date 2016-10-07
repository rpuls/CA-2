/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import enitity.Address;
import enitity.CityInfoNew;
import enitity.Hobby;
import enitity.Person;
import facade.Facade;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author rasmus
 */
@Stateless
@Path("person")
public class PersonFacadeREST {
    
    static Facade facade = new Facade(Persistence.createEntityManagerFactory("remote"));
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    static Gson gson2 = new GsonBuilder().create();

    @PersistenceContext(unitName = "remote")
    private EntityManager em;

    public PersonFacadeREST() {

    }
    
    /**
     * gets a List of Person objects from the facade.
     * sets json properties: firstName, lastName, email, street,
     * additionalInfo, zipCode, city - if they are not null.
     * @return Json String
     */
    @GET
    @Path("complete")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJSONPersons() {
        List<Person> persons = facade.getPersons();
        List<JsonObject> jList = new ArrayList();
        for (Person p : persons) {
            JsonObject job = new JsonObject();
            if(p.getFirstName()!=null){job.addProperty("firstName", p.getFirstName());}
            if(p.getLastName()!=null){job.addProperty("lastName", p.getLastName());}
            if(p.getEmail()!=null){job.addProperty("email", p.getEmail());}
            job.addProperty("phones", gson2.toJson(facade.getPhonesByPerson(p)));
            if(facade.getAdressByPerson(p)!=null){
                Address adr = facade.getAdressByPerson(p);
                if(adr.getStreet()!=null){job.addProperty("street", adr.getStreet());}
                if(adr.getAdditionalInfo()!=null){job.addProperty("additionalInfo", adr.getAdditionalInfo());}
            }
            if(facade.getCityInfoByPerson(p)!=null){
                CityInfoNew cti = facade.getCityInfoByPerson(p);
                if(cti.getZipCode()!=null){job.addProperty("zipCode", cti.getZipCode());}
                if(cti.getCity()!=null){job.addProperty("city", cti.getCity());}
            }
            jList.add(job);
        }
        return gson.toJson(jList);
    }


    /**
     gets one Person object from the facade by given id.
     * sets json properties: firstName, lastName, email, street,
     * additionalInfo, zipCode, city - if they are not null.
     * @param id
     * @return Json String
     */
    @GET
    @Path("complete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJSONPerson(@PathParam("id") Integer id) {
        Person p = facade.getPersonById(id);
            JsonObject job = new JsonObject();
            if(p.getFirstName()!=null){job.addProperty("firstName", p.getFirstName());}
            if(p.getLastName()!=null){job.addProperty("lastName", p.getLastName());}
            if(p.getEmail()!=null){job.addProperty("email", p.getEmail());}
            job.addProperty("phones", gson.toJson(facade.getPhonesByPerson(p)));
            if(facade.getAdressByPerson(p)!=null){
                Address adr = facade.getAdressByPerson(p);
                if(adr.getStreet()!=null){job.addProperty("street", adr.getStreet());}
                if(adr.getAdditionalInfo()!=null){job.addProperty("additionalInfo", adr.getAdditionalInfo());}
            }
            if(facade.getCityInfoByPerson(p)!=null){
                CityInfoNew cti = facade.getCityInfoByPerson(p);
                if(cti.getZipCode()!=null){job.addProperty("zipCode", cti.getZipCode());}
                if(cti.getCity()!=null){job.addProperty("city", cti.getCity());}
            }
        return gson.toJson(job);
    }
    
    /**
     * gets a List of Person objects from the facade.
     * sets json properties: id, name, email, phones - if they are not null.
     * @return Json String
     */
    @GET
    @Path("contactinfo")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJSONPersonContact(){
        List<Person> persons = facade.getPersons();
        List<JsonObject> jList = new ArrayList();
        for (Person p : persons) {
            JsonObject job = new JsonObject();
            if(p.getId()!=null){job.addProperty("id", p.getId());}
            if(p.getFirstName()!=null && p.getFirstName()!=null){job.addProperty("name", p.getFirstName() + " " +  p.getLastName());}
            if(p.getEmail()!=null){job.addProperty("email", p.getEmail());}
            job.addProperty("phones", gson.toJson(facade.getPhonesByPerson(p))); //MISSING - beware, that we might run in to s stackoverflow here
            jList.add(job);
        }
        return gson.toJson(jList);
    }
    
    /**
     * gets one Person object from the facade, by the given id.
     * sets json properties: id, name, email, phones - if they are not null.
     * @param id
     * @return
     */
    @GET
    @Path("contactinfo/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJSONPersonContactByPerson(@PathParam("id") Integer id){
        Person p = facade.getPersonById(id); 
            JsonObject job = new JsonObject();
            if(p.getId()!=null){job.addProperty("id", p.getId());}
            if(p.getFirstName()!=null && p.getFirstName()!=null){job.addProperty("name", p.getFirstName() + " " +  p.getLastName());}
            if(p.getEmail()!=null){job.addProperty("email", p.getEmail());}
            job.addProperty("phones", gson.toJson(facade.getPhonesByPerson(p))); //MISSING - beware, that we might run in to s stackoverflow here
        return gson.toJson(job);
    }
    
    /**
     * Gets one Person object from the facade by the given phone number
     * sets json properties: firstName, lastName, email, street,
     * additionalInfo, zipCode, city - if they are not null.
     * @param number
     * @return json String
     */
    @GET
    @Path("phone/{number}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJSONPersonByPhone(@PathParam("number") String number){
        Person p = facade.getPersonByPhone(number);
            JsonObject job = new JsonObject();
            if(p.getFirstName()!=null){job.addProperty("firstName", p.getFirstName());}
            if(p.getLastName()!=null){job.addProperty("lastName", p.getLastName());}
            if(p.getEmail()!=null){job.addProperty("email", p.getEmail());}
            job.addProperty("phones", gson.toJson(facade.getPhonesByPerson(p)));
            if(facade.getAdressByPerson(p)!=null){
                Address adr = facade.getAdressByPerson(p);
                if(adr.getStreet()!=null){job.addProperty("street", adr.getStreet());}
                if(adr.getAdditionalInfo()!=null){job.addProperty("additionalInfo", adr.getAdditionalInfo());}
            }
            if(facade.getCityInfoByPerson(p)!=null){
                CityInfoNew cti = facade.getCityInfoByPerson(p);
                if(cti.getZipCode()!=null){job.addProperty("zipCode", cti.getZipCode());}
                if(cti.getCity()!=null){job.addProperty("city", cti.getCity());}
            }
        return gson.toJson(job);
    }
    
    /**
     * Gets a Collection of Person objects from the facade
     * by the given hobby.
     * sets properties: id, name, email, phones - if they are not null.
     * @param hob
     * @return json String
     */
    @GET
    @Path("hobby/{hob}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJSONPersonContactByPerson(@PathParam("hob") Hobby hob){
        Collection<Person> persons = facade.getPersonsByHobby(hob);
        List<JsonObject> jList = new ArrayList();
        for (Person p : persons) {
            JsonObject job = new JsonObject();
            if(p.getId()!=null){job.addProperty("id", p.getId());}
            if(p.getFirstName()!=null && p.getFirstName()!=null){job.addProperty("name", p.getFirstName() + " " +  p.getLastName());}
            if(p.getEmail()!=null){job.addProperty("email", p.getEmail());}
            job.addProperty("phones", gson.toJson(facade.getPhonesByPerson(p))); //MISSING - beware, that we might run in to s stackoverflow here
            jList.add(job);
            }
        return gson.toJson(jList);
    }
    
    /**
     * Gets a Collection of Person objects from the facade by the given String city
     * sets properties: id, name, email, phones- if they are not null
     * @param city
     * @return json String
     */
    @GET
    @Path("city/{city}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJSONPersonContactByPerson(@PathParam("city") String city){
        Collection<Person> persons = facade.getPersonsByCity(city);
        List<JsonObject> jList = new ArrayList();
        for (Person p : persons) {
            JsonObject job = new JsonObject();
            if(p.getId()!=null){job.addProperty("id", p.getId());}
            if(p.getFirstName()!=null && p.getFirstName()!=null){job.addProperty("name", p.getFirstName() + " " +  p.getLastName());}
            if(p.getEmail()!=null){job.addProperty("email", p.getEmail());}
            job.addProperty("phones", gson.toJson(facade.getPhonesByPerson(p))); //MISSING - beware, that we might run in to s stackoverflow here
            jList.add(job);
            }
        return gson.toJson(jList);
    }
    
    /**
     * Takes a json string and creates a Person object p from the content
     * creates a newPerson object from the facade that takes in p
     * newPerson now has an id and is returned.
     * @param content
     * @return json String
     */
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String addPerson(String content){
        Person p = gson.fromJson(content, Person.class);
        Person newPerson = facade.addPerson(p);
        String jsonString = generateJsonString(newPerson);
        return jsonString;
    }

    private String generateJsonString(Person p) {
        JsonObject job = new JsonObject();
            if(p.getId()!=null){job.addProperty("id", p.getId());}
            if(p.getFirstName()!=null && p.getFirstName()!=null){job.addProperty("name", p.getFirstName() + " " +  p.getLastName());}
            if(p.getEmail()!=null){job.addProperty("email", p.getEmail());}
            job.addProperty("phones", gson.toJson(facade.getPhonesByPerson(p)));
            return gson.toJson(job);
    }
}
    