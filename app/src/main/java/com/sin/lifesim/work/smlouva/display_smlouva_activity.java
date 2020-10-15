package com.sin.lifesim.work.smlouva;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.sin.lifesim.Krmic;
import com.sin.lifesim.MainActivity;
import com.sin.lifesim.R;

import java.util.ArrayList;

public class display_smlouva_activity extends AppCompatActivity {
    public static final String SMLOUVA = "";
    TextView podminky;
    TextView request;
    TextView title;
    Intent i;
    final Krmic k = new Krmic();
    Smlouva smlouvaToReturn;

    @Override
    @SuppressWarnings({"unchecked"})
    protected void onCreate(Bundle savedInstanceState) {
        podminky = findViewById(R.id.podminky);
        request = findViewById(R.id.requirments);
        title = findViewById(R.id.title);
        i = getIntent();
        final ArrayList<Smlouva> smlouvy;
        smlouvy = (ArrayList<Smlouva>) i.getSerializableExtra(SMLOUVA);
        final Smlouva[] selectedText = new Smlouva[1];
        final ArrayList<String> jmena = new ArrayList<>();
        for (Smlouva smlouva : smlouvy) {
            jmena.add(smlouva.getTitle());
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("choose contract");
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        builder.setItems(Krmic.poleConverter(Krmic.polepull(jmena)), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                //potential problem
                selectedText[0] = smlouvy.get(item);
                Smlouva ret = selectedText[0];
                podminky.setText(String.valueOf(ret.getPodminky()));
                request.setText(ret.getZkusenost());
                title.setText(ret.getTitle());
                smlouvaToReturn = ret;
            }
        });
        builder.setCancelable(false);
        AlertDialog alertDialogObject = builder.create();
        alertDialogObject.show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_smlouva);


    }

    public void clickDecline(View view) {
        Intent intent = new Intent(this, MainActivity.class);
    }

    public void clickAccept(View view) {
        finish();
    }

    @Override
    public void finish() {
        Intent intent = new Intent();
        intent.putExtra("smlouva", smlouvaToReturn);
        setResult(0, intent);
        super.finish();
    }
}