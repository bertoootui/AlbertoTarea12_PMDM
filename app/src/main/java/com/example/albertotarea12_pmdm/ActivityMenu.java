package com.example.albertotarea12_pmdm;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.File;

public class ActivityMenu extends AppCompatActivity {
    Button butsave, butmod, butcreate;
    private static boolean sdAvaliable = false;
    private static boolean sdWriteAcces = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        butcreate = findViewById(R.id.butcrear);

        checkpermissions();
        checkmemorystate();

        butcreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sdWriteAcces && sdAvaliable)
                {
                    File ruta = Environment.getDataDirectory();
                    File file = new File(ruta.getAbsolutePath());

                }
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
