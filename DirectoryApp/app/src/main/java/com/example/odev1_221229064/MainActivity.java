package com.example.odev1_221229064;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.odev1_221229064.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    ArrayList<Contact> contactList;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        databaseHelper = new DatabaseHelper(this);
        contactList = databaseHelper.getAllContacts();

        ArrayAdapter contactArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,
                contactList.stream().map(contact -> contact.getName()).collect(Collectors.toList()));

        binding.listView.setAdapter(contactArrayAdapter);


        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
                startActivity(intent);
            }
        });

        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ContactDetailActivity.class);
                Contact contact = databaseHelper.getContact(contactList.get(position).getId());
                intent.putExtra("contact", contact);
                startActivity(intent);
            }
        });
    }
}