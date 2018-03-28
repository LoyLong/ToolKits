package org.loy.jdk8.beans;

public class Shop {
    public String name;
    public Integer distance;
    public Integer sales;
    public Integer priceLevel;

    public Shop(String name, int distance, int sales, int priceLevel) {
        this.name = name;
        this.distance = distance;
        this.sales = sales;
        this.priceLevel = priceLevel;
    }
}
