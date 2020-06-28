package com.sin.lifesim;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.sin.lifesim.work.smlouva.Smlouva;
import com.sin.lifesim.work.work;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;


@SuppressWarnings({"CanBeFinal", "EmptyMethod", "unused", "unchecked"})
public class MainActivity extends AppCompatActivity {
    //view and string variables
    EditText input;
    EditText bonus;
    Button button;
    String dat2;
    String dat3 = "";
    String place = "default";
    TextView out;

    //int variables


    public int money = 100;

    //array lists


    HashMap<String, Integer> itemssell = new HashMap<String, Integer>();
    HashMap<String, Integer> basicskills = new HashMap<String, Integer>();
    ArrayList<String> skills = new ArrayList<String>();
    ArrayList<String> itemshave = new ArrayList<String>();
    ArrayList<String> nms = new ArrayList<String>();
    String[] itsella = {"fries", "screwdriver", "gun", "knife", "baton", "pancakes", "house"};

    //other
    Krmic krmn;
    Prison alcatraz;
    randomEvents r = new randomEvents(this);
    work w;
    SharedPreferences.Editor editor;
    int energy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        krmn = new Krmic();
        basicskills.put("strength", 1);
        basicskills.put("agility", 1);
        basicskills.put("luck", 1);
        getResources().getString(R.string.baton);
        int[] itsellb = {10, 20, 100, 60, 30, 20, 200};
        ArrayList<String> ita;
        ArrayList<Integer> itB;
        ita = krmn.polePut(itsella);
        itB = krmn.polePut(itsellb);
        itemssell = krmn.nakrmDataHash(ita, itB);
        w = new work(this, getApplication());
        w.setZamestnani("garbage");
        click(new View(getApplication()));
    }

    public void window(final method.onmet method, final String[] items) {
        final String[] selectedText = new String[1];
        final String[] ret = new String[1];
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                selectedText[0] = items[item];
                ret[0] = selectedText[0];
                method.methoda(ret);
            }
        });

        AlertDialog alertDialogObject = builder.create();
        alertDialogObject.show();
    }

    public void windowkill() {

        final String[] selectedText = new String[1];
        final String[] jmena = krmn.poleConverter(krmn.polepull(itemshave));
        final String[] ret = new String[1];
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setNegativeButton("nothink", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                ret[0] = "nothink";
                alcatraz.kill(ret[0]);
            }
        });
        builder.setItems(jmena, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                selectedText[0] = jmena[item];
                ret[0] = selectedText[0];
                alcatraz.kill(ret[0]);
            }
        });

        AlertDialog alertDialogObject = builder.create();
        alertDialogObject.show();

    }

    @Override
    protected void onDestroy() {
        Calendar calendar = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") DateFormat formatData = new SimpleDateFormat("DD");
        editor.putInt("time", Integer.parseInt(formatData.format(calendar.getTime())));
        super.onDestroy();
    }

    @SuppressWarnings("ConstantConditions")
    public void buy(final SharedPreferences.Editor editor) {
        final String[] selectedText = new String[1];
        final String[] jmena = itsella;
        final String[] ret = new String[1];
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("what you want to buy?");
        builder.setItems(jmena, new DialogInterface.OnClickListener() {
            @SuppressLint("SetTextI18n")
            public void onClick(DialogInterface dialog, int itemr) {
                selectedText[0] = jmena[itemr];
                ret[0] = selectedText[0];
                String item = ret[0];
                int cena = itemssell.get(item);
                if (money > cena) {
                    money = money - cena;
                    try {
                        itemshave.add(item);
                    } catch (RuntimeException err) {
                        out.setText(R.string.err3);
                    }
                    editor.putInt("money", money);
                    out.setText("úspěšně jste si koupil/a" + item + "za" + cena);
                } else {
                    out.setText(R.string.err2);
                }

            }
        });

        AlertDialog alertDialogObject = builder.create();
        alertDialogObject.show();


    }

    @SuppressWarnings({"ConstantConditions", "rawtypes"})
    @SuppressLint({"ApplySharedPref", "SetTextI18n"})
    public void click(View view) {
        input = findViewById(R.id.in);
        dat2 = input.getText().toString();
        if (dat2.equals("shskills")) {
            shskills();
        }


        bonus = findViewById(R.id.bonus);
        out = findViewById(R.id.output);

        button = findViewById(R.id.butt);
        SharedPreferences data = getPreferences(MODE_PRIVATE);
        editor = data.edit();
        String s = input.getText().toString();

        if (!data.getString("started", "ne").equals("ano")) {
            money = 100;
            out.setText(R.string.tutorial);
            editor.putString("started", "ano");
            editor.putInt("agility", basicskills.get("agility"));
            editor.putInt("strength", basicskills.get("strength"));
            editor.putInt("luck", basicskills.get("luck"));
            editor.putInt("money", money);
            editor.putString("workplace", "garbage");
            editor.putString("place", place);
            w.first();
            Intent i = getIntent();


            editor.putStringSet("titles", krmn.smlouvasGetTitles(w.getSmlouvyHistorie()));
            editor.putStringSet("podminky", krmn.smlouvasGetPodminky(w.getSmlouvyHistorie()));
            editor.putStringSet("zkusenost", krmn.smlouvasGetZkusenost(w.getSmlouvyHistorie()));
            editor.putString("place", place);
            editor.commit();
        } else {
            basicskills.put("agility", data.getInt("agility", -1));
            basicskills.put("strength", data.getInt("strength", -1));
            basicskills.put("luck", -1);
            money = data.getInt("money", -1);
            w.setZamestnani(data.getString("workplace", null));
            place = data.getString("place", null);
            Set<String> titles = data.getStringSet("titles", null);
            Set<String> podminky = data.getStringSet("podminky", null);
            Set<String> zkusenost = data.getStringSet("zkusenost", null);
            Set<String> booleans = data.getStringSet("booleans", null);
            Calendar calendar = Calendar.getInstance();
            DateFormat format = new SimpleDateFormat("DD");
            if (data.getInt("time", -1) != Integer.parseInt(format.format(calendar.getTime()))) {
                Set<Smlouva> collection = w.getSmlouvyHistorie().keySet();
                for (Smlouva b : collection) {
                    if (w.getSmlouvyHistorie().get(b)) {
                        w.test(b);
                    }
                }

            }
            try {


                w.setSmlouvyHistorie(krmn.wendSmlouva((ArrayList<String>) titles, (ArrayList<String>) podminky, krmn.polePut(krmn.poleConverter(krmn.poleConverter(krmn.polepull((ArrayList) zkusenost)))), krmn.booleanconverter(booleans)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (place.equals("default")) {
            switch (s) {
                case "work":
                    final String[] jmena = {"1", "2", "3", "4", "showContracts"};
                    window(new method.onmet() {
                        @Override
                        public void methoda(String[] string) {
                            try {
                                w.goWork(Integer.parseInt(string[0]));
                            } catch (NumberFormatException e) {
                                if (string[0].equals("showContracts")) {
                                    Intent intent = new Intent(getApplicationContext(), showHashMap_activity.class);
                                    int i = 1;
                                    for (Smlouva s : w.getSmlouvy()) {
                                        String S = s.getTitle();
                                        String s1 = String.valueOf(i);
                                        intent.putExtra("title" + s1, S);
                                        String Sa = s.getPodminky();
                                        intent.putExtra("podminky" + s1, Sa);
                                        intent.putExtra("zkusenost" + s1, s.getZkusenost());
                                        i++;
                                    }
                                    i = 1;
                                    for (Smlouva s : w.getSmlouvy()) {
                                        intent.putExtra("booleans" + i, w.getSmlouvyHistorie().get(s));
                                        i++;
                                    }
                                    intent.putExtra(showHashMap_activity.WHICH, "smlouva");
                                    startActivity(intent);
                                }
                            }
                        }
                    }, jmena);

                    break;
                case ("apply"):
                    w.apply();
                case ("home"):
                    if (itemshave.contains("house")) {
                        place = "house";
                    }
                    break;
                case ("off"):
                    System.exit(0);
                    break;
                case ("rob"):
                    int r = ThreadLocalRandom.current().nextInt(1, 4 + 1);
                    if (r > (basicskills.get("strength") + basicskills.get("agility") + basicskills.get("luck"))) {
                        out.setText(R.string.note1);
                        place = "prison";

                        alcatraz = new Prison(this);
                        alcatraz.prison(20);
                        nms = alcatraz.prisonNames;
                    } else {
                        money = money + ThreadLocalRandom.current().nextInt(1, 50 + 1);
                        out.setText(R.string.note2);
                    }
                    break;
                case ("help"):
                    out.setText(R.string.help);
                    break;
                case ("extend"):
                    bonus.setVisibility(View.VISIBLE);
                    break;
                case ("reduce"):
                    bonus.setVisibility(View.GONE);
                    break;
                case ("shopping"):
                    buy(editor);
                    break;


                case ("shitems"):
                    Intent i = new Intent(this, showArray_activity.class);
                    i.putExtra(showArray_activity.DATA, krmn.poleConverter(krmn.polepull(itemshave)));
                    startActivity(i);
                case ("reset"):
                    editor.clear();
                    editor.commit();


            }

        }

        if (dat3.equals("death")) {
            editor.clear();
            editor.commit();
            System.exit(0);
            dat3 = "";
        }
        if (place.equals("prison")) {
            alcatraz.prison();
            Calendar kalendar;


            out = findViewById(R.id.output);
            out.setText(R.string.note1);
            input = findViewById(R.id.in);

            dat2 = input.getText().toString();
            if (dat2.equals("shprnames")) {
                Intent intent = new Intent(this, showArray_activity.class);
                intent.putExtra(showArray_activity.DATA, krmn.poleConverter(krmn.polepull(alcatraz.prisonNames)));
                startActivity(intent);
            }
            kalendar = Calendar.getInstance();
            @SuppressLint("SimpleDateFormat") DateFormat formatData = new SimpleDateFormat("H");
            String dat4 = formatData.format(kalendar.getTime());
            int dat5 = Integer.parseInt(dat4);
            switch (dat2) {
                case ("escape"):
                    try {


                        if (basicskills.get("luck") + basicskills.get("agility") + basicskills.get("strength") > ThreadLocalRandom.current().nextInt(1, 4 + 1)) {
                            out.setText(R.string.note5);
                            place = "default";
                        } else {
                            alcatraz.solitary(8);
                        }
                        break;

                    } catch (NullPointerException err) {
                        err.printStackTrace();
                    }
                case ("program"):
                    if (5 < dat5 && dat5 < 12) {


                        String[] acte = {"kill"};
                        final String[] selectedText = new String[1];
                        final String[] jmena = acte;
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setItems(jmena, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                selectedText[0] = jmena[item];
                                String ret = selectedText[0];
                                if (ret.equals("kill")) {
                                    windowkill();
                                }
                            }
                        });

                        AlertDialog alertDialogObject = builder.create();
                        alertDialogObject.show();


                    }
                    if (12 < dat5 && dat5 < 20) {

                        String[] acte = {"kill"};
                        final String[] selectedText = new String[1];
                        final String[] jmena = acte;
                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                        builder.setItems(jmena, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int item) {
                                selectedText[0] = jmena[item];
                                String ret = selectedText[0];
                                if (ret.equals("kill")) {
                                    windowkill();
                                }
                            }
                        });

                        AlertDialog alertDialogObject = builder.create();
                        alertDialogObject.show();

                    }
            }
            r.prison();
        }
        if (place.equals("house")) {
            dat2 = input.getText().toString();
            switch (dat2) {
                case "out":
                    place = "default";
                case "sleep":
                    energy = 100;
            }

        }
        r.house();
        if (energy > 100) {
            energy = 100;
            out.setText("max is 100");
        }
    }

    public void shskills() {
        Intent intent = new Intent(this, showHashMap_activity.class);
        intent.putExtra(showHashMap_activity.CALL, basicskills);
        startActivity(intent);
    }
}