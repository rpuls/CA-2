package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import enitity.Address;
import enitity.CityInfoNew;
import enitity.Company;
import facade.Facade;
import java.util.ArrayList;
import java.util.Collection;
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
@Path("company")
public class CompanyFacadeREST  {
    
    static Facade facade = new Facade(Persistence.createEntityManagerFactory("remote"));
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @PersistenceContext(unitName = "company")
    private EntityManager em;

    public CompanyFacadeREST() {

    }
    
    /**
     * Gets a List of Company objects from the facade
     * sets properties: cvr, name, email, phones, street, 
     * additionalInfo, zipCode, city - if they are not null
     * @return json String
     */
    @GET
    @Path("complete")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJSONCompaniess() {
        List<Company> companies = facade.getCompanies();
        List<JsonObject> jList = new ArrayList();
        for (Company c : companies) {
            JsonObject job = new JsonObject();
            if(c.getCvr()!=null){job.addProperty("cvr", c.getCvr());}
            if(c.getName()!=null){job.addProperty("name", c.getName());}
            if(c.getEmail()!=null){job.addProperty("email", c.getEmail());}
            job.addProperty("phones", gson.toJson(facade.getPhonesByCompany(c))); 
            if(facade.getAdressByCompany(c)!=null){
                Address adr = facade.getAdressByCompany(c);
                if(adr.getStreet()!=null){job.addProperty("street", adr.getStreet());}
                if(adr.getAdditionalInfo()!=null){job.addProperty("additionalInfo", adr.getAdditionalInfo());}
            }
            if(facade.getCityInfoByCompany(c)!=null){
                CityInfoNew cti = facade.getCityInfoByCompany(c);
                if(cti.getZipCode()!=null){job.addProperty("zipCode", cti.getZipCode());}
                if(cti.getCity()!=null){job.addProperty("city", cti.getCity());}
            }
            jList.add(job);
        }
        return gson.toJson(jList);
    }
    
    /**
     * Gets one Company object from the facade by the given id
     * sets properties: cvr, name, email, phones, street, 
     * additionalInfo, zipCode, city - if they are not null
     * @param id
     * @return json String
     */
    @GET
    @Path("complete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJSONCompanies(@PathParam("id") Integer id) {
        Company c = facade.getCompanyById(id);
        JsonObject job = new JsonObject();
        if(c.getCvr()!=null){job.addProperty("cvr", c.getCvr());}
        if(c.getName()!=null){job.addProperty("name", c.getName());}
        if(c.getEmail()!=null){job.addProperty("email", c.getEmail());}
        job.addProperty("phones", gson.toJson(facade.getPhonesByCompany(c))); 
        if(facade.getAdressByCompany(c)!=null){
            Address adr = facade.getAdressByCompany(c);
            if(adr.getStreet()!=null){job.addProperty("street", adr.getStreet());}
            if(adr.getAdditionalInfo()!=null){job.addProperty("additionalInfo", adr.getAdditionalInfo());}
        }
        if(facade.getCityInfoByCompany(c)!=null){
            CityInfoNew cti = facade.getCityInfoByCompany(c);
            if(cti.getZipCode()!=null){job.addProperty("zipCode", cti.getZipCode());}
            if(cti.getCity()!=null){job.addProperty("city", cti.getCity());}
        }
        return gson.toJson(job);
    }
    
    /**
     * Gets a List of Company objects from the facade.
     * set properties: id, name, email, phones - if they are not null
     * @return json String
     */
    @GET
    @Path("contactinfo")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJSONCompaniesContact(){
        List<Company> companies = facade.getCompanies();
        List<JsonObject> jList = new ArrayList();
        for (Company c : companies) {
            JsonObject job = new JsonObject();
            if(c.getId()!=null){job.addProperty("id", c.getId());}
            if(c.getName()!=null){job.addProperty("name", c.getName());}
            if(c.getEmail()!=null){job.addProperty("email", c.getEmail());}
            job.addProperty("phones", gson.toJson(facade.getPhonesByCompany(c))); //MISSING - beware, that we might run in to s stackoverflow here
            jList.add(job);
        }
        return gson.toJson(jList);
    }
    
    /**
     * Gets one Company object from the facade by the given id.
     * set properties: id, name, email, phones - if they are not null
     * @param id
     * @return json String
     */
    @GET
    @Path("contactinfo/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJSONCompaniesContactByCompanies(@PathParam("id") Integer id){
        Company c = facade.getCompanyById(id); 
            JsonObject job = new JsonObject();
            if(c.getId()!=null){job.addProperty("id", c.getId());}
            if(c.getName()!=null){job.addProperty("name", c.getName());}
            if(c.getEmail()!=null){job.addProperty("email", c.getEmail());}
            job.addProperty("phones", gson.toJson(facade.getPhonesByCompany(c))); //MISSING - beware, that we might run in to s stackoverflow here
        return gson.toJson(job);
    }
    
