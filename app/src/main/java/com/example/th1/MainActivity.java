package com.example.th1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    SearchView searchView;
    ListView listView;
    Button button_add, button_delete;
    ArrayList<Contact> contacts;
    ContactAdapter contactAdapter;
    MyDatabase database;

    private void init() {
        searchView = findViewById(R.id.search_item);
        listView = findViewById(R.id.lv_contact);
        button_add = findViewById(R.id.btn_add);
        button_delete = findViewById(R.id.btn_del);
        contacts = new ArrayList<>();
        database = new MyDatabase(MainActivity.this, "ContactDatabase.sqlite", null, 1);

        contactAdapter = new ContactAdapter(MainActivity.this, R.layout.list_item, contacts);
        listView.setAdapter(contactAdapter);
    }

    private void RenderData() {
        contacts.clear();
        Cursor c = database.SelectAllData(StringQuery.SelectAllData.getQuery());
        while (c.moveToNext()) {
            String id = c.getString(0);
            String n = c.getString(1);
            String p = c.getString(2);
            String s = c.getString(3);
            Boolean check = false;
            if (s.equals("1")) {
                check = true;
            }
            contacts.add(new Contact(Integer.parseInt(id), n, p, check));
        }
        contactAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        RenderData();

        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getBundleExtra("bundle");
            if (bundle != null) {
                String n = bundle.getString("name");
                String p = bundle.getString("phone");
                database.Query("INSERT INTO db_CONTACT VALUES (NULL, '" + n + "', '" + p + "', '0')");
                RenderData();
            }
        }

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AddContact.class));
            }
        });

        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Notification");
                builder.setMessage("Are you sure?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int pos) {
                        for (int i = 0; i < contacts.size(); i++) {
                            if (contacts.get(i).getStatus()) {
                                String strName = contacts.get(i).getName();
                                String strPhone = contacts.get(i).getPhone();
                                String DeleteQuery = "DELETE FROM db_CONTACT WHERE Name = '" + strName + "' and Phone = '" + strPhone + "'";
                                database.Query(DeleteQuery);
                            }
                        }
                        database.SelectAllData(StringQuery.SelectAllData.getQuery());
                        contactAdapter.notifyDataSetChanged();
                    }
                });
                builder.setNeutralButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.item_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_sort_by_name_asc:
                SortByNameASC();
                break;
            case R.id.item_sort_by_name_desc:
                SortByNameDESC();
                break;
            case R.id.item_find:
                DialogFind();
        }

        return super.onOptionsItemSelected(item);
    }

    private void SortByNameASC() {
        Collections.sort(contacts, new Contact.NameOrderASC());
        contactAdapter.notifyDataSetChanged();
    }

    private void SortByNameDESC() {
        Collections.sort(contacts, new Contact.NameOrderDESC());
        contactAdapter.notifyDataSetChanged();
    }

    private void DialogFind(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_edit);

    }

}