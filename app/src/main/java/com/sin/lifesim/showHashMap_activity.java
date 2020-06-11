package com.sin.lifesim;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.sin.lifesim.adapters.hashMapAdapterStringInteger;
import com.sin.lifesim.work.smlouva.Smlouva;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class showHashMap_activity extends AppCompatActivity {

    public static final String CALL = "";
    public static final String WHICH = "";
    ListView list;
    Intent i;
    Krmic krmic = new Krmic();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skill_show_activity);
        i = getIntent();
        showList();
    }

    @SuppressWarnings({"ConstantConditions", "unchecked"})
    public void showList() {
        Map<Smlouva, Boolean> hashMapa = null;
        Map<String, Integer> hashMapb = null;
        String[] s = {};
        list = findViewById(R.id.list);
        try {
            //s=i.getStringArrayExtra("map");
            Button button = findViewById(R.id.butt);
            button.setText(R.string.note12);
        } catch (ClassCastException e) {
            hashMapb = (HashMap<String, Integer>) i.getSerializableExtra(CALL);
        }
        if (i.getStringExtra(WHICH).equals("smlouva")) {
            ArrayList<Object> o = (ArrayList<Object>) i.getSerializableExtra(CALL);
            ArrayAdapter<Object> adapter = new ArrayAdapter<Object>(this, android.R.layout.simple_list_item_1, o);
            list.setAdapter(adapter);
        } else {
            hashMapAdapterStringInteger adapterStringInteger = new hashMapAdapterStringInteger(hashMapb);
            list.setAdapter(adapterStringInteger);
        }

    }


    public void shskillsOnclick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}

