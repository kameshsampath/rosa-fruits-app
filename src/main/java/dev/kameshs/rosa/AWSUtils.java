package dev.kameshs.rosa;

import io.quarkus.arc.profile.IfBuildProfile;
import java.net.URI;
import java.util.logging.Logger;
import javax.enterprise.inject.Default;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.auth.credentials.WebIdentityTokenFileCredentialsProvider;
import software.amazon.awssdk.http.SdkHttpClient;
import software.amazon.awssdk.http.apache.ApacheSdkHttpService;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Component
public class AWSUtils {

  private static final Logger LOGGER = Logger.getLogger(
    AWSUtils.class.getName());

  @ConfigProperty(name = "rosa-demos.dynamodb.endpoint-override", defaultValue = "http://localhost:8000")
  String localEndPointURI;

  @IfBuildProfile("prod")
  @Bean
  @Default
  public DynamoDbClient dynamoDbClient() {
    LOGGER.info("Building DynamoDbClient with WebIdentityProvider");
    SdkHttpClient httpClient = new ApacheSdkHttpService().createHttpClientBuilder()
                                                         .build();

    WebIdentityTokenFileCredentialsProvider credentialsProvider =
      WebIdentityTokenFileCredentialsProvider.builder()
                                             .build();
    return DynamoDbClient.builder()
                         .httpClient(httpClient)
                         .credentialsProvider(credentialsProvider)
                         .build();
  }

  @IfBuildProfile("dev")
  @Bean
  @Default
  public DynamoDbClient devDynamoDbClient() {
    LOGGER.info(
      "Building DynamoDbClient with Static Credentials:" + localEndPointURI);
    SdkHttpClient httpClient = new ApacheSdkHttpService().createHttpClientBuilder()
                                                         .build();

    AwsBasicCredentials credentials = AwsBasicCredentials.create("test-key",
      "test-secret");

    StaticCredentialsProvider credentialsProvider =
      StaticCredentialsProvider.create(credentials);

    return DynamoDbClient.builder()
                         .endpointOverride(URI.create(localEndPointURI))
                         .httpClient(httpClient)
                         .credentialsProvider(
                           credentialsProvider)
                         .build();
  }
}
