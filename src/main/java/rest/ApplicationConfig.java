package rest;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author rasmus
 */
@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(errorhandling.NotFoundExceptionMapper.class);
        resources.add(errorhandling.PersonNotFoundExceptionMapper.class);
        resources.add(errorhandling.RuntimeExceptionMapper.class);
        resources.add(rest.AddressFacadeREST.class);
        resources.add(rest.CityInfoNewFacadeREST.class);
        resources.add(rest.CompanyFacadeREST.class);
        resources.add(rest.HobbyFacadeREST.class);
        resources.add(rest.InfoEntityFacadeREST.class);
        resources.add(rest.PersonFacadeREST.class);
        resources.add(rest.PhoneFacadeREST.class);
    }
    
}
