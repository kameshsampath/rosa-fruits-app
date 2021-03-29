package dev.kameshs.rosa;

import java.util.List;
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@ApplicationScoped
public class FruitSyncService extends AbstractService {

  @Inject
  DynamoDbClient dynamoDB;

  public List<Fruit> findAll() throws Exception {
    return dynamoDB.scanPaginator(scanRequest())
                   .items()
                   .stream()
                   .map(Fruit::from)
                   .collect(Collectors.toList());
  }

  public List<Fruit> add(Fruit fruit) throws Exception {
    dynamoDB.putItem(putRequest(fruit));
    return findAll();
  }

  public Fruit get(String name) throws Exception {
    return Fruit.from(dynamoDB.getItem(getRequest(name))
                              .item());
  }

  public void delete(String name) throws Exception {
    dynamoDB.deleteItem(deleteRequest(name));
  }
}
