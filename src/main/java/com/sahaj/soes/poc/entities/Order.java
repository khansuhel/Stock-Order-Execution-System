package com.sahaj.soes.poc.entities;

/**
 * Created by sukhan on 1/1/2017.
 */
public class Order {

    private String stockID;
    private String side;
    private String company;
    private String quantity;
    private String balance;
    private String status;

    public Order(){
    }


    public Order(String stockID, String side, String company, String quantity) {
        this.stockID = stockID;
        this.side = side;
        this.company = company;
        this.quantity = quantity;
    }

    public String getStockID() {
        return stockID;
    }

    public void setStockID(String stockID) {
        this.stockID = stockID;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "stockID=" + stockID +
                ", side='" + side + '\'' +
                ", company='" + company + '\'' +
                ", quantity=" + quantity +
                ", balance=" + balance +
                ", status='" + status + '\'' +
                '}';
    }
}
