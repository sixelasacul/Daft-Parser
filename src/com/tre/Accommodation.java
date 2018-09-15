package com.tre;

import java.util.Date;

class Accommodation {
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

    Accommodation() {
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

    int getId() {
        return Id;
    }

    void setId(int id) {
        Id = id;
    }

    Date getDateEntered() {
        return DateEntered;
    }

    void setDateEntered(Date date) {
        DateEntered = date;
    }

    int getViews() {
        return Views;
    }

    void setViews(int views) {
        Views = views;
    }

    String getType() {
        return Type;
    }

    void setType(String type) {
        Type = type;
    }

    String getAddress() {
        return Address;
    }

    void setAddress(String address) {
        Address = address;
    }

    float getDistance() {
        return Distance;
    }

    void setDistance(float distance) {
        Distance = distance;
    }

    String getBedroom() {
        return Bedroom;
    }

    void setBedroom(String bedroom) {
        Bedroom = bedroom;
    }

    String getOwnerOccupied() {
        return OwnerOccupied;
    }

    void setOwnerOccupied(String ownerOccupied) {
        OwnerOccupied = ownerOccupied;
    }

    int getFlatmatesNumber() {
        return FlatmatesNumber;
    }

    void setFlatmatesNumber(int number) {
        FlatmatesNumber = number;
    }

    String getFlatmatesInfo() {
        return FlatmatesInfo;
    }

    void setFlatmatesInfo(String info) {
        FlatmatesInfo = info;
    }

    String getLookingFor() {
        return LookingFor;
    }

    void setLookingFor(String lookingFor) {
        LookingFor = lookingFor;
    }

    String getPreferences() {
        return Preferences;
    }

    void setPreferences(String preferences) {
        Preferences = preferences;
    }

    String getFacilities() {
        return Facilities;
    }

    void setFacilities(String facilities) {
        Facilities = facilities;
    }

    int getPrice() {
        return Price;
    }

    void setPrice(int price) {
        Price = price;
    }

    String getFrequency() {
        return Frequency;
    }

    void setFrequency(String frequency) {
        Frequency = frequency;
    }

    int getBills() {
        return Bills;
    }

    void setBills(int bills) {
        Bills = bills;
    }

    int getTotal() {
        return Total;
    }

    void setTotal(int total) {
        Total = total;
    }

    String getBer() {
        return Ber;
    }

    void setBer(String ber) {
        Ber = ber;
    }
}
