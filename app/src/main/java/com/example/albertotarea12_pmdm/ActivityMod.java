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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ActivityMod extends AppCompatActivity {
    FloatingActionButton butread, butsave, butback, butpath;
    EditText txtread, txtname;
    private static String name;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mod);

        butread = findViewById(R.id.butreadmod);
        butsave = findViewById(R.id.butsavemod);
        butback = findViewById(R.id.butbackmod);
        txtname = findViewById(R.id.txtnamemod);
        txtread = findViewById(R.id.txtreadmod);
        butpath = findViewById(R.id.butpahtmod);




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
                Intent t = new Intent(ActivityMod.this,ActivityMenu.class);
                startActivity(t);
                finish();
            }
        });


        butsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = txtread.getText().toString();
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
                        Toast.makeText(getApplicationContext(),"Se ha guardado el archivo",Toast.LENGTH_SHORT).show();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }//end else
            }
        });



        butread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = "";
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
