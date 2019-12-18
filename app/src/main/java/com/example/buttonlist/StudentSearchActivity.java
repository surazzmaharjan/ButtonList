package com.example.buttonlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.example.buttonlist.model.DBHelper;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

public class StudentSearchActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    SearchStudentAdapter searchStudentAdapter;

    MaterialSearchBar materialSearchBar;
    List<String> suggestList = new ArrayList<>();
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_student);

        recyclerView = findViewById(R.id.recycler_search);

        layoutManager= new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        materialSearchBar = findViewById(R.id.search_bar);

        //Init DB
        dbHelper = new DBHelper(this);

        //Setup seach bar

        materialSearchBar.setHint("Search By Name");
//        materialSearchBar.setCardViewElevation(10);
        loadSuggestList();

        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                List<String> suggest = new ArrayList<>();

                for(String search:suggestList)
                {
                    if(search.toLowerCase().contains(materialSearchBar.getText().toLowerCase())){
                        suggest.add(search);
                    }
                    materialSearchBar.setLastSuggestions(suggest);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if(!enabled){

                    searchStudentAdapter = new SearchStudentAdapter(dbHelper.getStudents(),getBaseContext());
                    recyclerView.setAdapter(searchStudentAdapter);
                }
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                    startSearch(text.toString());
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });


        //Init Adapter default set all result
        searchStudentAdapter = new SearchStudentAdapter(dbHelper.getStudents(),this);
        recyclerView.setAdapter(searchStudentAdapter);

    }

    private void startSearch(String text){
        searchStudentAdapter = new SearchStudentAdapter(dbHelper.getStudentsByName(text),this);
        recyclerView.setAdapter(searchStudentAdapter);
    }

    private void loadSuggestList(){
        suggestList = dbHelper.getStudentsNames();
        materialSearchBar.setLastSuggestions(suggestList);
    }
}
