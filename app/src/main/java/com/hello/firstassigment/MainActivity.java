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

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private TextView etcheckin,etcheckout,tvprice,tvresult,tvfirst,tvsecond;
    boolean checkin,checkout;
    Spinner spinroom;
    Button btncal;
    EditText etadult, etchild, etroom;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etcheckin =findViewById(R.id.etcheckin);
        etcheckout =findViewById(R.id.etcheckout);
        spinroom =findViewById(R.id.spinroom);
        etadult =findViewById(R.id.etadult);
        etroom =findViewById(R.id.etroom);
        tvprice =findViewById(R.id.tvprice);
        btncal =findViewById(R.id.btncal);
        tvresult =findViewById(R.id.tvresult);
        tvfirst =findViewById(R.id.tvfirst);
        tvsecond =findViewById(R.id.tvsecond);


        etcheckin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDatePickerCheckin();
                checkin =true;
            }
        });

        etcheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDatePickerCheckout();
                checkout =true;
            }
        });

        String room[] = {"Select Your Room Type", "Deluxe = Rs.2000", "Presidential = Rs.5000", "Premium = Rs.4000"};
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, room);
        spinroom.setAdapter(adapter);


        btncal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adult,child,room;
                double Total, Price, Vat, GrossTotal;

                adult =Integer.parseInt(etadult.getText().toString());
                child =Integer.parseInt(etchild.getText().toString());
                room =Integer.parseInt(etroom.getText().toString());


                if (TextUtils.isEmpty(etadult.getText()))
                {
                    etadult.setError("Enter number of Adults");
                    return;
                }
                if (TextUtils.isEmpty(etchild.getText()));
                {
                    etchild.setError("Enter number of Childs");
                    return;
                }
                if (TextUtils.isEmpty(etroom.getText()))
                {
                    etroom.setError("Enter number of Rooms");
                }

                if (spinroom.getSelectedItem() == "Deluxe =Rs.2000")
                {
                    tvprice.setText("2000");
                }

                if (spinroom.getSelectedItem() == "Presidential =Rs.5000")
                {
                    tvprice.setText("5000");
                }
                if (spinroom.getSelectedItem() == "Premium =Rs.4000")
                {
                    tvprice.setText("4000");
                }

                Price =Integer.parseInt(tvprice.getText().toString());
                Total = Price*room;
                Vat = 0.13*Total;
                GrossTotal = Total + Vat;

                tvresult.setText("Total: Rs." + Total +"\n"+ "Vat: Rs." +Vat + "\n" +"Gross Total: Rs." +GrossTotal);
            }
        });


    }


    private void loadDatePickerCheckin()
    {
        final Calendar c = Calendar.getInstance();
        int year =c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        tvfirst.setText(c.getTime() + "");

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this, year, month, day);
        datePickerDialog.show();

    }

    private  void loadDatePickerCheckout()
    {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        tvsecond.setText(c.getTime() + "");

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this, year, month, day);
        datePickerDialog.show();
    }





    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        month = month +1;
        if (checkin == true){
            String date = "Check-In Date:" +dayOfMonth+ "/" +month+ "/" +year;
            etcheckin.setText(date);

            checkin = false;
            return;
        }

        if (checkout = true){
            String date ="Check-Out Date: " +dayOfMonth+ "/" +month+ "/" +year;

            etcheckout.setText(date);
            checkout = false;
            return;
        }

    }
}
