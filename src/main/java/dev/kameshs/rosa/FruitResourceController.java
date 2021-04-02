package dev.kameshs.rosa;

import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.awscore.exception.AwsErrorDetails;
import software.amazon.awssdk.http.SdkHttpResponse;
import software.amazon.awssdk.services.sts.model.StsException;

@SuppressWarnings("rawtypes")
@RestController
@RequestMapping("/api")
public class FruitResourceController {

  private static final Logger LOGGER = Logger.getLogger(
    FruitResourceController.class.getName());

  @Autowired
  FruitSyncService service;

  @GetMapping("/fruits")
  public ResponseEntity getAll() {
    try {
      LOGGER.log(Level.INFO, "Getting all fruits");
      return ResponseEntity.ok(service.findAll());
    } catch (StsException e) {
      return stsErrorResponse(e, "Error Getting Fruits");
    } catch (Exception e) {
      LOGGER.log(Level.SEVERE, "Error Getting Fruits", e);
      return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .build();
    }
  }

  @PostMapping("/fruit/{name}")
  public ResponseEntity getSingle(@PathVariable("name") String name) {
    try {
      LOGGER.log(Level.INFO, "Getting fruit by name {0}", name);
      return ResponseEntity.ok(service.get(name));
    } catch (StsException e) {
      return stsErrorResponse(e, "Error Getting Fruit");
    } catch (Exception e) {
      LOGGER.log(Level.SEVERE, "Error Getting Fruit", e);
      return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .build();
    }

  }

  @PostMapping("/fruit")
  public ResponseEntity addFruit(Fruit fruit) {
    try {
      LOGGER.log(Level.INFO, "Saving fruit {0}", fruit);
      service.add(fruit);
      return ResponseEntity
        .created(URI.create("/api/fruit/" + fruit.name))
        .build();
    } catch (StsException e) {
      return stsErrorResponse(e, "Error Adding Fruit");
    } catch (Exception e) {
      LOGGER.log(Level.SEVERE, "Error Adding Fruit", e);
      return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .build();
    }
  }

  @DeleteMapping("/fruit/{name}")
  public ResponseEntity delete(@PathVariable("name") String fruitName) {
    try {
      LOGGER.log(Level.INFO, "Deleting fruit with name {0}", fruitName);
      service.delete(fruitName);
      return ResponseEntity.noContent()
                           .build();
    } catch (StsException e) {
      return stsErrorResponse(e, "Error Adding Fruit");
    } catch (Exception e) {
      LOGGER.log(Level.SEVERE, "Error Adding Fruit", e);
      return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .build();
    }
  }

  private ResponseEntity stsErrorResponse(StsException e, String s) {
    AwsErrorDetails awsErrorDetails = e.awsErrorDetails();
    LOGGER.log(Level.SEVERE, awsErrorDetails.errorMessage(), e);
    SdkHttpResponse httpResponse = awsErrorDetails
      .sdkHttpResponse();
    return ResponseEntity.status(httpResponse.statusCode())
                         .body(awsErrorDetails.errorMessage());
  }
}
