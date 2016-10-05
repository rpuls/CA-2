package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import enitity.Address;
import enitity.CityInfoNew;
import enitity.Company;
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
@Path("company")
public class CompanyFacadeREST extends AbstractFacade<Company> {
    
    static Facade facade = new Facade(Persistence.createEntityManagerFactory("remote"));
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @PersistenceContext(unitName = "company")
    private EntityManager em;

    public CompanyFacadeREST() {
        super(Company.class);
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

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Company entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Company entity) {
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
    public Company find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Company> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Company> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
