package dev.kameshs.rosa;

import java.net.URI;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import software.amazon.awssdk.awscore.exception.AwsErrorDetails;
import software.amazon.awssdk.http.SdkHttpResponse;
import software.amazon.awssdk.services.sts.model.StsException;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FruitResource {

  private static final Logger LOGGER = Logger.getLogger(
    FruitResource.class.getName());

  @Inject
  FruitSyncService service;

  @GET
  @Path("/fruits")
  public Response getAll() {
    try {
      LOGGER.log(Level.INFO, "Getting all fruits");
      return Response.ok(service.findAll())
                     .build();
    } catch (StsException e) {
      return stsErrorResponse(e, "Error Getting Fruits");
    } catch (Exception e) {
      LOGGER.log(Level.SEVERE, "Error Getting Fruits", e);
      return Response.status(Status.INTERNAL_SERVER_ERROR)
                     .build();
    }
  }


  @GET
  @Path("/fruit/{name}")
  public Response getSingle(@PathParam("name") String name) {
    try {
      LOGGER.log(Level.INFO, "Getting fruit by name {0}", name);
      return Response.ok(service.get(name))
                     .build();
    } catch (StsException e) {
      return stsErrorResponse(e, "Error Getting Fruit");
    } catch (Exception e) {
      LOGGER.log(Level.SEVERE, "Error Getting Fruit", e);
      return Response.status(Status.INTERNAL_SERVER_ERROR)
                     .build();
    }

  }

  @POST
  @Path("/fruit")
  public Response addFruit(Fruit fruit) {
    try {
      LOGGER.log(Level.INFO, "Saving fruit {0}", fruit);
      service.add(fruit);
      return Response.created(URI.create("/api/fruit/" + fruit.getName()))
                     .build();
    } catch (StsException e) {
      return stsErrorResponse(e, "Error Adding Fruit");
    } catch (Exception e) {
      LOGGER.log(Level.SEVERE, "Error Adding Fruit", e);
      return Response.status(Status.INTERNAL_SERVER_ERROR)
                     .build();
    }
  }

  @DELETE
  @Path("/fruit/{name}")
  public Response delete(@PathParam("name") String fruitName) {
    try {
      LOGGER.log(Level.INFO, "Deleting fruit with name {0}", fruitName);
      service.delete(fruitName);
      return Response.noContent()
                     .build();
    } catch (StsException e) {
      return stsErrorResponse(e, "Error Adding Fruit");
    } catch (Exception e) {
      LOGGER.log(Level.SEVERE, "Error Adding Fruit", e);
      return Response.status(Status.INTERNAL_SERVER_ERROR)
                     .build();
    }
  }

  private Response stsErrorResponse(StsException e, String s) {
    AwsErrorDetails awsErrorDetails = e.awsErrorDetails();
    LOGGER.log(Level.SEVERE, awsErrorDetails.errorMessage(), e);
    SdkHttpResponse httpResponse = awsErrorDetails
      .sdkHttpResponse();
    return Response.status(httpResponse.statusCode(),
      awsErrorDetails.errorMessage())
                   .build();
  }
}
