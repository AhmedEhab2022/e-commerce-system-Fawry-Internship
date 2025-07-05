package Models;
import java.util.Date;

import Interfaces.IExpirable;
import Interfaces.IShippable;

public class ExpirableShippableProduct extends Product implements IExpirable, IShippable {
  private Date expirationDate;
  private double shippingCost;
  private double weight;

  public ExpirableShippableProduct(String name, double price, long quantity, Date expirationDate, double shippingCost, double weight) {
    super(name, price, quantity);
    this.expirationDate = expirationDate;
    this.shippingCost = shippingCost;
    this.weight = weight;
  }

  @Override
  public ExpirableShippableProduct clone() {
    return new ExpirableShippableProduct(this.getName(), this.getPrice(), this.getQuantity(), this.expirationDate, this.shippingCost, this.weight);
  }

  @Override
  public Date getExpirationDate() {
    return expirationDate;
  }
  @Override
  public void setExpirationDate(Date expirationDate) {
    this.expirationDate = expirationDate;
  }
  @Override
  public boolean isExpired() {
    return new Date().after(expirationDate);
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
