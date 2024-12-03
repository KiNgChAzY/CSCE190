package classhomeandlabs2024;
//chase McCracken

public class Order implements Comparable<Order> {
    // Instance variables
    private String customer;
    private String foodOrder;
    private int cookingTime;
    private int arrivalTime;
    private int cookingTimeLeft;

    // Constructors
    public Order() {
        this.customer = "none";
        this.foodOrder = "none";
        this.cookingTime = 1;
        this.arrivalTime = 0;
        this.cookingTimeLeft = 1;
    }

    public Order(String customer, String foodOrder, int cookingTime, int arrivalTime) {
        this();
        setCustomer(customer);
        setFoodOrder(foodOrder);
        setCookingTime(cookingTime);
        setArrivalTime(arrivalTime);
    }

    // Getters and setters
    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        if (customer != null && !customer.isEmpty()) {
            this.customer = customer;
        }
    }

    public String getFoodOrder() {
        return foodOrder;
    }

    public void setFoodOrder(String foodOrder) {
        if (foodOrder != null && !foodOrder.isEmpty()) {
            this.foodOrder = foodOrder;
        }
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        if (cookingTime > 0) {
            this.cookingTime = cookingTime;
            this.cookingTimeLeft = cookingTime;
        }
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        if (arrivalTime >= 0) {
            this.arrivalTime = arrivalTime;
        }
    }

    // Methods
    @Override
    public int compareTo(Order other) {
        return Integer.compare(this.cookingTime, other.cookingTime);
    }

    public void cookForOneMinute() {
        if (cookingTimeLeft > 0) {
            cookingTimeLeft--;
        }
    }

    public boolean isDone() {
        return cookingTimeLeft == 0;
    }

    @Override
    public String toString() {
        return "Customer: " + customer + ", Order: " + foodOrder + ", Cooking Time Left: " + cookingTimeLeft;
    }
}
