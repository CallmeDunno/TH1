package com.example.th1;

import java.util.Comparator;

public class Contact {
    private int Id;
    private String name;
    private String lastName;
    private String phone;
    private boolean status;

    public Contact(int id, String name, String phone, boolean status) {
        this.Id = id;
        this.name = name;
        String[] field = name.split("\\s+");
        this.lastName = field[field.length-1];
        this.phone = phone;
        this.status = status;
    }

    public String getLastName() {
        return lastName;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
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

    public static class NameOrderASC implements Comparator<Contact> {

        @Override
        public int compare(Contact a, Contact b) {
            return a.lastName.compareTo(b.lastName);
        }
    }

    public static class NameOrderDESC implements Comparator<Contact> {

        @Override
        public int compare(Contact a, Contact b) {
            return b.lastName.compareTo(a.lastName);
        }
    }
}
