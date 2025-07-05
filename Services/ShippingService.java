package Services;
import java.util.List;

import Interfaces.IShippable;
import Models.Product;

public class ShippingService {
  private List<Product> shippableProducts;

  public ShippingService(List<Product> shippableProducts) {
    this.shippableProducts = shippableProducts;
  }

  public void printShipmentDetails() {
    System.out.println("** Shipment notice **");
    double totalWeight = 0.0;
    for (Product product : shippableProducts) {
      System.out.printf("%dx %s: %.2fg\n", product.getQuantity(), product.getName(), ((IShippable)product).getWeight() * product.getQuantity());
      totalWeight += ((IShippable)product).getWeight() * product.getQuantity();
    }
    String out = "Total package weight: ";
    if (totalWeight >= 1000) {
      totalWeight /= 1000;
      System.out.println(out + totalWeight + "kg");
    } else {
      System.out.println(out + totalWeight + "g");
    }
  }

  public double calculateShipmentFees() {
    double shipmentFees = 0.0;
    for (Product shippableProduct : shippableProducts) {
      // assuming shipping cost is per unit
      shipmentFees +=  ((IShippable)shippableProduct).getShippingCost() * shippableProduct.getQuantity();
    }
    return shipmentFees;
  }
}
