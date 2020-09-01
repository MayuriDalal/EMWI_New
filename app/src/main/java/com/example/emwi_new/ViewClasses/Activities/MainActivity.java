package com.example.emwi_new.ViewClasses.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.emwi_new.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout iv_get_started;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        initializeView();
    }

    private void initializeView() {
        iv_get_started = findViewById(R.id.iv_get_started);
        iv_get_started.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_get_started:
                Intent intent = new Intent(this, LoginActivity.class );
                startActivity(intent);
        }
    }
}