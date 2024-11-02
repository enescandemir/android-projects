package com.example.odev1_221229064;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.odev1_221229064.databinding.ActivityContactDetailBinding;


public class ContactDetailActivity extends AppCompatActivity {
    TextView nameTextView, phoneNumberTextView;
    Button deleteButton, updateButton;
    Contact contact;
    private ActivityContactDetailBinding binding;

    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityContactDetailBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        databaseHelper = new DatabaseHelper(this);

        // MainActivity'den gelen verileri al
        Intent intent = getIntent();
        Contact contact = (Contact) intent.getSerializableExtra("contact");

        binding.nameText.setText(contact.getName());
        binding.phoneText.setText(contact.getPhoneNumber());


        // Silme işlemi
        binding.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.deleteContact(contact.getId());
                Toast.makeText(ContactDetailActivity.this, "Kişi silindi", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ContactDetailActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Güncelleme işlemi
        binding.updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                contact.setName(binding.nameText.getText().toString());
                contact.setPhoneNumber(binding.phoneText.getText().toString());

                databaseHelper.updateContact(contact);
                Intent intent = new Intent(ContactDetailActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
