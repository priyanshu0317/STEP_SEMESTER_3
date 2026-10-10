package object_oriented_design.class_problems;

import java.util.ArrayList;
import java.util.List;

public class PaymentProcessingSystem {

    // Interface for polymorphic PaymentMethod abstraction
    public interface PaymentMethod {
        String getMethodName();
        boolean processPayment(double amount);
    }

    // Concrete CreditCardPayment implementation
    public static class CreditCardPayment implements PaymentMethod {
        private String cardNumber;

        public CreditCardPayment(String cardNumber) {
            this.cardNumber = cardNumber;
        }

        @Override
        public String getMethodName() {
            return "Credit Card";
        }

        @Override
        public boolean processPayment(double amount) {
            // Simulates successful authorization
            return true;
        }
    }

    // Concrete PayPalPayment implementation
    public static class PayPalPayment implements PaymentMethod {
        private String email;
        private boolean shouldSucceed;

        public PayPalPayment(String email, boolean shouldSucceed) {
            this.email = email;
            this.shouldSucceed = shouldSucceed;
        }

        @Override
        public String getMethodName() {
            return "PayPal";
        }

        @Override
        public boolean processPayment(double amount) {
            return shouldSucceed;
        }
    }

    // Concrete BankTransferPayment implementation
    public static class BankTransferPayment implements PaymentMethod {
        private String accountNumber;

        public BankTransferPayment(String accountNumber) {
            this.accountNumber = accountNumber;
        }

        @Override
        public String getMethodName() {
            return "Bank Transfer";
        }

        @Override
        public boolean processPayment(double amount) {
            return true;
        }
    }

    // Product entity
    public static class Product {
        private String id;
        private String name;
        private double price;

        public Product(String id, String name, double price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }
    }

    // OrderItem representing line items in an order
    public static class OrderItem {
        private Product product;
        private int quantity;

        public OrderItem(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        public Product getProduct() {
            return product;
        }

        public int getQuantity() {
            return quantity;
        }

        public double getSubtotal() {
            return product.getPrice() * quantity;
        }
    }

    // Order status enumeration
    public enum OrderStatus {
        PENDING,
        PAID
    }

    // Customer entity
    public static class Customer {
        private String id;
        private String name;

        public Customer(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    // Order entity managing items and payment status
    public static class Order {
        private String orderId;
        private Customer customer;
        private List<OrderItem> items;
        private OrderStatus status;

        public Order(String orderId, Customer customer) {
            this.orderId = orderId;
            this.customer = customer;
            this.items = new ArrayList<>();
            this.status = OrderStatus.PENDING;
        }

        public void addItem(Product product, int quantity) {
            this.items.add(new OrderItem(product, quantity));
        }

        public String getOrderId() {
            return orderId;
        }

        public Customer getCustomer() {
            return customer;
        }

        public List<OrderItem> getItems() {
            return items;
        }

        public OrderStatus getStatus() {
            return status;
        }

        public void setStatus(OrderStatus status) {
            this.status = status;
        }

        public boolean isEmpty() {
            return items.isEmpty();
        }

        public double getTotalAmount() {
            double total = 0;
            for (OrderItem item : items) {
                total += item.getSubtotal();
            }
            return total;
        }
    }

    // ShoppingService managing order creation and payment workflow
    public static class ShoppingService {
        public Order createOrder(String orderId, Customer customer) {
            Order order = new Order(orderId, customer);
            System.out.printf("Order created for %s.%n", customer.getName());
            return order;
        }

        public void processPayment(Order order, PaymentMethod paymentMethod) {
            if (order.isEmpty()) {
                System.out.println("Cannot process payment for an empty order.");
                return;
            }

            System.out.printf("Payment initiated via %s for %s.%n",
                    paymentMethod.getMethodName(), order.getOrderId());

            boolean success = paymentMethod.processPayment(order.getTotalAmount());
            if (success) {
                order.setStatus(OrderStatus.PAID);
                System.out.printf("Payment for %s successful. Order status: Paid.%n", order.getOrderId());
            } else {
                order.setStatus(OrderStatus.PENDING);
                System.out.printf("Payment for %s failed. Order status: Pending.%n", order.getOrderId());
            }
        }
    }

    public static void main(String[] args) {
        ShoppingService service = new ShoppingService();

        Customer customerX = new Customer("C1", "Customer X");
        Customer customerY = new Customer("C2", "Customer Y");
        Customer customerZ = new Customer("C3", "Customer Z");

        Product productA = new Product("P1", "Product A", 25.0);
        Product productB = new Product("P2", "Product B", 50.0);
        Product productC = new Product("P3", "Product C", 40.0);

        // Customer X creates an order with Product A (qty 2) and Product B (qty 1)
        Order orderX = service.createOrder("Order X", customerX);
        orderX.addItem(productA, 2);
        orderX.addItem(productB, 1);

        // Customer X attempts to pay for the order using Credit Card (successful)
        PaymentMethod creditCard = new CreditCardPayment("4111-2222-3333-4444");
        service.processPayment(orderX, creditCard);

        // Customer Y creates an empty order and attempts to pay for it
        Order orderY = new Order("Order Y", customerY);
        service.processPayment(orderY, creditCard);

        // Customer Z creates an order with Product C (qty 1)
        Order orderZ = service.createOrder("Order Z", customerZ);
        orderZ.addItem(productC, 1);

        // Customer Z attempts to pay using PayPal (payment fails)
        PaymentMethod payPalFail = new PayPalPayment("z@example.com", false);
        service.processPayment(orderZ, payPalFail);
    }
}
