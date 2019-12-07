package com.example.buttonlist.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

   private static final String DB_NAME ="mydb";
   private static final int DB_VERSION = 1;


    public DBHelper(@Nullable Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Student.TBL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addStudent(Student student){
        try{

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO students (name,email,phone) Values('"+student.getName()+"','"+student.getEmail()+"','"+student.getPhone()+"')");
        return true;
        }
        catch (Exception e){
            Log.d("Db_Ex",e.toString());
            return false;
        }
    }

    public List<Student> getStudents(){
        List<Student> studentList = new ArrayList<>();

        try{

            SQLiteDatabase db = getWritableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM students",null);
            if(cursor!=null){
                while (cursor.moveToNext()){
                    studentList.add(new Student(cursor.getInt(0),
                            cursor.getString(1),cursor.getString(2),
                            cursor.getString(3)));

                }
            }
            return studentList;
        }
        catch (Exception e) {
            Log.d("Db_Ex", e.toString());

        }
            return studentList;

    }


    public List<Student> getStudentsByName(String name){
        List<Student> studentList = new ArrayList<>();

        try{

            SQLiteDatabase db = getWritableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM students WHERE name LIKE ?",new String[]{"%"+name+"%"});
            if(cursor!=null){
                while (cursor.moveToNext()){
                    studentList.add(new Student(cursor.getInt(0),
                            cursor.getString(1),cursor.getString(2),
                            cursor.getString(3)));

                }
            }
            return studentList;
        }
        catch (Exception e) {
            Log.d("Db_Ex", e.toString());

        }
        return studentList;

    }

}
