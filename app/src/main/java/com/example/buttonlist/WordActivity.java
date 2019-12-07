package com.example.buttonlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class WordActivity extends AppCompatActivity {

    ListView listView;
    TextView textView;
    Button button,btnclear;
    String[] words={"apple","ee","ff"};
    SharedPreferences sp,spu;
    SharedPreferences.Editor editor,editoru;
    int level =0;
    int sub=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);

        listView = findViewById(R.id.wordlist);
        textView = findViewById(R.id.ans);
        button = findViewById(R.id.btnOk);
        btnclear = findViewById(R.id.btnClear);



        sp = getApplicationContext().getSharedPreferences("mygamed", MODE_PRIVATE);

        editor = sp.edit();


        level = sp.getInt("gamelevel", 0);
        showWord(level);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_word = textView.getText().toString();
                if(user_word.equals(words[level])){
                    level++;
                    showWord(level);

                    textView.setText("");

                    editor.putInt("gamelevel", level);

                    sub=words.length-1;

                    Log.d("level","total "+words.length);
                    Log.d("level","level "+level);

                    if(level==sub) {
                        Toast.makeText(getApplicationContext(), "Congratulations! You have finished all levels", Toast.LENGTH_SHORT).show();
                        level = 0;
                        editor.putInt("gamelevel", level);

                    }
                    editor.apply();


                }else{
                    Toast.makeText(WordActivity.this, "Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView.setText("");
            }
        });





        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String letter = adapterView.getItemAtPosition(i).toString();
                textView.append(letter);
                Toast.makeText(WordActivity.this,"my position is " + letter, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showWord(int i){
        Character[] word = shuffleWord(words[i]);

        ArrayAdapter<Character> adapter = new ArrayAdapter<Character>(WordActivity.this,R.layout.spinner_values,word);
        listView.setAdapter(adapter);

    }

    private Character[] shuffleWord(String word){

        ArrayList<Character> chars = new ArrayList<>(word.length());
        for(char c : word.toCharArray())
        {
            chars.add(c);
        }

        Collections.shuffle(chars);
        Character[] shuffled = new Character[chars.size()];

        for(int i =0; i<shuffled.length;i++)
        {
            shuffled[i]=chars.get(i);
        }
//        String shuffledword = new String(shuffled);
        return  shuffled;
    }







}
