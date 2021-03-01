package com.example.printful;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.printful.homescreen.HomeScreen;
import com.example.printful.onboarding.MainActivity;

public class SplashScreen extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                sharedPreferences = getSharedPreferences("MySharedPrefs", MODE_PRIVATE);
                String token = (sharedPreferences.getString("AccessToken", ""));
                if (token.isEmpty()) {
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(SplashScreen.this, HomeScreen.class);
                    startActivity(intent);
                    finish();
                }

            }
        },3000);
    }
}