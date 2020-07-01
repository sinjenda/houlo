package com.sin.lifesim.work.smlouva;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.sin.lifesim.Krmic;
import com.sin.lifesim.MainActivity;
import com.sin.lifesim.R;

import java.util.ArrayList;

@SuppressWarnings({"unused", "AccessStaticViaInstance"})
public class display_smlouva_activity extends AppCompatActivity {
    public static final String SMLOUVA = "";
    TextView podminky;
    TextView request;
    TextView title;
    Intent i;
    Krmic k = new Krmic();

    @Override
    @SuppressWarnings({"unchecked", "ConstantConditions"})
    protected void onCreate(Bundle savedInstanceState) {
        podminky = findViewById(R.id.podminky);
        request = findViewById(R.id.requirments);
        title = findViewById(R.id.title);
        i = getIntent();
        final ArrayList<Smlouva> smlouvy;
        smlouvy = (ArrayList<Smlouva>) i.getSerializableExtra(SMLOUVA);
        final Smlouva[] selectedText = new Smlouva[1];
        final String[] jmena = k.poleConverter(k.polepull(smlouvy));
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("choose contract");
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        builder.setItems(jmena, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                selectedText[0] = smlouvy.get(item);
                Smlouva ret = selectedText[0];
                podminky.setText(String.valueOf(ret.getPodminky()));
                request.setText(ret.getZkusenost());
                title.setText(ret.getTitle());
            }
        });

        AlertDialog alertDialogObject = builder.create();
        alertDialogObject.show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_smlouva);


    }
}