package com.example.car_rental_21f21500;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;

public class carowner extends AppCompatActivity {

    Button addcr, displaycust, showcar, close, log_out, showweather;
    cust21F21500sql adddb;
    cars21F21500sql crsdb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carowner);

        addcr = findViewById(R.id.addcar);
        displaycust = findViewById(R.id.showcust);
        showcar = findViewById(R.id.showcr);
        close = findViewById(R.id.exitapp);
        log_out = findViewById(R.id.logout);
        showweather = findViewById(R.id.sw);

        adddb = new cust21F21500sql(this);
        crsdb = new cars21F21500sql(this);

        getaccount();
        SHOWallcarinfo();

        // Add customer button opens newcustomer activity
        addcr.setOnClickListener(view -> {
            Intent intent = new Intent(carowner.this, newcustomer.class);
            startActivity(intent);
        });

        // Show weather button
        showweather.setOnClickListener(view -> {
            Intent intent = new Intent(carowner.this, WeatherActivity.class);
            startActivity(intent);
                });

        // Close app button
        close.setOnClickListener(view -> finish());

        // Logout button
        log_out.setOnClickListener(view -> {
            Intent intent = new Intent(carowner.this, login.class);
            startActivity(intent);
            finish();
        });
    }

    // Get and display customer info
    public void getaccount() {
        displaycust.setOnClickListener(view -> {
            Cursor c3 = adddb.getaccount();
            if (c3 == null || c3.getCount() == 0) {
                viewccInformation("Error", "Customer not found");
                return;
            }
            StringBuilder ssbb = new StringBuilder();
            while (c3.moveToNext()) {
                ssbb.append("Customer ID: ").append(c3.getString(0)).append("\n");
                ssbb.append("Customer Name: ").append(c3.getString(1)).append("\n");
                ssbb.append("Customer Password: ").append(c3.getString(2)).append("\n");
                ssbb.append("----------------------\n");
            }
            c3.close();
            viewccInformation("Customer Details", ssbb.toString());
        });
    }

    // Get and display car info
    public void SHOWallcarinfo() {
        showcar.setOnClickListener(view -> {
            Cursor c4 = crsdb.SHOWallcarinfo();
            if (c4 == null || c4.getCount() == 0) {
                viewccInformation("Error", "Car not found");
                return;
            }
            StringBuilder sssbbb = new StringBuilder();
            while (c4.moveToNext()) {
                sssbbb.append("Car ID: ").append(c4.getString(0)).append("\n");
                sssbbb.append("Total days: ").append(c4.getString(1)).append("\n");
                sssbbb.append("Total rent pay: ").append(c4.getString(2)).append("\n");
                sssbbb.append("Payment date: ").append(c4.getString(3)).append("\n");
                sssbbb.append("Customer phone number: ").append(c4.getString(4)).append("\n");
            }
            c4.close();
            viewccInformation("Car Information", sssbbb.toString());
        });
    }

    // Show alert dialog
    public void viewccInformation(String title, String message) {
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.setCancelable(true);
        ad.setTitle(title);
        ad.setMessage(message);
        ad.show();
    }
}
