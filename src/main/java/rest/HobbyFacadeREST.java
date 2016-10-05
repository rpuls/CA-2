/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import enitity.Hobby;
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

    
}
