package com.pramedia.call;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.Permission;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtPhoneNumber;
    private Button btnCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtPhoneNumber = findViewById(R.id.txtPhoneNumber);

        btnCall = findViewById(R.id.btnCall);
        btnCall.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == btnCall){
            Intent intentcall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + txtPhoneNumber.getText().toString()));
            if(Build.VERSION.SDK_INT >= 23){
                if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{ Manifest.permission.CALL_PHONE}, 100);
                }else{
                    startActivity(intentcall);
                }
            }else{
                startActivity(intentcall);
            }
        }
    }
}