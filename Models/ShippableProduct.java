package Models;
import Interfaces.IShippable;


public class ShippableProduct extends Product implements IShippable {
  // shipping cost per unit
  private double shippingCost;
  // weight per unit in grams
  private double weight;

  public ShippableProduct(String name, double price, long quantity, double shippingCost, double weight) {
    super(name, price, quantity);
    this.shippingCost = shippingCost;
    this.weight = weight;
  }
  @Override
  public ShippableProduct clone() {
    return new ShippableProduct(this.getName(), this.getPrice(), this.getQuantity(), this.shippingCost, this.weight);
  }

  @Override
  public double getShippingCost() {
    return shippingCost;
  }
  @Override
  public void setShippingCost(double shippingCost) {
    this.shippingCost = shippingCost;
  }
  @Override
  public double getWeight() {
    return weight;
  }
  @Override
  public void setWeight(double weight) {
    this.weight = weight;
  }
}
