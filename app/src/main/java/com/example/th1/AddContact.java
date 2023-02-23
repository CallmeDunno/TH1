package com.example.th1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddContact extends AppCompatActivity {

    EditText editText_name, editText_phone;
    Button button_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        editText_name = findViewById(R.id.edt_name);
        editText_phone = findViewById(R.id.edt_phone);
        button_add = findViewById(R.id.btn_add_new);

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = editText_name.getText().toString().trim();
                String p = editText_phone.getText().toString().trim();
                Intent intent = new Intent(AddContact.this, MainActivity.class);
                Bundle bundle = new Bundle();
//                intent.putExtra("name", n);
//                intent.putExtra("phone", p);
                bundle.putString("name", n);
                bundle.putString("phone", p);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });
    }
}