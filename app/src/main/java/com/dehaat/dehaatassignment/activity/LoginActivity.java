package com.dehaat.dehaatassignment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.dehaat.dehaatassignment.R;
import com.dehaat.dehaatassignment.util.Constant;
import com.dehaat.dehaatassignment.util.Utils;
import com.dehaat.dehaatassignment.viewmodel.LoginViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.email)
    public EditText email;
    @BindView(R.id.password)
    public EditText password;
    @BindView(R.id.login)
    public Button login;
    @BindView(R.id.progressbar)
    public ProgressBar pb;

    private LoginViewModel loginViewModel;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        unbinder = ButterKnife.bind(this);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        loginViewModel.responseMutableLiveData.observe(this, myResponse -> {
            if (myResponse.code == Constant.CODE_SUCCESS) {
                sendIntent(MainActivity.class);
            } else {
                Toast.makeText(LoginActivity.this, myResponse.message, Toast.LENGTH_SHORT).show();
            }
        });
        loginViewModel.email.observe(this, validator -> {
            if (validator.error)
                email.setError(validator.msg);
        });
        loginViewModel.password.observe(this, validator -> {
            if (validator.error)
                password.setError(validator.msg);
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.isNetworkConnected(LoginActivity.this)) {
                    loginViewModel.login(email.getText().toString(), password.getText().toString());
                } else {
                    Toast.makeText(LoginActivity.this, "Please check the internet connectivity", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void sendIntent(Class cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) unbinder.unbind();
    }
}
