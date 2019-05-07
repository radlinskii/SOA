package application;

import filter.KeyGenerator;
import io.jsonwebtoken.Jwts;

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
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

@Path("auth")
@Transactional
public class AuthResource {
    private String HARDCODED_LOGIN = "admin";
    private String HARDCODED_PASSWORD= "admin";

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
    public Response authenticateUser(@FormParam("login") String login, @FormParam("password") String password) {

        try {

            System.out.println("#### login : " + login);
            System.out.println("#### password : " + password);

            // Authenticate the user using the credentials provided
            authenticate(login, password);

            System.out.println("authenticated");

            // Issue a token for the user
            String token = issueToken(login);

            System.out.println("token: " + token);

            // Return the token on the response
            return Response.ok().header(AUTHORIZATION, "Bearer " + token).build();

        } catch (Exception e) {
            return Response.status(BAD_REQUEST).build();
        }
    }

    private void authenticate(String login, String password) throws SecurityException {


        if (!login.equals(HARDCODED_LOGIN) || !password.equals(HARDCODED_PASSWORD))
            throw new SecurityException("Invalid login or password");
    }

    private String issueToken(String login) {
        System.out.println("### issuing token");

        Key key = keyGenerator.generateKey();

        try {
        return Jwts.builder()
                .setSubject(login)
                .signWith(key)
                .compact();

        } catch (Exception e) {
            System.out.println("ee:   " + e.getMessage());
            return "ERROR";
        }
    }
}
