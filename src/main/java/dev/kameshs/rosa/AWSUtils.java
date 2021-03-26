package dev.kameshs.rosa;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import software.amazon.awssdk.auth.credentials.WebIdentityTokenFileCredentialsProvider;
import software.amazon.awssdk.http.urlconnection.UrlConnectionHttpClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@ApplicationScoped
public class AWSUtils {

  @Named("WebIdProviderClient")
  public DynamoDbClient dynamoDbClient() {
    WebIdentityTokenFileCredentialsProvider credentialsProvider =
      WebIdentityTokenFileCredentialsProvider.builder()
                                             .build();
    return DynamoDbClient.builder()
                         .httpClient(UrlConnectionHttpClient.create())
                         .credentialsProvider(credentialsProvider)
                         .build();
  }
}
