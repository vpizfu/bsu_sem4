package com.company.DAO;

/**
 * Represents "tour" table content.
 */
public class Tour {

    public enum type {
        allInclusive,
        sport,
    }

    private int id;
    private String location;
    private float price;
    private String startDate;
    private String endDate;
    private boolean isHot;
    private type type;

    public Tour(int id, String location, float price, String startDate, String endDate, boolean isHot, Tour.type type) {
        this.id = id;
        this.location = location;
        this.price = price;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isHot = isHot;
        this.type = type;
    }

    public Tour.type getType() {
        return type;
    }

    public void setType(Tour.type type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public boolean isHot() {
        return isHot;
    }

    public void setHot(boolean hot) {
        isHot = hot;
    }
}
