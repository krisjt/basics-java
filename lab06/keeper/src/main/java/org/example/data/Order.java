package org.example.data;

public class Order {

    public Customer customer;
    public String goods;

    public Order() {
    }

    public Order(Customer customer, String goods) {
        this.customer = customer;
        this.goods = goods;
    }

    public String getGoods() {
        return goods;
    }

    public Customer getIdc() {
        return customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "goods='" + goods + '\'' +
                ", customer=" + customer +
                '}';
    }
}
