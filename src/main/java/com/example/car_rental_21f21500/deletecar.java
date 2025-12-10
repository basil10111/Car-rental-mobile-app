package com.example.car_rental_21f21500;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class deletecar extends AppCompatActivity {
    Button bhome, bdel;
    EditText c_ids;
    cars21F21500sql cdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deletecar);

        bhome=findViewById(R.id.home);
        bhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(deletecar.this, customer.class);
                startActivity(intent);
            }
        });
        c_ids=findViewById(R.id.idc);
        bdel=findViewById(R.id.delete);

        cdb=new cars21F21500sql(this);
        deleteOrder(); }
    public void deleteOrder(){
        bdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String iidd=c_ids.getText().toString();
                Integer ddeletee=cdb.deletecar(iidd);
                if(ddeletee>0) {
                    Toast.makeText(deletecar.this, "Car deleted", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(deletecar.this, customer.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(deletecar.this,"Car not deleted",Toast.LENGTH_SHORT).show();
                }}
        });
    }}