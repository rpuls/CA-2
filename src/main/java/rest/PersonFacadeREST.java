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
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String addPerson(String content){
        Person p = gson.fromJson(content, Person.class);
        Person newPerson = facade.addPerson(p);
        return gson.toJson(newPerson);
    }
}
    