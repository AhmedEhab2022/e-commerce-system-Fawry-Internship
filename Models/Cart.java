package Models;
import java.util.ArrayList;
import java.util.List;

import Interfaces.IExpirable;

public class Cart {
  private List<Product> products;

  public Cart() {
    products = new ArrayList<>();
  }

  public void add(Product product, long quantity) {
    if (product == null) {
      throw new IllegalArgumentException("Product cannot be null");
    }
    if (quantity <= 0) {
      throw new IllegalArgumentException("Quantity must be greater than zero.");
    }
    if (product.getQuantity() < quantity) {
      throw new IllegalArgumentException("Not enough stock for product: " + product.getName());
    }
    if (product instanceof IExpirable && ((IExpirable)product).isExpired()) {
      throw new IllegalArgumentException("Cannot add expired product: " + product.getName());
    }
    product.setQuantity(product.getQuantity() - quantity);
    Product clonedProduct = product.clone();
    clonedProduct.setQuantity(quantity);
    products.add(clonedProduct);
  }

  public List<Product> getProducts() {
    return products;
  }

  public boolean isEmpty() {
    return products.isEmpty();
  }
}
