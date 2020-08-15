package com.sin.lifesim;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.sin.lifesim.adapters.hashMapAdapterSmlouvaBoolean;
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
    final ArrayList<String> titles = new ArrayList<>();
    final ArrayList<Integer> zkusenost = new ArrayList<>();
    final ArrayList<Boolean> booleans = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skill_show_activity);
        i = getIntent();
        showList();
    }

    @SuppressWarnings({"ConstantConditions", "unchecked"})
    public void showList() {

        Map<Smlouva, Boolean> hashMapa;
        Map<String, Integer> hashMapb = new HashMap<>();
        String s;
        list = findViewById(R.id.list);

        if (i.getStringExtra("thing").equals("smlouvy")) {

            hashMapAdapterSmlouvaBoolean adapterSmlouvaBoolean = new hashMapAdapterSmlouvaBoolean((HashMap<Smlouva, Boolean>) i.getSerializableExtra(WHICH));
            list.setAdapter(adapterSmlouvaBoolean);
        } else {
            hashMapAdapterStringInteger adapterStringInteger = new hashMapAdapterStringInteger((HashMap<String, Integer>) i.getSerializableExtra(CALL));
            list.setAdapter(adapterStringInteger);
        }

    }


    public void shskillsOnclick(@SuppressWarnings("unused") View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}

