package Models;
public class Customer {
  private String name;
  private String email;
  private String address;
  private double balance;
  private Cart cart;

  public Customer(String name, String email, String address, double balance) {
    this.name = name;
    this.email = email;
    this.address = address;
    if (balance < 0) {
      throw new IllegalArgumentException("Balance cannot be negative");
    }
    this.balance = balance;
    this.cart = new Cart();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public double getBalance() {
    return balance;
  }

  public void setBalance(double balance) {
    if (balance < 0) {
      throw new IllegalArgumentException("Balance cannot be negative");
    }
    this.balance = balance;
  }

  public Cart getCart() {
    return cart;
  }

  public void addToCart(Product product, long quantity) {
    try {
      cart.add(product, quantity);
    } catch (IllegalArgumentException e) {
      System.err.println("Error adding product to cart: " + e.getMessage());
      return;
    }
  }
}
