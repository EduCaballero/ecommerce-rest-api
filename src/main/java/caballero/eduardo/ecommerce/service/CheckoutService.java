package caballero.eduardo.ecommerce.service;

import caballero.eduardo.ecommerce.dto.Purchase;
import caballero.eduardo.ecommerce.dto.PurchaseResponse;

public interface CheckoutService {
    PurchaseResponse placeOrder(Purchase purchase);
}
