package ola.api;

import ola.api.model.UserModel;
import ola.ejb.User;
import ola.ejb.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Path("/user")
public class UserRS {

    @Inject
    private UserService userService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(UserModel model) {

        userService.save(model.getUsername(), model.getFirstName(), model.getLastName());
        return Response.created(URI.create("/user")).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get() {

        List<User> user = userService.getAll();
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {

            List<UserModel> result = user.stream().map(UserModel::parse).collect(Collectors.toList());
            return Response.ok(result, MediaType.APPLICATION_JSON_TYPE).build();
        }
    }
}
