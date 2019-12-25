package com.example.buttonlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.buttonlist.API.EmployeeInterface;
import com.example.buttonlist.model.Employee;
import com.example.buttonlist.model.Flag;
import com.example.buttonlist.model.Student;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity {

    EmployeeInterface employeeInterface;
    Retrofit retrofit;
    TextView textView;
    EditText etName,etSalary,etAge;
    Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        textView = findViewById(R.id.allEmployee);

        etName = findViewById(R.id.employeeName);
        etAge = findViewById(R.id.employeeAge);
        etSalary = findViewById(R.id.employeeSalary);

        btnAdd = findViewById(R.id.SaveEmployeeButton);

        retrofit = new Retrofit.Builder().baseUrl("http://dummy.restapiexample.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        employeeInterface = retrofit.create(EmployeeInterface.class);



        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n = etName.getText().toString();
                String a = etAge.getText().toString();
                String s = etSalary.getText().toString();

                Employee em = new Employee(0,n,s,a);
                deleteEmployee(180548);
//                addEmployee(em);
//                updateEmployee(1,em);

//                Flag flag = new Flag(0,n,s);
            }
        });






        employeeList();
        employeeListId();



    }

    private void employeeListId(){
        Call<Employee> employeeListById = employeeInterface.getEmployeeById(1);


        employeeListById.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                Toast.makeText(RetrofitActivity.this, response.body().getEmployee_name(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Log.d("ApiEx:",t.getMessage());
            }
        });
    }
    private void employeeList(){
        Call<List<Employee>> employeeList = employeeInterface.getEmployees();

        employeeList.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                List<Employee> empList = response.body();

                for (Employee emp: empList) {
//                    Log.d("name:",emp.getEmployee_name());
//                    textView.append("Name"+);
//
//                    emp.getEmployee_name();
//                    emp.getEmployee_salary();
                }
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Log.d("ApiEx:",t.getMessage());

            }
        });
    }
    private void addEmployee(Employee employee){
        Call<Void> employeeAdd = employeeInterface.addEmployee(employee);

        employeeAdd.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(RetrofitActivity.this, "Employee Added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("ApiEx:",t.getMessage());
            }
        });
    }


    private void updateEmployee(int i ,Employee employee){
        Call<Void> employeeupdate = employeeInterface.updateEmployee(i,employee);

        employeeupdate.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(RetrofitActivity.this, "Employee Update", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("ApiEx:",t.getMessage());
            }
        });
    }



    private void deleteEmployee(int id){
        Call<Void> employeedelete = employeeInterface.deleteEmployee(id);

        employeedelete.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(RetrofitActivity.this, "Employee Deleted "+ response.code(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d("ApiEx:",t.getMessage());

            }
        });
    }
}