    /**
     * Gets one Company object from the facede by the given phone number
     * sets properties: cvr, name, email, phones, street, 
     * additionalInfo, zipCode, city - if they are not null
     * @param number
     * @return json String
     */
    @GET
    @Path("phone/{number}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJSONCompanyByPhone(@PathParam("number") String number){
        Company c = facade.getCompanyByPhone(number);
        JsonObject job = new JsonObject();
        if(c.getCvr()!=null){job.addProperty("cvr", c.getCvr());}
        if(c.getName()!=null){job.addProperty("name", c.getName());}
        if(c.getEmail()!=null){job.addProperty("email", c.getEmail());}
        job.addProperty("phones", gson.toJson(facade.getPhonesByCompany(c))); 
        if(facade.getAdressByCompany(c)!=null){
            Address adr = facade.getAdressByCompany(c);
            if(adr.getStreet()!=null){job.addProperty("street", adr.getStreet());}
            if(adr.getAdditionalInfo()!=null){job.addProperty("additionalInfo", adr.getAdditionalInfo());}
        }
        if(facade.getCityInfoByCompany(c)!=null){
            CityInfoNew cti = facade.getCityInfoByCompany(c);
            if(cti.getZipCode()!=null){job.addProperty("zipCode", cti.getZipCode());}
            if(cti.getCity()!=null){job.addProperty("city", cti.getCity());}
        }
        return gson.toJson(job);
    }
    
    /**
     * Gets one Company object from the facede by the given cvr number
     * sets properties: cvr, name, email, phones, street, 
     * additionalInfo, zipCode, city - if they are not null
     * @param number
     * @return json String
     */
    @GET
    @Path("cvr/{number}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJSONCompanyByCvr(@PathParam("number") String number){
        Company c = facade.getCompanyByCvr(number);
        JsonObject job = new JsonObject();
        if(c.getCvr()!=null){job.addProperty("cvr", c.getCvr());}
        if(c.getName()!=null){job.addProperty("name", c.getName());}
        if(c.getEmail()!=null){job.addProperty("email", c.getEmail());}
        job.addProperty("phones", gson.toJson(facade.getPhonesByCompany(c))); 
        if(facade.getAdressByCompany(c)!=null){
            Address adr = facade.getAdressByCompany(c);
            if(adr.getStreet()!=null){job.addProperty("street", adr.getStreet());}
            if(adr.getAdditionalInfo()!=null){job.addProperty("additionalInfo", adr.getAdditionalInfo());}
        }
        if(facade.getCityInfoByCompany(c)!=null){
            CityInfoNew cti = facade.getCityInfoByCompany(c);
            if(cti.getZipCode()!=null){job.addProperty("zipCode", cti.getZipCode());}
            if(cti.getCity()!=null){job.addProperty("city", cti.getCity());}
        }
        return gson.toJson(job);
    }
    
    /**
     * Gets a collection Company objects from the facade by the 
     * given employee (amount) number.
     * sets properties: cvr, name, email, phones, street, 
     * additionalInfo, zipCode, city - if they are not null
     * @param number
     * @return json String
     */
    @GET
    @Path("employees/{number}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getJSONCompaniesByEmpNumber(@PathParam("number") int number){
        Collection<Company> companies = facade.getCompaniesByEmpAmount(number);
        List<JsonObject> jList = new ArrayList();
        for (Company c : companies) {
            JsonObject job = new JsonObject();
            if(c.getId()!=null){job.addProperty("id", c.getId());}
            if(c.getName()!=null){job.addProperty("name", c.getName());}
            if(c.getEmail()!=null){job.addProperty("email", c.getEmail());}
            job.addProperty("phones", gson.toJson(facade.getPhonesByCompany(c))); //MISSING - beware, that we might run in to s stackoverflow here
            jList.add(job);
        }
        return gson.toJson(jList);
    }
    
    /**
     * Takes a json string and creates a Company object c from the content
     * creates a newPerson object from the facade that takes in c
     * newPerson now has an id and is returned.
     * @param content
     * @return json String
     */
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String addCompany(String content){
        Company c = gson.fromJson(content, Company.class);
        Company newCompany = facade.addCompany(c);
        return gson.toJson(newCompany);
    }
    
}
