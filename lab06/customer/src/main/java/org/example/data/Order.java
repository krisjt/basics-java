package org.example.data;

public class Order {
    public CustomerModel customerModel;
    public String product;

    public Order(CustomerModel customerModel, String product) {
        this.customerModel = customerModel;
        this.product = product;
    }

    public Order() {
    }


    public CustomerModel getCustomer() {
        return customerModel;
    }

    public void setIdc(CustomerModel customerModel) {
        this.customerModel = customerModel;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
