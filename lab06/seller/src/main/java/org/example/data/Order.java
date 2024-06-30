package org.example.data;

import com.google.gson.annotations.Expose;

public class Order {
    @Expose
    public Customer customer;
    @Expose
    public String product;

    private String way;

    public Order() {
    }

    public Order(Customer customer, String product) {
        this.customer = customer;
        this.product = product;
    }

    public Order(Customer customer, String product, String way) {
        this.customer = customer;
        this.product = product;
        this.way = way;
    }

    public String getWay() {
        return way;
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
