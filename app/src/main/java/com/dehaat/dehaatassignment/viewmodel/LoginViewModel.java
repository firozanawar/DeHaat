package com.dehaat.dehaatassignment.viewmodel;

import android.app.Application;
import android.text.TextUtils;
import android.util.Patterns;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.dehaat.dehaatassignment.R;
import com.dehaat.dehaatassignment.model.MyResponse;
import com.dehaat.dehaatassignment.model.Validator;
import com.dehaat.dehaatassignment.rest.AppRestClient;
import com.dehaat.dehaatassignment.rest.AppRestClientService;
import com.dehaat.dehaatassignment.util.Constant;
import com.dehaat.dehaatassignment.util.SharedPrefsUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends AndroidViewModel {

    private String TAG = LoginViewModel.class.getSimpleName();

    private Application application;
    public MutableLiveData<MyResponse> responseMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Validator> email = new MutableLiveData<>();
    public MutableLiveData<Validator> password = new MutableLiveData<>();

    public LoginViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }

    public void login(String emailId, String pwd) {

        if (!validation(emailId, pwd)) {
            AppRestClientService appRestClient = AppRestClient.getInstance().getRestClient();
            Call<MyResponse> response = appRestClient.login();

            response.enqueue(new Callback<MyResponse>() {
                @Override
                public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                    try {
                        SharedPrefsUtils.setBooleanPreference(application.getApplicationContext(), Constant.SIGNIN, true);
                        MyResponse myResponse = new MyResponse(Constant.CODE_SUCCESS, Constant.STATUS_SUCCESS, "");
                        responseMutableLiveData.setValue(myResponse);
                    } catch (Exception e) {
                        MyResponse myResponse = new MyResponse(Constant.CODE_ERROR, Constant.STATUS_ERROR, Constant.ERROR_MSG);
                        responseMutableLiveData.setValue(myResponse);
                    }
                }

                @Override
                public void onFailure(Call<MyResponse> call, Throwable t) {
                    SharedPrefsUtils.setBooleanPreference(application.getApplicationContext(), Constant.SIGNIN, true);
                    MyResponse myResponse = new MyResponse(Constant.CODE_ERROR, Constant.STATUS_ERROR, Constant.ERROR_MSG);
                    responseMutableLiveData.setValue(myResponse);
                }
            });
        }
    }

    private boolean validation(String emailId, String pwd) {
        Validator validator = new Validator(false);
        if (emailId.length() == 0) {
            validator.msg = application.getString(R.string.error);
            validator.error = true;
            email.setValue(validator);
        } else if (!isValidEmail(emailId)) {
            validator.msg = application.getString(R.string.invalid_email);
            validator.error = true;
            email.setValue(validator);
        } else if (pwd.length() == 0) {
            validator.msg = application.getString(R.string.error);
            validator.error = true;
            password.setValue(validator);
        } else if (pwd.length() < 6) {
            validator.msg = application.getString(R.string.invalid_password);
            validator.error = true;
            password.setValue(validator);
        }
        return validator.error;
    }

    private boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
