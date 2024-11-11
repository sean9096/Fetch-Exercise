package com.fetch.take_home.data;

import java.util.Arrays;

public class Receipt {
    String retailer;
    String purchaseDate;
    String purchaseTime;
    String total;
    Item[] items;

    public Receipt(String retailer, String purchaseDate, String purchaseTime, String total, Item[] items) {
        this.retailer = retailer;
        this.purchaseDate = purchaseDate;
        this.purchaseTime = purchaseTime;
        this.total = total;
        this.items = items;
    }

    public String getRetailer() {
        return retailer;
    }

    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(String purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item[] items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Receipt [retailer=" + retailer + ", purchaseDate=" + purchaseDate + ", purchaseTime=" + purchaseTime
                + ", total=" + total + ", items=" + Arrays.toString(items) + "]";
    }
}
