package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import enitity.Address;
import enitity.CityInfoNew;
import enitity.Company;
import facade.Facade;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.ejb.Stateless;
import javax.json.JsonString;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
public class CompanyFacadeREST {

    static Facade facade = new Facade(Persistence.createEntityManagerFactory("remote"));
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @PersistenceContext(unitName = "company")
    private EntityManager em;

    public CompanyFacadeREST() {

    }

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

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_PLAIN})
    public String addCompany(String content) {
        Company c = gson.fromJson(content, Company.class);
        Company newCompany = facade.addCompany(c);
        try {
            return something(newCompany);
        } catch (Exception e) {
            System.out.println("Lol");
            throw e;
        }

    }

    public String something(Company c) {
        JsonObject job = new JsonObject();
        if (c.getId() != null) {
            job.addProperty("id", c.getId());
        }
        if (c.getCvr() != null) {
            job.addProperty("cvr", c.getCvr());
        }
        if (c.getName() != null) {
            job.addProperty("name", c.getName());
        }
        if (c.getEmail() != null) {
            job.addProperty("email", c.getEmail());
        }
        return gson.toJson(job);
    }

}
