import javax.ws.rs.*;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

public interface SimpleClient
{
    @GET
    @Produces(APPLICATION_JSON)
    String getBasic();

    @PUT
    @Path("basic")
    @Consumes("text/plain")
    void putBasic(String body);

    @GET
    @Path("queryParam")
    @Produces("text/plain")
    String getQueryParam(@QueryParam("param")String param);

    @GET
    @Path("matrixParam")
    @Produces("text/plain")
    String getMatrixParam(@MatrixParam("param")String param);

    @GET
    @Path("uriParam/{param}")
    @Produces("text/plain")
    int getUriParam(@PathParam("param")int param);
}