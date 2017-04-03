package com.sofittech.flashlight;

import android.hardware.Camera;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.content.pm.PackageManager;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView imageView,imageView2;
    LinearLayout linearLayout;
    Camera camera;
    Camera.Parameters on,off;
    boolean state = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout=(LinearLayout)findViewById(R.id.lineee);
        imageView = (ImageView) findViewById(R.id.onbtn);
        imageView2=(ImageView)findViewById(R.id.onbtn2);
        if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
            camera = Camera.open();
            ( on=camera.getParameters() ).setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            (off=camera.getParameters()).setFlashMode(Camera.Parameters.FLASH_MODE_OFF);

        }else{
            Toast.makeText(MainActivity.this,"No flash Light on your phone",Toast.LENGTH_SHORT).show();
            linearLayout.setClickable(false);
        }


        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!state) {
                    imageView.setVisibility(View.GONE);
                    camera.setParameters(on);


                } else {
                    camera.setParameters(off);
                    imageView.setVisibility(View.VISIBLE);
                }
                state=!state;
            }
        });
    }

}