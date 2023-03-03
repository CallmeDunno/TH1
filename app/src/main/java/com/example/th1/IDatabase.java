package com.example.th1;

import android.database.Cursor;

public interface IDatabase {
    void Query(String query);
    Cursor SelectAllData(String query);
}
