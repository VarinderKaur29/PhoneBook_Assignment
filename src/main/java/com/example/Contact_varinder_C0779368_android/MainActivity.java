package com.example.Contact_varinder_C0779368_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText firstName;
    EditText lastName;
    EditText phone;
    EditText address;
    TextView count;
    EditText personemail;
    DatabaseHelper mDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        personemail = findViewById(R.id.personemail);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        phone = findViewById(R.id.phoneNumber);
        address = findViewById(R.id.address);
        findViewById(R.id.add).setOnClickListener(this);
        findViewById(R.id.phoneList).setOnClickListener(this);
        mDataBase = new DatabaseHelper(this);



    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.add:
                addPerson();
                break;
            case R.id.phoneList:
                Intent intent = new Intent(MainActivity.this,PersonActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void addPerson() {
        String first_name = firstName.getText().toString().trim();
        String last_name = lastName.getText().toString().trim();
        String phone_number = phone.getText().toString().trim();
        String addr = address.getText().toString().trim();
        String email = personemail.getText().toString().trim();

        if (first_name.isEmpty()){
            firstName.setError("First Name field is empty");
            firstName.requestFocus();
            return;
        }

        if (last_name.isEmpty()){
            lastName.setError("First Name field is empty");
            lastName.requestFocus();
            return;
        }

        if (phone_number.isEmpty()){
            phone.setError("First Name field is empty");
            phone.requestFocus();
            return;
        }

        if (addr.isEmpty()){
            address.setError("First Name field is empty");
            address.requestFocus();
            return;
        }


        if (mDataBase.addPerson(first_name, last_name, phone_number, addr,email))
            Toast.makeText(this, "Person added", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Person not added", Toast.LENGTH_SHORT).show();


        firstName.setText("");
        lastName.setText("");
        phone.setText("");
        address.setText("");
        personemail.setText("");
    }
}
