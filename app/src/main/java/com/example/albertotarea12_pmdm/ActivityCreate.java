package com.example.albertotarea12_pmdm;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class ActivityCreate extends AppCompatActivity {
    FloatingActionButton butsave, butback;
    EditText txttext, txtname;
    private static String name;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        butsave = findViewById(R.id.butsave);
        butback = findViewById(R.id.butback);
        txtname = findViewById(R.id.txtname);
        txttext = findViewById(R.id.txttext);

        butback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(ActivityCreate.this,ActivityMenu.class);
                t.putExtra("name",name);
                startActivity(t);
                finish();
            }
        });
        butsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = txttext.getText().toString();
                name = txtname.getText().toString();
                if(name.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"El campo del nombre del archivo no puede estar vacío",Toast.LENGTH_SHORT).show();
                }else {
                    File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS + "/Tarea12PMDM/");
                    File doc = new File(file + "/" + name + ".txt");
                    if (!file.exists()) file.mkdir();
                    if (!doc.exists()) {
                        try {
                            doc.createNewFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }//end if

                    OutputStreamWriter os = null;
                    try {
                        os = new OutputStreamWriter(new FileOutputStream(doc));
                        os.write(text);
                        os.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }//end else
            }
        });
    }
}
