package com.example.th1;

public enum StringQuery {
    CreateDatabase ("CREATE TABLE IF NOT EXISTS db_CONTACT (ID INTEGER PRIMARY KEY AUTOINCREMENT" +
            ", Name VARCHAR(200) NOT NULL" +
            ", Phone VARCHAR(200) NOT NULL" +
            ", Status INTEGER NOT NULL)"),
    SelectAllData ("SELECT ID, Name, Phone, Status FROM db_CONTACT"),
    DeleteAllData ("DELETE FROM db_CONTACT"),
    DeleteTable ("DROP TABLE IF EXISTS db_CONTACT");

    public String getQuery() {
        return query;
    }

    private String query;


    StringQuery(String query) {
        this.query = query;
    }
}
