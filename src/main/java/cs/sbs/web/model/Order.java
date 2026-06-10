package cs.sbs.web.model;

public class Order {
    private final int orderId;
    private final String customerName;
    private final String foodName;
    private final int quantity;

    public Order(int orderId, String customerName, String foodName, int quantity) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.foodName = foodName;
        this.quantity = quantity;
    }

    public int getOrderId() { return orderId; }
    public String getCustomerName() { return customerName; }
    public String getFoodName() { return foodName; }
    public int getQuantity() { return quantity; }
}