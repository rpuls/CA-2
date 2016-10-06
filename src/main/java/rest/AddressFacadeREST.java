package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import enitity.Address;
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
import javax.ws.rs.core.MediaType;

/**
 *
 * @author rasmus
 */
@Stateless
@Path("address")
public class AddressFacadeREST {
    
    static Facade facade = new Facade(Persistence.createEntityManagerFactory("remote"));
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @PersistenceContext(unitName = "remote")
    private EntityManager em;

    public AddressFacadeREST() {
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public String addPerson(String content){
        Address adr = gson.fromJson(content, Address.class);
        Address newAdress = facade.addAddress(adr);
        return gson.toJson(newAdress);
    }

}
