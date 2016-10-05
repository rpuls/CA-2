package errorhandling;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.servlet.ServletContext;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.StatusType;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GeneralExceptionMapper implements ExceptionMapper<Exception> {

    static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    @Context
    ServletContext context;

    @Override
    public Response toResponse(Exception ex) {

        ErrorMessage err;
        int statusCode = 500;
        boolean isDebug = context.getInitParameter("debug").equals("true");
        String msg = ex.getMessage();
        if (ex instanceof WebApplicationException) {

            Response res = ((WebApplicationException) ex).getResponse();
            if (res != null) {
                StatusType type = res.getStatusInfo();
                statusCode = type.getStatusCode();
                msg = type.getReasonPhrase();
            }
        }
        if (isDebug) {
           err = new ErrorMessage(ex, statusCode, isDebug);
        } else {
            
            err = new ErrorMessage(ex.getClass().getName(), statusCode);
        }
        return Response.status(statusCode)
                .entity(gson.toJson(err))
                .type(MediaType.APPLICATION_JSON).
                build();
    }
}
