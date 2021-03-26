package dev.kameshs.rosa;

import io.quarkus.runtime.annotations.RegisterForReflection;
import java.util.Map;
import java.util.Objects;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

@RegisterForReflection
public class Fruit {

  private String name;
  private String season;

  public Fruit() {
  }

  public static Fruit from(Map<String, AttributeValue> item) {
    Fruit fruit = new Fruit();
    if (item != null && !item.isEmpty()) {
      fruit.setName(item.get(AbstractService.FRUIT_NAME_COL)
                        .s());
      fruit.setSeason(item.get(AbstractService.FRUIT_SEASON)
                          .s());
    }
    return fruit;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSeason() {
    return season;
  }

  public void setSeason(String season) {
    this.season = season;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof Fruit)) {
      return false;
    }

    Fruit other = (Fruit) obj;

    return Objects.equals(other.name, this.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.name);
  }

}
