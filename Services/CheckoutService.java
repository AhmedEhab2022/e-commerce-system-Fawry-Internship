package Services;
import java.util.ArrayList;
import java.util.List;

import Interfaces.IShippable;
import Models.Cart;
import Models.Customer;
import Models.Product;

public class CheckoutService {

  public static void checkout(Customer customer) {
    if (customer == null) {
      throw new IllegalArgumentException("Customer cannot be null.");
    }
    Cart cart = customer.getCart();
    if (cart.isEmpty()) {
      throw new IllegalStateException("Cart is empty. Cannot proceed to checkout.");
    }

    List<Product> shippableProducts = new ArrayList<>();

    for (Product product : cart.getProducts()) {
      if (product instanceof IShippable) {
        shippableProducts.add(product);
      }
    }


    double shipmentFees = 0.0;
    ShippingService shippingService = new ShippingService(shippableProducts);
    if (!shippableProducts.isEmpty()) {
      shipmentFees = shippingService.calculateShipmentFees();
    }

    double subTotal = cart.getProducts().stream()
        .mapToDouble(Product::getPrice)
        .sum();

    double amount = subTotal + shipmentFees;

    double newBalance = customer.getBalance() - amount;
    try {
      customer.setBalance(newBalance);
    } catch (IllegalArgumentException e) {
      System.err.println("Insufficient balance for checkout, the total amount: " + amount + ". Your current balance is: " + customer.getBalance());
      return;
    }

    if (!shippableProducts.isEmpty()) {
      shippingService.printShipmentDetails();
      System.out.println();
    }

    System.out.println("** Checkout receipt **");
    System.out.println("Customer: " + customer.getName());

    for (Product product : cart.getProducts()) {
      System.out.printf("%dx %s: %.2f%n", product.getQuantity(), product.getName(), product.getPrice());
    }
    System.out.println("----------------------------------");
    System.out.println("Subtotal: " + subTotal);
    if (shipmentFees > 0) {
      System.out.println("Shipping: " + shipmentFees);
    }
    System.out.println("Amount: " + amount);
    System.out.println("Balance after Payment: " + newBalance);
  }
}