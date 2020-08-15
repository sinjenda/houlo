package com.sin.lifesim;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.sin.lifesim.work.smlouva.Smlouva;
import com.sin.lifesim.work.work;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;


@SuppressWarnings({"CanBeFinal", "EmptyMethod", "unused", "AccessStaticViaInstance", "ResultOfMethodCallIgnored"})
public class MainActivity extends AppCompatActivity {
    //view and string variables
    EditText input;
    Button button;
    String dat2;
    String dat3 = "";
    String place = "default";
    TextView out;
    String randomBlocker = "";
    //int variables
    public int money = 100;

    //array lists


    HashMap<String, Integer> itemssell = new HashMap<String, Integer>();
    HashMap<String, Integer> basicskills = new HashMap<String, Integer>();
    ArrayList<String> skills = new ArrayList<String>();
    public ArrayList<String> itemshave = new ArrayList<String>();
    ArrayList<String> nms = new ArrayList<String>();
    String[] itsella = {"fries", "screwdriver", "gun", "knife", "baton", "pancakes", "house"};

    //other
    Krmic krmn;
    Prison alcatraz;
    randomEvents r;
    work w;
    Window window;
    public SharedPreferences.Editor editor;
    int energy;
    public static final int STORAGE_REQUEST_CODE = 101;


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
        window = new Window(this);
        r = new randomEvents(this);
        click(new View(getApplication()));
        SQLiteDatabase db;
    }

    public void windowkill() {
        final String[] jmena = krmn.poleConverter(krmn.polepull(itemshave));
        window.windowItems(new method.onmet() {
            @Override
            public void methoda(String[] string) {
                alcatraz.kill(string[0]);
            }
        }, jmena);
    }

    @Override
    protected void onDestroy() {
        Calendar calendar = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") DateFormat formatData = new SimpleDateFormat("DD");
        editor.putInt("time", Integer.parseInt(formatData.format(calendar.getTime())));
        try {
            krmn.objectSaveHandler(new stream() {
                @Override
                public FileInputStream input() {
                    return null;
                }

                @Override
                public FileOutputStream output() throws IOException {
                    try {
                        File file = new File("storage/emulated/0/smlouvy");
                        return new FileOutputStream(file);
                    } catch (FileNotFoundException e) {
                        throw new IOException();
                    }
                }
            }, w.getSmlouvyHistorie());
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    @SuppressWarnings("ConstantConditions")
    public void buy(final SharedPreferences.Editor editor) {
        onDestroy();
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void requestPerm(String permType, int requestCode) {
        int perm = ContextCompat.checkSelfPermission(this, permType);

        if (perm != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{permType}, requestCode);

        }
    }

    @SuppressWarnings({"ConstantConditions", "unchecked"})
    @SuppressLint({"ApplySharedPref", "SetTextI18n"})
    public void click(View view) {
        input = findViewById(R.id.in);
        dat2 = input.getText().toString();
        if (dat2.equals("shskills")) {
            shskills();
        }
        requestPerm(Manifest.permission.WRITE_EXTERNAL_STORAGE, STORAGE_REQUEST_CODE);
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
            editor.putString("place", place);
            editor.putInt("worked", w.worked);
            w.first();
            Intent i = getIntent();

            final File file = new File("storage/emulated/0/smlouvy");
            try {
                file.createNewFile();
                krmn.objectSaveHandler(new stream() {
                    @Override
                    public FileInputStream input() {
                        return null;
                    }

                    @Override
                    public FileOutputStream output() throws IOException {
                        return new FileOutputStream(file);
                    }
                }, w.getSmlouvyHistorie());
            } catch (IOException e) {
                e.printStackTrace();
            }


            editor.putString("place", place);
            editor.commit();
        } else {
            basicskills.put("agility", data.getInt("agility", -1));
            basicskills.put("strength", data.getInt("strength", -1));
            basicskills.put("luck", -1);
            money = data.getInt("money", -1);
            w.setZamestnani(data.getString("workplace", null));
            String place = this.place = data.getString("place", null);
            Calendar calendar = Calendar.getInstance();
            DateFormat format = new SimpleDateFormat("DD");
            w.worked = data.getInt("worked", -1);
            if (data.getInt("time", -1) != Integer.parseInt(format.format(calendar.getTime()))) {
                Set<Smlouva> collection = w.getSmlouvyHistorie().keySet();
                for (Smlouva b : collection) {
                    if (w.getSmlouvyHistorie().get(b)) {
                        w.test(b);
                    }
                }

            }

        }
        try {
            w.setSmlouvyHistorie((HashMap<Smlouva, Boolean>) krmn.objectSaveHandler(new stream() {
                @Override
                public FileInputStream input() {
                    try {
                        return new FileInputStream(new File("storage/emulated/0/smlouvy"));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        return null;
                    }
                }

                @Override
                public FileOutputStream output() {
                    return null;
                }
            }, null));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (place.equals("default")) {
            switch (s) {
                case "work":
                    final String[] jmena = {"1", "2", "3", "4", "showContracts"};
                    window.windowItems(new method.onmet() {
                        @Override
                        public void methoda(String[] string) {
                            try {
                                w.goWork(Integer.parseInt(string[0]));
                            } catch (NumberFormatException e) {
                                if (string[0].equals("showContracts")) {
                                    Intent intent = new Intent(getApplicationContext(), showHashMap_activity.class);
                                    intent.putExtra("thing", "smlouvy");
                                    intent.putExtra(showHashMap_activity.WHICH, w.getSmlouvyHistorie());
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
        r.Default();
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
        intent.putExtra("thing", basicskills);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }
}