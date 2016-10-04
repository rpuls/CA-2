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
import enitity.Person;
import enitity.Phone;
import facade.Facade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
public class PersonFacadeREST extends AbstractFacade<Person> {
    
    static Facade facade = new Facade(Persistence.createEntityManagerFactory("remote"));
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @PersistenceContext(unitName = "remote")
    private EntityManager em;

    public PersonFacadeREST() {
        super(Person.class);
    }
    
    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Person> findAll() {
        return super.findAll();
    }  
    
    @GET
    @Path("complete")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJSONPersons() {
        List<Person> persons = facade.getPersons();
        List<JsonObject> jList = new ArrayList();
        for (Person p : persons) {
            Address adr = facade.getAdressByPerson(p);
            CityInfoNew cti = facade.getCityInfoByPerson(p);
            JsonObject job = new JsonObject();
            job.addProperty("firstName", p.getFirstName());
            job.addProperty("lastName", p.getLastName());
            job.addProperty("email", p.getEmail());
            job.addProperty("phones", gson.toJson(facade.getPhonesByPerson(p))); //MISSING - beware, that we might run in to s stackoverflow here
            job.addProperty("street", adr.getStreet());
            job.addProperty("additionalInfo", adr.getAdditionalInfo());
            job.addProperty("zipCode", cti.getCity());
            job.addProperty("city", cti.getCity());
            jList.add(job);
        }
        return gson.toJson(jList);
    }
    
    @GET
    @Path("complete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJSONPerson(@PathParam("id") Integer id) {
        Person p = facade.getPersonById(id);
            Address adr = facade.getAdressByPerson(p);
            CityInfoNew cti = facade.getCityInfoByPerson(p);
            JsonObject job = new JsonObject();
            job.addProperty("firstName", p.getFirstName());
            job.addProperty("lastName", p.getLastName());
            job.addProperty("email", p.getEmail());
            job.addProperty("phones", gson.toJson(facade.getPhonesByPerson(p))); //MISSING - beware, that we might run in to s stackoverflow here
            job.addProperty("street", adr.getStreet());
            job.addProperty("additionalInfo", adr.getAdditionalInfo());
            job.addProperty("zipCode", cti.getCity());
            job.addProperty("city", cti.getCity());
        
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
            job.addProperty("id", p.getId());
            job.addProperty("name", p.getFirstName() + " " +  p.getLastName());
            job.addProperty("email", p.getEmail());
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
            job.addProperty("id", p.getId());
            job.addProperty("name", p.getFirstName() + " " +  p.getLastName());
            job.addProperty("email", p.getEmail());
            job.addProperty("phones", gson.toJson(facade.getPhonesByPerson(p))); //MISSING - beware, that we might run in to s stackoverflow here
        return gson.toJson(job);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Person entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Person entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Person find(@PathParam("id") Integer id) {
        return super.find(id);
    }


    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Person> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}