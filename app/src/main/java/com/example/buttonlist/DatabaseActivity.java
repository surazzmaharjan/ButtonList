package com.example.buttonlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.buttonlist.model.DBHelper;
import com.example.buttonlist.model.Student;

import java.util.List;

public class DatabaseActivity extends AppCompatActivity {

    EditText editTextName,editTextEmail,editTextPhone;
    Button btnSave,btnsearch,btnallstudent;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        editTextName = findViewById(R.id.stname);
        editTextEmail = findViewById(R.id.stemail);
        editTextPhone = findViewById(R.id.stphone);

        btnSave = findViewById(R.id.btnStSave);
        btnsearch = findViewById(R.id.btnSearch);
        btnallstudent = findViewById(R.id.btnAllStudent);


        btnallstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DatabaseActivity.this, AllStudentActivity.class);
                startActivity(intent);
            }
        });

        dbHelper = new DBHelper(this);

        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId()==R.id.btnSearch)
                {
                    Intent intent = new Intent(DatabaseActivity.this,StudentSearchActivity.class);
                    startActivity(intent);
                }
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             String n = editTextName.getText().toString();
             String e = editTextEmail.getText().toString();
             String p = editTextPhone.getText().toString();

             Student student = new Student(0,n,e,p);

            if(dbHelper.addStudents(student)){
                Toast.makeText(DatabaseActivity.this, "Successfully saved", Toast.LENGTH_SHORT).show();
            }

            }
        });


        List<Student> students = dbHelper.getStudents();
        for(Student student:students){
            Log.d("id",String.valueOf(student.getId()));
            Log.d("name",student.getName());
            Log.d("email",student.getEmail());
            Log.d("phone",student.getPhone());

        }
    }



}
