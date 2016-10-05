package rest;

import enitity.CityInfoNew;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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
@Path("cityinfonew")
public class CityInfoNewFacadeREST {

    @PersistenceContext(unitName = "remote")
    private EntityManager em;

    public CityInfoNewFacadeREST() {
    }

}
