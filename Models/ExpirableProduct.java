package Models;
import java.util.Date;

import Interfaces.IExpirable;

public class ExpirableProduct extends Product implements IExpirable {
  private Date expirationDate;

  public ExpirableProduct(String name, double price, long quantity, Date expirationDate) {
    super(name, price, quantity);
    this.expirationDate = expirationDate;
  }

  @Override
  public ExpirableProduct clone() {
    return new ExpirableProduct(this.getName(), this.getPrice(), this.getQuantity(), this.expirationDate);
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
}
