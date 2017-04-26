package com.sofittech.simpleflashlight;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;


public class Settings extends AppCompatActivity {

    CheckBox checkBox;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        checkBox=(CheckBox)findViewById(R.id.checkBox);
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);

        (findViewById(R.id.back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Settings.super.onBackPressed();
            }
        });
        checkBox.setChecked(sharedPreferences.getBoolean("Sound",true));
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBox.isChecked()){
                    editor=sharedPreferences.edit();
                    editor.putBoolean("Sound",true);
                    editor.commit();
                }
                else {

                    editor=sharedPreferences.edit();
                    editor.putBoolean("Sound",false);
                    editor.commit();

                }
            }
        });

    }
}
