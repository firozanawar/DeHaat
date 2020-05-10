package com.dehaat.dehaatassignment.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.dehaat.dehaatassignment.R;
import com.dehaat.dehaatassignment.util.Constant;
import com.dehaat.dehaatassignment.util.SharedPrefsUtils;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (getSupportActionBar() != null)
            getSupportActionBar().hide();

        boolean isSign = SharedPrefsUtils.getBooleanPreference(this, Constant.SIGNIN, false);
        sendIntent(isSign ? MainActivity.class : LoginActivity.class);
    }

    private void sendIntent(Class cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
        finish();
    }
}
