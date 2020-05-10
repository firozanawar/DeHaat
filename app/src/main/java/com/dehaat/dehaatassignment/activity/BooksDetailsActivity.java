package com.dehaat.dehaatassignment.activity;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.dehaat.dehaatassignment.R;
import com.dehaat.dehaatassignment.fragments.BookDetailsFragment;

public class BooksDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }

        if (savedInstanceState == null) {
            BookDetailsFragment bookFragment = new BookDetailsFragment();
            bookFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, bookFragment).commit();
        }
    }
}
