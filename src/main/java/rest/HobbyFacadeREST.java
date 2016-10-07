package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import enitity.Address;
import enitity.CityInfoNew;
import enitity.Company;
import enitity.Hobby;
import facade.Facade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import static rest.CompanyFacadeREST.facade;

/**
 *
 * @author rasmus
 */
@Stateless
@Path("hobby")
public class HobbyFacadeREST {
    
    static Facade facade = new Facade(Persistence.createEntityManagerFactory("remote"));
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @PersistenceContext(unitName = "remote")
    private EntityManager em;

    public HobbyFacadeREST() {
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String addHobby(String content){
        Hobby h = gson.fromJson(content, Hobby.class);
        Hobby newHobby = facade.addHobby(h);
        return gson.toJson(newHobby);
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJSONHobbies() {
        List<Hobby> hobbyList = facade.getHobbies();
        List<JsonObject> jObList = new ArrayList();
        for (Hobby h : hobbyList) {
            JsonObject jOb = new JsonObject();
            if(h.getId() != null){jOb.addProperty("id", h.getId());}
            if(h.getName()!=null){jOb.addProperty("name", h.getName());}
            if(h.getDescription()!=null){jOb.addProperty("description", h.getDescription());}
            jObList.add(jOb);
        }
        return gson.toJson(jObList);
    }

}
