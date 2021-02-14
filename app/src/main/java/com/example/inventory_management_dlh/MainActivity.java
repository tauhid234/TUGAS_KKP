package com.example.inventory_management_dlh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    private int waktu = 4000;
    ImageView gif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gif = (ImageView)findViewById(R.id.box);
        Glide.with(MainActivity.this)
                .load(R.drawable.box)
                .into(gif);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent login = new Intent(MainActivity.this, login.class);
                startActivity(login);
                finish();
            }
        }, waktu);
    }
}