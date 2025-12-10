package com.example.car_rental_21f21500;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class newcustomer extends AppCompatActivity {
    EditText ctid,ctname,ctpass;
    Button addct,hme,clrr;
    cust21F21500sql ctsqldb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newcustomer);
        ctid=findViewById(R.id.cttidtext);
        ctname=findViewById(R.id.cttname);
        ctpass=findViewById(R.id.ctttpass);
        addct=findViewById(R.id.addcustm);

        clrr=findViewById(R.id.cleartext);
        clrr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ctid.setText("");
                ctname.setText("");
                ctid.setText("");
            }
        });
        addnew_customer();
        ctsqldb = new cust21F21500sql(this);
        hme=findViewById(R.id.homepg);
        hme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(newcustomer.this, carowner.class);
                startActivity(intent);
            }
        });
    }
    public void addnew_customer(){
        addct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stid=ctid.getText().toString();
                String stn=ctname.getText().toString();
                String stp=ctpass.getText().toString();
                boolean insert=ctsqldb.addcust(stid,stn,stp);
                if(insert==true)
                {
                    Toast.makeText(newcustomer.this,"Customer added", Toast.LENGTH_SHORT).show();
                    Intent login=new Intent(newcustomer.this,login.class);
                    startActivity(login);
                }
                else
                {
                    Toast.makeText(newcustomer.this,"Customer already added", Toast.LENGTH_SHORT).show();
                }}
        });
    }}