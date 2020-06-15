package com.sin.lifesim;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;


public class showArray_activity extends AppCompatActivity {

    public static final String DATA = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showarray);
        ListView list = findViewById(R.id.list);
        String[] strings;
        Intent i = getIntent();

        strings = i.getStringArrayExtra(DATA);
        @SuppressWarnings("ConstantConditions") ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strings);
        list.setAdapter(adapter);
    }


    @SuppressWarnings("unused")
    public void shskillsArrayOnclick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
