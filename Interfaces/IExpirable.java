package Interfaces;
import java.util.Date;

public interface IExpirable {
  Date getExpirationDate();
  void setExpirationDate(Date expirationDate);
  boolean isExpired();
}
