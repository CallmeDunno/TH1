package com.example.th1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SearchView searchView;
    ListView listView;
    Button button_add, button_delete;
    ArrayList<Contact> contacts;
    ContactAdapter contactAdapter;

    private void init(){
        searchView = findViewById(R.id.search_item);
        listView = findViewById(R.id.lv_contact);
        button_add = findViewById(R.id.btn_add);
        button_delete = findViewById(R.id.btn_del);
        contacts = new ArrayList<>();
        contacts.add(new Contact(0, "Nguyen Quoc Dung", "0337693148", true));
        contacts.add(new Contact(0, "Nguyen Quoc Dung", "0337693148", true));
        contacts.add(new Contact(0, "Nguyen Quoc Dung", "0337693148", false));
        contacts.add(new Contact(0, "Nguyen Quoc Dung", "0337693148", true));
        contacts.add(new Contact(0, "Nguyen Quoc Dung", "0337693148", true));
        contacts.add(new Contact(0, "Nguyen Quoc Dung", "0337693148", true));
        contacts.add(new Contact(0, "Nguyen Quoc Dung", "0337693148", true));
        contacts.add(new Contact(0, "Nguyen Quoc Dung", "0337693148", true));
        contacts.add(new Contact(0, "Nguyen Quoc Dung", "0337693148", true));
        contactAdapter = new ContactAdapter(MainActivity.this, R.layout.list_item, contacts);
        listView.setAdapter(contactAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        getSupportActionBar().hide();
        init();

        Intent intent = getIntent();
        if (intent != null){
            Bundle bundle = intent.getBundleExtra("bundle");
            if (bundle != null){
                String n = bundle.getString("name");
                String p = bundle.getString("phone");
                contacts.add(new Contact(0, n, p, false));
                contactAdapter.notifyDataSetChanged();
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
                        for (int i = 0; i < contacts.size(); i++){
                            if (contacts.get(i).getStatus()){
                                contacts.remove(i);
                                contactAdapter.notifyDataSetChanged();
                            }
                        }
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
}