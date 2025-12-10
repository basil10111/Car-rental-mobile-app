package com.example.car_rental_21f21500;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class customer extends AppCompatActivity {
    Button customercar, deletecar, logot, closeapp, shwwthr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        // Add car
        customercar = findViewById(R.id.Addccr);
        customercar.setOnClickListener(view -> {
            Intent intent = new Intent(customer.this, newcar_rent.class);
            startActivity(intent);
        });

        // Delete car
        deletecar = findViewById(R.id.deletecr);
        deletecar.setOnClickListener(view -> {
            Intent intent = new Intent(customer.this, deletecar.class);
            startActivity(intent);
        });

        // Logout
        logot = findViewById(R.id.logout);
        logot.setOnClickListener(view -> {
            Intent intent = new Intent(customer.this, login.class);
            startActivity(intent);
            finish();
        });

        // Close app
        closeapp = findViewById(R.id.exitcr);
        closeapp.setOnClickListener(view -> System.exit(0));


        // Show Weather button
        shwwthr = findViewById(R.id.showwethr);
        shwwthr.setOnClickListener(view -> {
            Intent intent = new Intent(customer.this, WeatherActivity.class);
            startActivity(intent);
        });
    }
}
