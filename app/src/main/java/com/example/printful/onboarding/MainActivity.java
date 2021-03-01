package com.example.printful.onboarding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.printful.R;
import com.example.printful.homescreen.HomeScreen;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout tabIndicator;
    int postion = 0;
    Button nextBtn,skip;
    ViewPager viewPager;
    int[] bacground = {R.drawable.movie1,R.drawable.movie3};
    String[] heading = {"Wanna See Movie", "Enjoy Your Movie"};
    String[] description = {
            "Want to watch movie and relax? but can't find a one. We Bring you set of all latest one movie at one platfrom",
            "Choose movie, see rating and enjoy your movie"
    };

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("MySharedPrefs", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("AccessToken", "true");
        myEdit.apply();
        tabIndicator = findViewById(R.id.tabIndicator);
        viewPager = findViewById(R.id.viewPager);
        Adapter adapter = new Adapter(MainActivity.this, bacground, heading, description);
        viewPager.setAdapter(adapter);
        tabIndicator.setupWithViewPager(viewPager);

        nextBtn = findViewById(R.id.nextBtn);
        skip=findViewById(R.id.btskip);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                postion = viewPager.getCurrentItem();
                if (postion < bacground.length) {
                    postion++;
                    viewPager.setCurrentItem(postion);
                }
                if (postion == 2) {
                    intentTomainScreen();
                }
                if(postion==bacground.length-1)
                {
                    nextBtn.setText("Start");
                }


            }


            private void intentTomainScreen() {

                Intent MainIntent = new Intent(MainActivity.this, HomeScreen.class);
                startActivity(MainIntent);
                finish();
            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent MainIntent = new Intent(MainActivity.this, HomeScreen.class);
                startActivity(MainIntent);
                finish();
            }
        });
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position==3)
                {
                    nextBtn.setText("Start");
                    skip.setVisibility(View.GONE);
                }
                else
                {
                    skip.setVisibility(View.VISIBLE);

                    nextBtn.setText("Next");
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}