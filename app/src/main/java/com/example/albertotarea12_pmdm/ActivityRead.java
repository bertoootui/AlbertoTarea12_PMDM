package com.example.albertotarea12_pmdm;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class ActivityRead extends AppCompatActivity {
    FloatingActionButton butback, butread, butpath;
    TextView txtread;
    EditText txtname;
    private static String name;
    private static String text ="ggggggg";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        butread = findViewById(R.id.butread);
        butback = findViewById(R.id.butbackread);
        txtname = findViewById(R.id.txtname2);
        txtread = findViewById(R.id.txtread);
        butpath = findViewById(R.id.butpahtread);






        butpath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (name.equals("")) {
                    Toast.makeText(getApplicationContext(), "El campo del nombre del archivo no puede estar vacío", Toast.LENGTH_SHORT).show();
                } else {
                    File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS + "/Tarea12PMDM/");
                    File doc = new File(file + "/" + name + ".txt");
                    if (!doc.exists()) {
                        Toast.makeText(getApplicationContext(), "No se puede encontrar el archivo especificado", Toast.LENGTH_SHORT).show();
                    } else {


                        String path = doc.getAbsolutePath();
                        Toast.makeText(getApplicationContext(),"RUTA ARCHIVO: "+path,Toast.LENGTH_SHORT).show();


                    }

                }
            }

        });




        butback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent t = new Intent(ActivityRead.this,ActivityMenu.class);
                startActivity(t);
                finish();
            }
        });

        butread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = txtname.getText().toString();
                if(name.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"El campo del nombre del archivo no puede estar vacío",Toast.LENGTH_SHORT).show();
                }else {
                    File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS + "/Tarea12PMDM/");
                    File doc = new File(file + "/" + name + ".txt");
                    if(!doc.exists())
                    {
                        Toast.makeText(getApplicationContext(),"No se puede encontrar el archivo especificado",Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        try {
                            BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(doc)));
                            while((text = bf.readLine()) !=null)
                            {
                                txtread.append(text);
                            }
                            bf.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }


                }
            }
        });




    }
}
