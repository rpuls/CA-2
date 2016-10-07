package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import enitity.CityInfoNew;
import facade.Facade;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import static rest.PersonFacadeREST.gson;

/**
 *
 * @author rasmus
 */
@Stateless
@Path("cityinfonew")
public class CityInfoNewFacadeREST {
    
    static Facade facade = PersonFacadeREST.facade;
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @PersistenceContext(unitName = "remote")
    private EntityManager em;

    public CityInfoNewFacadeREST() {
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String addPerson(String content){
        CityInfoNew ci = gson.fromJson(content, CityInfoNew.class);
        CityInfoNew newCityInfo = facade.addCityInfo(ci);
        return gson.toJson(newCityInfo);
    }
    
    @GET
    @Produces({MediaType.TEXT_PLAIN})
    public String getCityFromZipCode(@Context UriInfo info){
       String zip = info.getQueryParameters().getFirst("zip");
       String city= facade.getCityByZipCode(zip);
       return city;
    }

}
