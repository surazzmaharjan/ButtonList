package com.example.buttonlist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.buttonlist.model.User;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class TableListActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener{

    EditText editTextN,editTextE,editTextD,editTextA,editTextP;
//    CalendarView calendar;
    RadioGroup radioG;
    Spinner spin;
    Button buttonsubmit,buttonView;
    String name,gender,dob,country,email,address,phone;
//    String[] countries ={"Nepal","India","Pakistan","Sri-Lanka","Bhutan","Maldives","Myanmar","Afganistan"};

    ArrayList<User> userList = new ArrayList<>();


    Calendar calendardata = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener mydatepicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            calendardata.set(Calendar.YEAR,i);
            calendardata.set(Calendar.MONTH,i1);
            calendardata.set(Calendar.DAY_OF_MONTH,i2);
            String mydateFormat ="dd-MM-y";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(mydateFormat, Locale.getDefault());
            editTextD.setText(simpleDateFormat.format(calendardata.getTime()));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_layout);

        editTextA=findViewById(R.id.address);
        editTextN=findViewById(R.id.name);
        editTextE=findViewById(R.id.email);
        editTextD=findViewById(R.id.date);
        editTextP=findViewById(R.id.phone);
        radioG=findViewById(R.id.gender);
        spin=findViewById(R.id.spCountry);

        buttonsubmit=findViewById(R.id.btnsubmit);
        buttonView=findViewById(R.id.btnview);
//        calendar=findViewById(R.id.calendarView);
//
//
//
//
//        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            @Override
//            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
//                String mydate = String.valueOf(calendar.getDate());
//                Toast.makeText(TableListActivity.this, String.valueOf(i)+"-"+String.valueOf(i1)+"-"+String.valueOf(i2), Toast.LENGTH_SHORT).show();
//            }
//        });

        List<String> countries = new ArrayList<>();


        countries.add(0,"Choose Country");
        countries.add("Nepal");
        countries.add("India");
        countries.add("Pakistan");
        countries.add("Bhutan");

        ArrayAdapter<String> adapter =new ArrayAdapter(this,R.layout.spinner_values,countries);
        spin.setAdapter(adapter);



        radioG.setOnCheckedChangeListener(this);
        buttonsubmit.setOnClickListener(this);
        buttonView.setOnClickListener(this);
        editTextD.setOnClickListener(this);

        setSpinnerValue();
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if(i== R.id.rmale)
        {
            gender="Male";
            //Toast.makeText(this, "Male", Toast.LENGTH_SHORT).show();
        }
        if(i == R.id.rfemale)
        {
            gender = "Female";
            //Toast.makeText(this, "Female", Toast.LENGTH_SHORT).show();
        }
        if(i== R.id.rother)
        {
            gender ="Other";
            //Toast.makeText(this, "Other", Toast.LENGTH_SHORT).show();
        }
    }

    private void setSpinnerValue(){

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(adapterView.getItemAtPosition(i).equals("Choose Country"))
                {
                    Toast.makeText(getApplicationContext(),"Please Select any one option",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    country = adapterView.getSelectedItem().toString();
                    //Toast.makeText(TableListActivity.this, "Hello mane", Toast.LENGTH_SHORT).show();
                    Toast.makeText(adapterView.getContext(), "Selected :"+country, Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(TableListActivity.this, "Please Choose Country", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onClick(View view) {
        name = editTextN.getText().toString();
        dob=editTextD.getText().toString();
        address=editTextA.getText().toString();
        email=editTextE.getText().toString();
        phone = editTextP.getText().toString();

            if(view.getId()==R.id.btnsubmit)
            {
                if(validate())
                {
                    userList.add(new User(name,gender,dob,country,phone,email));
//                    Toast.makeText(this,userList.get(0).getName(), Toast.LENGTH_SHORT).show();
                }


//                String uri = "@drawable/"+img;
//                int resID = getResources().getIdentifier(uri,null,getPackageName());
//                Drawable drawable = getResources().getDrawable(resID);
//                imageView.setImageDrawable(drawable);

            }

        if(view.getId()==R.id.date)
        {
            new DatePickerDialog(this,mydatepicker,calendardata.get(Calendar.YEAR),calendardata.get(Calendar.MONTH),
                    calendardata.get(Calendar.DAY_OF_MONTH)).show();
        }

        if(view.getId()==R.id.btnview)
        {

//            for(User user:userList){
//                Log.d("Name :",user.getName());
////                String name=user.getName();
//                Log.d("Dob : ",user.getDob());
//                Log.d("Country :",user.getCountry());
//                Log.d("Gender :",user.getGender());
//                Log.d("Phone : ",user.getPhone());
//                Log.d("Email : ",user.getEmail());
//
//                intent.putExtra("username",user.getName());
//                intent.putExtra("dob",user.getDob());
//                intent.putExtra("country",user.getCountry());
//                intent.putExtra("gender",user.getGender());
//                intent.putExtra("phone",user.getPhone());
//                intent.putExtra("email",user.getEmail());
//
//            }
            Intent intent = new Intent(this, view_details_listVIew.class);

//            Bundle bundle = new Bundle();
//            bundle.putSerializable("userList", userList);
            intent.putExtra("userlist",userList);


            startActivity(intent);

        }


    }

    private boolean validate(){
        if(TextUtils.isEmpty(name))
        {
            editTextN.setError("Enter A Name");
            editTextN.requestFocus();
            editTextN.setHint("Please Enter a Name");
            return false;
        }
        if(TextUtils.isEmpty(dob))
        {
            editTextD.setError("Enter A DOB");
            editTextD.requestFocus();
            editTextD.setHint("Please Enter a DOB");
            return false;
        }

        if(TextUtils.isEmpty(address))
        {
            editTextA.setError("Enter A Address");
            editTextA.requestFocus();
            editTextA.setHint("Please Enter a Address");
            return false;
        }

        if(TextUtils.isEmpty(email))
        {
            editTextE.setError("Enter A Email");
            editTextE.requestFocus();
            editTextE.setHint("Please Enter a Email");
            return false;
        }

        if(TextUtils.isEmpty(gender))
        {
            Toast.makeText(this, "Please select a gender", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(country))
        {
            Toast.makeText(this, "Please select a country", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(TextUtils.isEmpty(phone))
        {
            editTextP.setError("Enter empty Phone");
            editTextP.requestFocus();
            editTextP.setHint("Please Enter a Phone");
            return false;
        }

        if(!TextUtils.isDigitsOnly(phone))
        {
            editTextP.setError("Invalid Phone");
            editTextP.requestFocus();
            editTextP.setHint("Please Enter a Phone");
            return false;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            editTextE.setError("Invalid  Email");
            editTextE.requestFocus();
            editTextE.setHint("Please Enter a Email");
            return false;
        }

        return  true;
    }
}
