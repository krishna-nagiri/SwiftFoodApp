    package com.infy.models;

    import java.time.LocalDateTime;
    import java.util.List;    
    public class Order {

        private String orderId;
        private String customerId;
        private String customerName;
        private String restaurantId;
        private String deliveryPartnerId;
        private List<Food> orderedFoods;
        private double totalPrice;
        private String paymentMethod;
        private String status;
        private LocalDateTime orderTime;
        private String deliveryAddress;
        private String failureReason;
        private int customerRating;
        private LocalDateTime deliveryTime; 

        public Order(String orderId, String customerId,String customerName, String restaurantId, String deliveryPartnerId,List<Food> orderedFoods, String paymentMethod, String deliveryAddress) {
            this.orderId = orderId;
            this.customerId = customerId;
            this.customerName = customerName;
            this.restaurantId = restaurantId;
            this.deliveryPartnerId = deliveryPartnerId;
            this.paymentMethod = paymentMethod;
            this.orderedFoods = orderedFoods;
            this.totalPrice = calculateTotalPrice(orderedFoods);
            this.status = "Placed";
            this.orderTime = LocalDateTime.now();
            this.deliveryAddress = deliveryAddress;
            this.customerRating = -1;
        }

        public double calculateTotalPrice(List<Food> foods){
            double total = 0;
            for (Food f : foods){
                total += f.getUnitPrice();
            }
            return total - (total *0.05);
        }                                   // for simple order calculations

        public double calculateTotalAmount() {
            double total = 0;
            for (Food f : this.orderedFoods) {
                total += f.getUnitPrice();
            }
            return total;   // replace if need more than 5% discount
        }
                // Setter methods.
        public void setCustomerId(String customerId){
            this.customerId = customerId;
        }
        
        public void setRestaurantId(String restaurantId){
            this.restaurantId = restaurantId;
        }

        public void setDeliveryPartnerId(String deliveryPartnerId){
            this.deliveryPartnerId = deliveryPartnerId;
        }

        public void setOrderedFoods(List<Food> orderedFoods){
            this.orderedFoods = orderedFoods;
        }

        public void setPaymentMethod(String paymentMethod){
            this.paymentMethod = paymentMethod;
        }

        public void setDeliveryAddress(String deliveryAddress){
            this.deliveryAddress = deliveryAddress;
        }
        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }
        public void setStatus(String status) {
            this.status = status;
        }
        public void setFailureReason(String reason) {
            this.failureReason = reason;
        }
        public void setCustomerRating(int rating) {
            this.customerRating = rating;
        }
        public void setDeliveryTime(LocalDateTime deliveryTime) {
            this.deliveryTime = deliveryTime;
        }
                //  Getter Methods 
        public String getCustomerId(){
            return this.customerId;
        }

        public String getRestaurantId(){
            return this.restaurantId;
        }
        public String getCustomerName() {
            return customerName;
        }

        public String getDeliveryPartnerId(){
            return this.deliveryPartnerId;
        }
        public LocalDateTime getDeliveryTime() {
            return deliveryTime;
        }
        public List<Food> getOrderedFoods(){
            return this.orderedFoods;
        }

        public String getPaymentMethod(){
            return this.paymentMethod;
        }

        public String getDeliveryAddress(){
            return this.deliveryAddress;
        }

        public String getOrderId() {
        return this.orderId;
        }

        public double getTotalPrice() {
            return this.totalPrice;
        }

        public String getStatus() {
            return this.status;
        }

        public LocalDateTime getOrderTime() {
            return this.orderTime;
        }
        public String getFailureReason() {
            return failureReason;
        }
        public int getCustomerRating() {
            return customerRating;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Order ID: ").append(orderId)
            .append("\nCustomer ID: ").append(customerId)
            .append("\nCustomer Name : ").append(customerName)
            .append("\nRestaurant ID: ").append(restaurantId)
            .append("\nDelivery Partner ID: ").append(deliveryPartnerId != null ? deliveryPartnerId : "Not-Assigned")
            .append("\nDelivery Address: ").append(deliveryAddress)
            .append("\nPayment Method: ").append(paymentMethod)
            .append("\nOrder Status: ").append(status)
            .append("\nOrder Time: ").append(orderTime)
            .append("\nCustomer Rating: ").append(customerRating >= 0 ? customerRating + "/5" : "Not Rated")
            .append("\nOrdered Items:\n");
            for (Food f : orderedFoods) {
                sb.append(" ('-')-> ").append(f.getFoodName()).append(" Rs. ").append(f.getUnitPrice()).append("\n");
            }

            sb.append("Total Price (after 5% discount): ₹").append(totalPrice);

            if ("Delivered".equalsIgnoreCase(status) && deliveryTime != null) {
                sb.append("\nDelivery Time: ").append(deliveryTime);
            } else if ("Rejected".equalsIgnoreCase(status) && failureReason != null) {
                sb.append("\nReason for Rejection: ").append(failureReason);
            } else {
                sb.append("\nDelivery Time: Pending");
            }

            return sb.toString();
        }
  
    }