package com.dehaat.dehaatassignment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.dehaat.dehaatassignment.R;
import com.dehaat.dehaatassignment.util.SharedPrefsUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_logout, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menu_item) {
            SharedPrefsUtils.clearAllSharedPref(MainActivity.this);
            finish();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
