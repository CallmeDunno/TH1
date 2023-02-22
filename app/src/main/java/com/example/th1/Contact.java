package com.example.th1;

public class Contact {
    private int Id;
    private String name;
    private String phone;
    private boolean status;

    public Contact(int id, String name, String phone, boolean status) {
        Id = id;
        this.name = name;
        this.phone = phone;
        this.status = status;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
