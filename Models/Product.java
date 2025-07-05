package Models;
import Interfaces.ICloneable;

public class Product implements ICloneable {
  private String name;
  private double price;
  private long quantity;

  public Product(String name, double price, long quantity) {
    this.name = name;
    this.price = price;
    this.quantity = quantity;
  }

  @Override
  public Product clone() {
    return new Product(this.name, this.price, this.quantity);
  }

  public String getName() {
    return name;
  }
  public double getPrice() {
    return price;
  }
  public long getQuantity() {
    return quantity;
  }

  public void setName(String name) {
    this.name = name;
  }
  public void setPrice(double price) {
    this.price = price;
  }
  public void setQuantity(long quantity) {
    this.quantity = quantity;
  }
}
