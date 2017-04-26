package com.sofittech.simpleflashlight;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;

import android.hardware.Camera;
import android.media.MediaPlayer;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import android.view.View;

import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    ImageView offL,onnL;
    FrameLayout frameLayout;
    LinearLayout linearLayout;
    boolean state = false;
    Camera camera;
    Camera.Parameters on,off;
    MediaPlayer mp;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);

        offL=(ImageView)findViewById(R.id.offbtn);
        onnL=(ImageView)findViewById(R.id.onbtn);
        frameLayout=(FrameLayout)findViewById(R.id.RLayout);
        linearLayout=(LinearLayout)findViewById(R.id.Llayout);
        mp=MediaPlayer.create(getApplicationContext(), R.raw.tick);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mp.stop();
                mp.release();
                mp = null;
                mp=MediaPlayer.create(getApplicationContext(), R.raw.tick);

                    if (!state) {
                        offL.setVisibility(View.GONE);
                        camera.setParameters(on);
                    } else {
                        camera.setParameters(off);
                        offL.setVisibility(View.VISIBLE);
                    }

                    if(sharedPreferences.getBoolean("Sound",true)){
                        mp.start();
                    }else{

                    }
                    state=!state;

            }
        });


        (findViewById(R.id.settings)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity.this,Settings.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        camera.release();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
            camera = Camera.open();
            ( on=camera.getParameters() ).setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            (off=camera.getParameters()).setFlashMode(Camera.Parameters.FLASH_MODE_OFF);

        }else{
            Toast.makeText(MainActivity.this,"No flash Light on your phone",Toast.LENGTH_SHORT).show();
            frameLayout.setClickable(false);
        }
    }


}
