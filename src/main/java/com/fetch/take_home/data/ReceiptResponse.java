package com.fetch.take_home.data;

public class ReceiptResponse {
    String id;

    public ReceiptResponse(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ReceiptResponse [id=" + id + "]";
    }
}
