package com.example.odev1_221229064;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddContactActivity extends AppCompatActivity {
    EditText nameEditText, phoneNumberEditText;
    Button addButton;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        nameEditText = findViewById(R.id.nameText);
        phoneNumberEditText = findViewById(R.id.phoneText);
        addButton = findViewById(R.id.addButton);
        databaseHelper = new DatabaseHelper(this);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addContact();
            }
        });
    }

    private void addContact() {
        String name = nameEditText.getText().toString().trim();
        String phoneNumber = phoneNumberEditText.getText().toString().trim();

        if (name.isEmpty() || phoneNumber.isEmpty()) {
            Toast.makeText(this, "Lütfen tüm alanları doldurun", Toast.LENGTH_SHORT).show();
            return;
        }

        Contact contact = new Contact(name, phoneNumber);
        databaseHelper.addContact(contact);

        Toast.makeText(this, "Kişi başarıyla eklendi", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
