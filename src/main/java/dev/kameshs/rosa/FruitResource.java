package dev.kameshs.rosa;

import java.net.URI;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FruitResource {

  @Inject
  FruitSyncService service;

  @GET
  @Path("/fruits")
  public Response getAll() {
    return Response.ok(service.findAll())
                   .build();
  }

  @GET
  @Path("/fruit/{name}")
  public Response getSingle(@PathParam("name") String name) {
    return Response.ok(service.get(name))
                   .build();
  }

  @POST
  @Path("/fruit")
  public Response addFruit(Fruit fruit) {
    service.add(fruit);
    return Response.created(URI.create("/api/fruit/" + fruit.getName()))
                   .build();
  }
}
