package com.hello.firstassigment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView etcheckin, etcheckout, tvprice, tvresult;
    Spinner spinRoom;
    Button btncal;
    EditText etadult, etchild, etroom;
    int year2, year3;
    int month2, month3;
    int day2, day3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etcheckin = findViewById(R.id.etcheckin);
        etcheckout = findViewById(R.id.etcheckout);
        spinRoom = findViewById(R.id.spinroom);
        etadult = findViewById(R.id.etadult);
        etchild = findViewById(R.id.etchild);
        etroom = findViewById(R.id.etroom);
        tvprice = findViewById(R.id.tvprice);
        btncal = findViewById(R.id.btncal);
        tvresult = findViewById(R.id.tvresult);


        etcheckin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDatePickerDate1();
            }
        });

        etcheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDatePickerDate2();
            }
        });
        String room[] = {"Select room type", "Deluxe -Rs. 2000", "Presidential -Rs. 5000", "Premium- Rs. 4000"};
        ArrayAdapter adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,room);
        spinRoom.setAdapter(adapter);

        btncal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(etcheckin.getText()))
                {
                    etcheckin.setError("Please enter Check in Date");
                    return;
                }

                if(TextUtils.isEmpty(etcheckout.getText()))
                {
                    etcheckout.setError("Please enter Check out Date");
                    return;
                }


                if (spinRoom.getSelectedItem()=="Select room type")
                {

                }
                if (TextUtils.isEmpty(etadult.getText())) {
                    etadult.setError("Enter no of Adults");
                    return;
                }
                if (TextUtils.isEmpty(etchild.getText())) {
                    etchild.setError("Enter no of childs");
                    return;
                }
                if (TextUtils.isEmpty(etroom.getText())) {
                    etroom.setError("Enter no of rooms");
                    return;
                }


                if (spinRoom.getSelectedItem() == "Deluxe -Rs. 2000") {
                    tvprice.setText("2000");


                }
                if (spinRoom.getSelectedItem() == "Presidential -Rs. 5000") {
                    tvprice.setText("5000");


                }
                if (spinRoom.getSelectedItem() == "Premium- Rs. 4000") {
                    tvprice.setText("4000");

                }

                int adult, child, room;
                double total, price, vat, GrossTotal;

                adult = Integer.parseInt(etadult.getText().toString());
                child = Integer.parseInt(etchild.getText().toString());
                room = Integer.parseInt(etroom.getText().toString());

                Calendar cal1=Calendar.getInstance();
                Calendar cal2=Calendar.getInstance();
                cal1.set(year2,month2,day2);
                cal2.set(year3,month3,day3);
                long millis1=cal1.getTimeInMillis();
                long millis2= cal2.getTimeInMillis();
                long diff=millis2-millis1;
                long diffDays=(diff/86400000);

                price = Integer.parseInt(tvprice.getText().toString());
                total = price * room * diffDays;
                vat = 0.13 * total;
                GrossTotal = total + vat;

                if (diff <=0)
                {
                    etcheckin.setError("Check-In Date is invalid");
                    etcheckout.setError("Check-Out Date is invalid");
                    tvresult.setText("Invalid Date");
                    return;
                }
                else
                {
                    tvresult.setText("Total: Rs." + total + "\n" + "Vat Rs.:" + vat + "\n" + "Gross Total: Rs." + GrossTotal);
                }





            }
        });

    }

    private void loadDatePickerDate2() {
        final Calendar c1=Calendar.getInstance();
        int year= c1.get(Calendar.YEAR);
        final int month = c1.get(Calendar.MONTH);
        int day= c1.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year1, int month, int dayOfMonth1) {
                month=month+1;
                String date = "Checking out Date: "+month + "/" + dayOfMonth1 + "/" + year1;

                month3=month;
                day3=dayOfMonth1;
                year3=year1;
                etcheckout.setText(date);
            }
        },year,month,day);
        datePickerDialog.show();


    }

    private void loadDatePickerDate1() {
        final Calendar c=Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month=month+1;
                String date = "Checking Date: "+month + "/" + dayOfMonth + "/" + year;
                month2=month;
                day2=dayOfMonth;
                year2=year;
                etcheckin.setText(date);
            }
        },year,month,day);
        datePickerDialog.show();



    }

}