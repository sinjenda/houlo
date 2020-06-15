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
    ArrayList<String> titles = new ArrayList<>();
    ArrayList<String> podminky = new ArrayList<>();
    ArrayList<Integer> zkusenost = new ArrayList<>();
    ArrayList<Boolean> booleans = new ArrayList<>();
    ArrayList<String> ret = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skill_show_activity);
        i = getIntent();
        showList();
    }

    @SuppressWarnings({"ConstantConditions", "unchecked"})
    public void showList() {

        Map<Smlouva, Boolean> hashMapa = new HashMap<>();
        Map<String, Integer> hashMapb = new HashMap<>();
        String s;
        list = findViewById(R.id.list);
        try {
            int i1 = 1;
            int test = 1;
            boolean fin = true;
            boolean b;

            while (test != 0) {
                s = i.getStringExtra("title" + i1);
                titles.add(s);
                s = i.getStringExtra("podminky" + i1);
                podminky.add(s);
                test = i.getIntExtra("zkusenost" + i1, 0);
                zkusenost.add(test);
                b = i.getBooleanExtra("booleans" + i1, false);
                booleans.add(b);
                i1++;
            }

            Button button = findViewById(R.id.butt);
            button.setText(R.string.note12);

        } catch (ClassCastException e) {
            e.getStackTrace();
            hashMapb = (HashMap<String, Integer>) i.getSerializableExtra(CALL);
        }
        if (i.getStringExtra(WHICH).equals("smlouva")) {
            for (int i = 0; i != titles.size(); ) {
                Smlouva smlouva = new Smlouva(titles.get(i), podminky.get(i), zkusenost.get(i), null);
                ret.add(titles.get(i) + " " + podminky.get(i) + " \nzkusenost: " + zkusenost.get(i) + "\n" + booleans.get(i));
                i++;
            }
            ArrayAdapter<String> adapterSmlouvaBoolean = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ret);
            list.setAdapter(adapterSmlouvaBoolean);
        } else {
            hashMapAdapterStringInteger adapterStringInteger = new hashMapAdapterStringInteger(hashMapb);
            list.setAdapter(adapterStringInteger);
        }

    }


    public void shskillsOnclick(@SuppressWarnings("unused") View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}

