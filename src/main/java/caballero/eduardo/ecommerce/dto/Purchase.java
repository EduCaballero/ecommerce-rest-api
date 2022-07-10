package caballero.eduardo.ecommerce.dto;

import caballero.eduardo.ecommerce.entity.Address;
import caballero.eduardo.ecommerce.entity.Customer;
import caballero.eduardo.ecommerce.entity.Order;
import caballero.eduardo.ecommerce.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {
    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;
}
