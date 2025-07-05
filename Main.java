import java.util.Date;

import Models.Customer;
import Models.ExpirableProduct;
import Models.ExpirableShippableProduct;
import Models.Product;
import Models.ShippableProduct;
import Services.CheckoutService;

public class Main {

  public static void main(String[] args) {
    // create products of different types
    Product regularProduct = new Product("Regular Product", 10.0, 100);
    ExpirableProduct expirableProduct = new ExpirableProduct("Expirable Product", 15.0, 50, new Date(System.currentTimeMillis() + 86400000)); // expires in 1 day
    ExpirableProduct expiredProduct = new ExpirableProduct("Expired Product", 20.0, 30, new Date(System.currentTimeMillis() - 86400000)); // expired yesterday
    ShippableProduct shippableProduct = new ShippableProduct("Shippable Product", 50.0, 20, 2.0, 500.0); // weight 500g
    ExpirableShippableProduct expirableShippableProduct = new ExpirableShippableProduct("Expirable Shippable Product", 25.0, 10, new Date(System.currentTimeMillis() + 86400000), 3.0, 1000.0); // expires in 1 day, weight 1000g

    // create Customer
    Customer customer = new Customer("John Doe", "J.example@gmail.com", "address", 200.0);
    customer.addToCart(regularProduct, 2);
    customer.addToCart(expirableProduct, 1);
    
    // print error for adding expired product
    customer.addToCart(expiredProduct, 1); // prints error message
    System.err.println("-------------------------------------------------");

    // add quantities more than available stock
    customer.addToCart(shippableProduct, 25); // prints error message
    System.err.println("-------------------------------------------------");
    

    // checkout with valid Customer without shippable products
    CheckoutService.checkout(customer);
    System.out.println("-------------------------------------------------");

    // Checkout with valid Customer with shippable products
    customer.addToCart(shippableProduct, 1);
    customer.addToCart(expirableShippableProduct, 1);
    CheckoutService.checkout(customer);
    System.out.println("-------------------------------------------------");


    // checkout with invalid Customer
    try {
      CheckoutService.checkout(null);
    } catch (IllegalArgumentException e) {
      System.out.println("Error: " + e.getMessage());
    }
    System.out.println("-------------------------------------------------");

    Customer customer2 = new Customer("Jane Smith", "Ja.example.com", "address2", 10);
    // Checkout with empty cart
    try {
      CheckoutService.checkout(customer2);
    } catch (IllegalStateException e) {
      System.out.println("Error: " + e.getMessage());
    }
    System.out.println("-------------------------------------------------");
    
    // Insufficient balance for checkout
    customer2.addToCart(shippableProduct, 1);
    customer2.addToCart(expirableShippableProduct, 1);
    CheckoutService.checkout(customer2);
  }
}
