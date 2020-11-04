package com.sin.lifesim;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.sin.lifesim.Item.Item;
import com.sin.lifesim.Item.ItemExtended;
import com.sin.lifesim.Item.ItemTool;
import com.sin.lifesim.Item.ItemWeapon;
import com.sin.lifesim.Item.computer.ComputerComponent;
import com.sin.lifesim.entity.DataClass;
import com.sin.lifesim.entity.Effect;
import com.sin.lifesim.entity.Entity;
import com.sin.lifesim.entity.EntityRender;
import com.sin.lifesim.entity.Me;
import com.sin.lifesim.interfaces.method;
import com.sin.lifesim.interfaces.stream;
import com.sin.lifesim.school.School;
import com.sin.lifesim.school.schools.KomensSchool;
import com.sin.lifesim.school.schools.PragueGymnasiumSchool;
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
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;


@SuppressWarnings({"CanBeFinal", "EmptyMethod", "AccessStaticViaInstance", "ResultOfMethodCallIgnored", "unchecked"})
public class MainActivity extends AppCompatActivity {
    //view and string variables
    EditText input;
    Button button;
    String dat2;
    String dat3 = "";
    String place = "default";
    Effect bleeding = new Effect(new method() {
        @Override
        public void effect(Entity entity) {
            entity.hp = entity.hp - 10;
        }
    }, "bleeding");
    Item[] itsellPrepare = {new ItemExtended("fries", 10, null, 15), new ItemTool("screwdriver", 20), new ItemWeapon(100, bleeding, 20, "gun", 100), new ItemWeapon(150, bleeding, 30, "knife", 60), new ItemWeapon(50, null, 50, "baton", 30), new ItemExtended("pancakes", 20, null, 50), new Item("house", false, 200), new ItemExtended("milk", 50, new Effect(new method() {
        @Override
        public void effect(Entity entity) {
            entity.effects.clear();
        }
    }, "clear"), 0), new ComputerComponent("cpu", 50, 1), new ComputerComponent("ram", 50, 1), new ComputerComponent("screen", 50, 1), new ComputerComponent("keyboard", 25, 1), new ComputerComponent("mouse", 25, 1), new ComputerComponent("net", 15, 1)};
    public String SchoolName;
    TextView out;
    String randomBlocker = "";
    //int variables
    public int money = 100;
    int energy;
    //array lists and HashMaps
    HashMap<String, Integer> basicskills = new HashMap<String, Integer>();
    ArrayList<String> skills = new ArrayList<String>();
    public ArrayList<Item> itemshave = new ArrayList<>();
    public ArrayList<String> courses = new ArrayList<>();
    public ArrayList<String> schools = new ArrayList<>();
    ArrayList<String> nms = new ArrayList<String>();
    public ArrayList<Item> itemssell = new ArrayList<>();
    //other
    Krmic krmn;
    public EntityRender render;
    DataClass effectRenderData;
    Prison alcatraz;
    randomEvents r;
    work w;
    Window window;
    public SharedPreferences.Editor editor;
    public static final int STORAGE_REQUEST_CODE = 101;
    public static final String PATH = "storage/emulated/0/school";
    public static final String PATH_SCHOOLS = "storage/emulated/0/schools";
    public School school;
    public Me me;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestPerm(Manifest.permission.WRITE_EXTERNAL_STORAGE, STORAGE_REQUEST_CODE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        krmn = new Krmic();
        me = new Me("me", ThreadLocalRandom.current().nextInt(0, 100));
        render = new EntityRender();
        render.renderEntity(me);
        effectRenderData = new DataClass();
        itemssell.addAll(Arrays.asList(itsellPrepare));
        basicskills.put("strength", 1);
        basicskills.put("agility", 1);
        basicskills.put("luck", 1);
        getResources().getString(R.string.baton);
        ArrayList<String> ita;
        ArrayList<Integer> itB;
        w = new work(this, getApplication());
        w.setZamestnani(work.mistaPrepare[0]);
        window = new Window(this);
        r = new randomEvents(this);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            try {
                click(new View(getApplication()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            window.informationDialog(getString(R.string.initializationerror));
        }

    }

    public void windowkill() {
        alcatraz.kill();
    }

    @Override
    protected void onDestroy() {
        Calendar calendar = Calendar.getInstance();
        w.getZamestnani().saver.save();
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

    public void buy(final SharedPreferences.Editor editor) {
        final String[] selectedText = new String[1];
        final String[] jmena;
        ArrayList<String> items = new ArrayList<>();
        for (Item item : itemssell)
            items.add(item.name);
        jmena = Krmic.poleConverter(Krmic.polepull(items));
        final String[] ret = new String[1];
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("what you want to buy?");
        builder.setItems(jmena, new DialogInterface.OnClickListener() {
            @SuppressLint("SetTextI18n")
            public void onClick(DialogInterface dialog, int itemr) {
                selectedText[0] = jmena[itemr];
                ret[0] = selectedText[0];
                String item = ret[0];
                int cena = itemssell.get(itemr).price;
                if (money > cena) {
                    money = money - cena;
                    render.renderItem(itemssell.get(itemr), me);
                    editor.putInt("money", money);
                    out.setText("úspěšně jste si koupil/a" + " " + item + " za" + " " + cena);
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

    private void SaveSchools() {
        try {
            schools = (ArrayList<String>) Krmic.objectSaveHandler(new stream() {
                @Override
                public FileInputStream input() {
                    return null;
                }

                @Override
                public FileOutputStream output() throws IOException {
                    return new FileOutputStream(new File(PATH_SCHOOLS));
                }
            }, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings({"unchecked"})
    @SuppressLint({"ApplySharedPref", "SetTextI18n"})
    public void click(View view) throws IOException {
        try {
            if (render.render.getActive()) {
                effectRenderData.clicked = true;
            }
        } catch (NullPointerException ignored) {

        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            throw new IllegalAccessError("don't click button without permission");
        }
        input = findViewById(R.id.in);
        dat2 = input.getText().toString();
        if (dat2.equals("shskills")) {
            shskills();
        }


        out = findViewById(R.id.output);

        button = findViewById(R.id.butt);
        SharedPreferences data = getPreferences(MODE_PRIVATE);
        editor = data.edit();


        if (!data.getString("started", "ne").equals("ano")) {
            money = 100;
            out.setText(R.string.tutorial);
            editor.putString("started", "ano");
            editor.putInt("agility", basicskills.get("agility"));
            editor.putInt("strength", basicskills.get("strength"));
            editor.putInt("luck", basicskills.get("luck"));
            editor.putInt("money", money);
            editor.putString("place", place);
            w.first();
            editor.putInt("worked", w.worked);

            Intent i = getIntent();

            final File file = new File("storage/emulated/0/lifesim/smlouvy");
            try {
                File dir = new File("storage/emulated/0/lifesim");
                dir.mkdir();
                File file1 = new File(PATH);
                file1.createNewFile();
                File file2 = new File(work.PATH);
                file2.createNewFile();
                File file3 = new File(w.applyPath);
                file3.createNewFile();
                File file4 = new File(PATH_SCHOOLS);
                file4.createNewFile();
                File zamestnani = new File("storage/emulated/0/lifesim/zamestnani");
                zamestnani.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
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
            String place = this.place = data.getString("place", null);
            Calendar calendar = Calendar.getInstance();
            DateFormat format = new SimpleDateFormat("DD");
            w.worked = data.getInt("worked", -1);
            SaveSchools();
            w.normal();
            loadSchool();
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
            ArrayList<Smlouva> smlouva = w.getSmlouvyForApply();
            Smlouva smlouva1 = w.generateSmlouvaForApply();
            if (smlouva1 != null) {
                smlouva.add(smlouva1);
            }
            w.setSmlouvyForApply(smlouva);
            switch (input.getText().toString()) {
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
                    break;
                case ("school"):
                    if (school == null) {

                        SchoolName = School.generate(this);
                        switch (SchoolName) {
                            case "komens":
                                window.informationDialog("you are now in low school");
                            case "prague":
                                window.informationDialog("you are now in medium school");
                        }

                    } else {
                        switch (SchoolName) {
                            case ("komens"):
                                KomensSchool school1 = (KomensSchool) school;
                                school1.study(this);
                                break;
                            case ("prague"):
                                PragueGymnasiumSchool school = (PragueGymnasiumSchool) this.school;
                                break;
                            default:
                                throw new UnsupportedOperationException("this school does not exist");
                        }
                    }
                    break;
                case ("home"):
                    boolean test;
                    for (Item item : me.items) {
                        if (item.name.equals("house")) {
                            place = "house";
                            break;
                        }
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

                case ("consume"):
                    ArrayList<String> itemsPrepare = new ArrayList<>();
                    for (Item item : me.items)
                        itemsPrepare.add(item.name);
                    window.windowItems(new method.onmet() {
                        @Override
                        public void methoda(String[] string) {
                            for (Item item : me.items) {
                                if (item.name.equals(string[0])) {
                                    ItemExtended item1;
                                    try {
                                        item1 = (ItemExtended) item;
                                    } catch (ClassCastException e) {
                                        out.setText("this item cannot be eaten");
                                        return;
                                    }
                                    item1.OnConsumeEffect(render, me, effectRenderData);
                                    render.renderRemoveItem(item, me);
                                    out.setText("you ate " + item1.name);
                                    break;
                                }
                            }
                        }
                    }, Krmic.poleConverter(Krmic.polepull(itemsPrepare)));
                    break;
                case ("shitems"):
                    Intent i = new Intent(this, showArray_activity.class);
                    ArrayList<String> itemshave = new ArrayList<>();
                    for (Item item : me.items) {
                        itemshave.add(item.name);
                    }
                    i.putExtra(showArray_activity.DATA, krmn.poleConverter(krmn.polepull(itemshave)));
                    startActivity(i);
                    break;
                case ("reset"):
                    editor.clear();
                    editor.commit();
                    break;

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
                ArrayList<String> nms = new ArrayList<>();
                for (Prisoner prisoner : alcatraz.vezni) {
                    nms.add(prisoner.name);
                }
                intent.putExtra(showArray_activity.DATA, krmn.poleConverter(krmn.polepull(nms)));
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
            r.house();
        }

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

    public void saveSchool() {
        try {
            Krmic.objectSaveHandler(new stream() {
                @Override
                public FileInputStream input() {
                    return null;
                }

                @Override
                public FileOutputStream output() {
                    try {
                        return new FileOutputStream(new File(PATH));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            }, school);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadSchool() {
        if (school != null) {
            try {
                Krmic.objectSaveHandler(new stream() {
                    @Override
                    public FileInputStream input() throws FileNotFoundException {
                        return new FileInputStream(new File(PATH));
                    }

                    @Override
                    public FileOutputStream output() {
                        return null;
                    }
                }, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                w.getSmlouvyHistorie().put((Smlouva) data.getSerializableExtra("smlouva"), true);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}