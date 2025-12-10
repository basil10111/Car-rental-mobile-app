package com.example.car_rental_21f21500;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class newcar_rent extends AppCompatActivity {
    EditText crr1, cdy1, tltrent;
    EditText carID, pmtdate, tltdys, tltrnt, ctphoneno;
    CheckBox ccarb1;
    Button totalcalcrnt, addcr , clean, hm;
    EditText tdays, c1;

    private car1pricecalc c1calculate;
    private totalcar_rentprice tcrp;

    String b1;

    cars21F21500sql crdb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newcar_rent);

        ccarb1=findViewById(R.id.c1);
        crr1=findViewById(R.id.c1r);
        cdy1=findViewById(R.id.c1d);

        tdays=findViewById(R.id.tdays);
        c1=findViewById(R.id.c1d);

        ccarb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crr1.setText("22");
                b1="Car 1";
            }
        });

        hm=findViewById(R.id.homepgg);
        hm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(newcar_rent.this, customer.class);
                startActivity(intent);
            }
        });

        c1calculate=new car1pricecalc();
        tcrp=new totalcar_rentprice();

        //Calculating total rent with days taken by customer
        tltrent=findViewById(R.id.totalpcarrent);
        totalcalcrnt=findViewById(R.id.calcrent);

        totalcalcrnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String csr11=crr1.getText().toString();

                String csd11=cdy1.getText().toString();

                //converting numbers to double
                Double cdrent=Double.parseDouble(csr11);

                Double cddays=Double.parseDouble(csd11);

                Double crcalc1=c1calculate.car1calcc(cdrent, cddays);

                //Calculating total rent with days
                Double tltrr= tcrp.totalcar_rentcalculate(crcalc1);

                tltrent.setText(""+tltrr);
                tltrent.setVisibility(View.VISIBLE);
                tdays.setText(b1 + "-" + csd11);
            }
        });
        //Adding data into car database
        addcr=findViewById(R.id.add);
        carID=findViewById(R.id.carrID);
        tltrnt=findViewById(R.id.totalpcarrent);
        tltdys=findViewById(R.id.tdays);
        pmtdate=findViewById(R.id.pdate);
        ctphoneno=findViewById(R.id.phoneno);

        clean=findViewById(R.id.clear);
        clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tdays.setText("");
                tltdys.setText("");
                carID.setText("");
                pmtdate.setText("");
                ctphoneno.setText("");
                crr1.setText("");
                cdy1.setText("");
            }
        });
        crdb=new cars21F21500sql(this);
        addcars();
    }
    public void addcars(){
        addcr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String car_ID=carID.getText().toString();
                String daytotal=tltrnt.getText().toString();
                String totalrent=tltdys.getText().toString();
                String paydate=pmtdate.getText().toString();
                String phone_number=ctphoneno.getText().toString();
                boolean insert=crdb.addcar(car_ID, daytotal, totalrent, paydate, phone_number);
                if(insert==true)
                {
                    Toast.makeText(newcar_rent.this,"Car rent added", Toast.LENGTH_SHORT).show();
                    Intent login=new Intent(newcar_rent.this,customer.class);
                    startActivity(login);
                }
                else
                {
                    Toast.makeText(newcar_rent.this,"Car rent not added",Toast.LENGTH_SHORT).show();
                }}
        });
    }}