package com.tre;

import java.util.Date;

public class Accommodation {
    private int Id;
    private Date DateEntered;
    private int Views;
    private String Type;
    private String Address;
    private float Distance;
    private String Bedroom;
    private String OwnerOccupied;
    private int FlatmatesNumber;
    private String FlatmatesInfo;
    private String LookingFor;
    private String Preferences;
    private String Facilities;
    private int Price;
    private String Frequency;
    private int Bills;
    private int Total;
    private String Ber;

    public Accommodation() {
        this.setId(0);
        this.setViews(0);
        this.setFlatmatesNumber(0);
        this.setPrice(0);
        this.setBills(0);
        this.setTotal(0);
        this.setDistance(0);
        this.setDateEntered(null);
        this.setType("");
        this.setAddress("");
        this.setBedroom("");
        this.setOwnerOccupied("");
        this.setFlatmatesInfo("");
        this.setLookingFor("");
        this.setPreferences("");
        this.setFacilities("");
        this.setFrequency("");
        this.setBer("");
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Date getDateEntered() {
        return DateEntered;
    }

    public void setDateEntered(Date date) {
        DateEntered = date;
    }

    public int getViews() {
        return Views;
    }

    public void setViews(int views) {
        Views = views;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public float getDistance() {
        return Distance;
    }

    public void setDistance(float distance) {
        Distance = distance;
    }

    public String getBedroom() {
        return Bedroom;
    }

    public void setBedroom(String bedroom) {
        Bedroom = bedroom;
    }

    public String getOwnerOccupied() {
        return OwnerOccupied;
    }

    public void setOwnerOccupied(String ownerOccupied) {
        OwnerOccupied = ownerOccupied;
    }

    public int getFlatmatesNumber() {
        return FlatmatesNumber;
    }

    public void setFlatmatesNumber(int number) {
        FlatmatesNumber = number;
    }

    public String getFlatmatesInfo() {
        return FlatmatesInfo;
    }

    public void setFlatmatesInfo(String info) {
        FlatmatesInfo = info;
    }

    public String getLookingFor() {
        return LookingFor;
    }

    public void setLookingFor(String lookingFor) {
        LookingFor = lookingFor;
    }

    public String getPreferences() {
        return Preferences;
    }

    public void setPreferences(String preferences) {
        Preferences = preferences;
    }

    public String getFacilities() {
        return Facilities;
    }

    public void setFacilities(String facilities) {
        Facilities = facilities;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public String getFrequency() {
        return Frequency;
    }

    public void setFrequency(String frequency) {
        Frequency = frequency;
    }

    public int getBills() {
        return Bills;
    }

    public void setBills(int bills) {
        Bills = bills;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int total) {
        Total = total;
    }

    public String getBer() {
        return Ber;
    }

    public void setBer(String ber) {
        Ber = ber;
    }
}
