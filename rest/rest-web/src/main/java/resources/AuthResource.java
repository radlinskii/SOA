package resources;

import application.StudentContainer;
import filter.KeyGenerator;
import io.jsonwebtoken.Jwts;
import model.Student;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import java.security.Key;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;
import static javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

@Path("auth")
@Transactional
public class AuthResource {

    @Context
    private UriInfo uriInfo;


    @Inject
    private KeyGenerator keyGenerator;


    @Inject
    StudentContainer container;

    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(APPLICATION_FORM_URLENCODED)
    public Response authenticateUser(@FormParam("studentCardId") Integer studentCardId) {

        try {

            System.out.println("#### studentCardId : " + studentCardId);

            // Authenticate the user using the credentials provided
            authenticate(studentCardId);

            System.out.println("authenticated");

            // Issue a token for the user
            String token = issueToken(studentCardId.toString());

            System.out.println("token: " + token);

            // Return the token on the response
            return Response.ok().header(AUTHORIZATION, "Bearer " + token).build();

        } catch (Exception e) {
            return Response.status(UNAUTHORIZED).build();
        }
    }

    private void authenticate(Integer studentCardId) throws SecurityException {
        Student student = container.get(studentCardId);
        System.out.println(student);

        if (student == null)
            throw new SecurityException("Invalid studentCardId");
    }

    private String issueToken(String studentCardId) {
        System.out.println("### issuing token");

        Key key = keyGenerator.generateKey();

        try {
        return Jwts.builder()
                .setSubject(studentCardId)
                .signWith(key)
                .compact();

        } catch (Exception e) {
            System.out.println("ee:   " + e.getMessage());
            return "ERROR";
        }



    }
}
