package offres;


import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/admin")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AdminController {
	
	AdminService a = new AdminService();
public boolean isAdmin() {
		
		if (Accueil.mapUsers.containsKey("admin")==true) {
			return true;
		}
		return false;
	}

@PUT
@Path("/upd")
public Response updatePassword(@QueryParam("password") String password) {
    if (isAdmin()) {
        if (password != null && !password.isEmpty()) {
            a.UpdatePass(password);
            return Response.ok("Password updated successfully").build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Error: Password parameter is required").build();
        }
    } else {
        return Response.status(Response.Status.FORBIDDEN)
                .entity("Error: User is not authorized to perform this action").build();
    }
}
@POST
@Consumes(MediaType.APPLICATION_JSON)
@Path("/adduser")
public Response addUser(User user) {
    if (isAdmin()) {
        String result = a.addUser(user);
        return Response.ok(result).build();
    } else {
        return Response.status(Response.Status.UNAUTHORIZED)
                .entity("Error: User is not authorized to perform this action").build();
    }
}


	
	@GET
	@Path("all/users")
	public Response allUsers() {
        if (isAdmin()) {
            List<User> users = a.allUsers();
            return Response.ok(users).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED)
            .entity("Error: User is not authorized to perform this action").build();
        }
    }

	

	@GET
    @Path("/user")
    public Response getUser(@QueryParam("id") String userId) {
        if (isAdmin()) {
            User user = a.getUser(userId);

            if (user != null) {
                return Response.ok(user).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("User with ID " + userId + " not found").build();
            }
        } else {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Error: User is not authorized to access this resource").build();
        }
    }
	 @DELETE
	 @Path("/delete")
	    public Response deleteUser(@QueryParam("id") String userId) {
	        if (isAdmin()) {
	            String result = a.deleteUser(userId);
	            return Response.ok(result).build();
	        } else {
	            return Response.status(Response.Status.FORBIDDEN)
	                    .entity("Error: User is not authorized to perform this action").build();
	        }
	    }
}
