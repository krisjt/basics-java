package org.example.data;

public class Order {
    public Customer customer;
    public String product;

    public Order(Customer customer, String product) {
        this.customer = customer;
        this.product = product;
    }

    public Order() {
    }

    @Override
    public String toString() {
        return "Order{" +
                "customer=" + customer +
                ", product='" + product + '\'' +
                '}';
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setIdc(Customer customer) {
        this.customer = customer;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
