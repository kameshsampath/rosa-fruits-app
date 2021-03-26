package dev.kameshs.rosa;

import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import software.amazon.awssdk.auth.credentials.WebIdentityTokenFileCredentialsProvider;
import software.amazon.awssdk.http.SdkHttpClient;
import software.amazon.awssdk.http.apache.ApacheSdkHttpService;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@ApplicationScoped
public class AWSUtils {

  private static final Logger LOGGER = Logger.getLogger(
    AWSUtils.class.getName());

  @Named("WebIdProviderClient")
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
}
