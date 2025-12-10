package com.example.car_rental_21f21500;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {

    EditText ID, pass;
    Button logn;
    cust21F21500sql ctadd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        logn = findViewById(R.id.button);
        ID = findViewById(R.id.idtext);
        pass = findViewById(R.id.passtext);

        ctadd = new cust21F21500sql(this);
        setupLogin();
    }
    public void setupLogin() {
        logn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ID1 = ID.getText().toString().trim(); // get input for ID
                String P1 = pass.getText().toString().trim(); // get input for pass
                // Check if the owner or admin is logging in
                if (ID1.equals("basil") && P1.equals("admin")) {
                    Intent intent = new Intent(login.this, carowner.class);
                    startActivity(intent);
                } else {
                    // Attempt to login as tenant
                    try {
                        String BB = ctadd.getPass(ID1); // get pass from database
                        if (P1.equals(BB)) {
                            Toast.makeText(login.this, "Welcome to Rental Cars", Toast.LENGTH_SHORT).show();
                            ID.setText(""); // Clear input fields
                            pass.setText("");
                            Intent intent = new Intent(login.this, customer.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(login.this, "Wrong information provided", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(login.this, "Not able to login: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }}
        });
    }}