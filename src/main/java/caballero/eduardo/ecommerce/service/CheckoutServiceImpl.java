package caballero.eduardo.ecommerce.service;

import caballero.eduardo.ecommerce.dao.CustomerRepository;
import caballero.eduardo.ecommerce.dto.Purchase;
import caballero.eduardo.ecommerce.dto.PurchaseResponse;
import caballero.eduardo.ecommerce.entity.Customer;
import caballero.eduardo.ecommerce.entity.Order;
import caballero.eduardo.ecommerce.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService{

    private CustomerRepository customerRepository;

    public CheckoutServiceImpl(CustomerRepository customerRepository){
        this.customerRepository=customerRepository;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {
        //info order from dto
        Order order = purchase.getOrder();
        //generate tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        //fill order with orderItems
        Set<OrderItem> orderItems = purchase.getOrderItems();
        //orderItems.forEach(item -> order.add(item));
        orderItems.forEach(order::add);

        //fill order with both address
        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        // populate customer with order
        Customer customer = purchase.getCustomer();

        // check if this is an existing customer
        /*String theEmail = customer.getEmail();

        Customer customerFromDB = customerRepository.findByEmail(theEmail);

        if (customerFromDB != null) {
            // We found them ... let's assign them accordingly
            customer = customerFromDB;
        }*/
        customer.add(order);
        customerRepository.save(customer);
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {
        //UUID 4 no dupes
        return UUID.randomUUID().toString();
    }
}
