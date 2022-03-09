package com.example.albertotarea12_pmdm;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ActivityMenu extends AppCompatActivity {
    Button butread, butmod, butcreate;
    private static boolean sdAvaliable = false;
    private static boolean sdWriteAcces = false;
    final String[] txt = {"no furruca"};
    private static String name;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        butcreate = findViewById(R.id.butcrear);
        butread = findViewById(R.id.butleer);

        checkpermissions();
        checkmemorystate();

        Intent tname = getIntent();
        if(tname.getExtras() != null)
        {
            name = tname.getStringExtra("name");
        }

        butcreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(ActivityMenu.this,ActivityCreate.class);
                t.putExtra("mode","create");
                startActivity(t);
                finish();


            }
        });
        butread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent t = new Intent(ActivityMenu.this,ActivityRead.class);
                startActivity(t);
                finish();
            }
        });





    }

    private void checkmemorystate() {
        String state = Environment.getExternalStorageState();
        if(state.equals(Environment.MEDIA_MOUNTED))
        {
            sdAvaliable = true;
            sdWriteAcces = true;

        }else if(state.equals(Environment.MEDIA_MOUNTED_READ_ONLY))
        {
            sdAvaliable = true;
            sdWriteAcces = false;

        }
        else
        {
            sdAvaliable = false;
            sdWriteAcces = false;
        }





    }
    private void Help()
    {

            if(sdWriteAcces && sdAvaliable){
            File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS+"/Tarea12PMDM/");
            File doc = new File(file + "/fichero.txt");
            try {
                if(!file.exists()) file.mkdir();
                if(!doc.exists()) doc.createNewFile();

                OutputStreamWriter os = new OutputStreamWriter(new FileOutputStream(doc));
                os.write("ESTO ES UNA PRUEBA");
                os.close();

                BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(doc)));
                txt[0] = bf.readLine();
                Toast.makeText(ActivityMenu.this, txt[0],Toast.LENGTH_SHORT).show();

                bf.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else Toast.makeText(getApplicationContext(),"no furruca",Toast.LENGTH_SHORT).show();
    }

    private void checkpermissions() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    ActivityCompat.requestPermissions(ActivityMenu.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},101);

                }
            });
            t.start();
            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    ActivityCompat.requestPermissions(ActivityMenu.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},101);

                }
            });
            t2.start();
        }
    }
}
