package com.saami.app.projects.form.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;
import com.saami.app.projects.form.HomeActivity;
import com.saami.app.projects.form.R;
import com.saami.app.projects.form.SharedPrefManager;
import com.saami.app.projects.form.connection.Client;
import com.saami.app.projects.form.connection.Service;
import com.saami.app.projects.form.model.login.LoginResponse;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    TextInputLayout etEmail, etPassword;
    Button btnLogin;
    Context mContext = this;
    ProgressDialog loading;
    SharedPrefManager sharedPrefManager;
    TextView register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = this;
        sharedPrefManager = new SharedPrefManager(this);
        String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        Permissions.check(this/*context*/, permissions, null/*rationale*/, null/*options*/, new PermissionHandler() {
            @Override
            public void onGranted() {
                // do your task.
            }
        });
        register = findViewById(R.id.txtRegister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        initComponents();
    }
    private void initComponents(){
        etEmail = findViewById(R.id.etEmail);
        etPassword =  findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateEmail() | !validatePassword()) {
                    return;
                }
                loading = ProgressDialog.show(mContext, null, "Harap Tunggu...", true, false);
                requestLogin();


            }
        });

    }
    private boolean validateEmail() {
        String email = (etEmail.getEditText()).getText().toString().trim();

        if (email.isEmpty()) {
            etEmail.setError("Email tidak boleh kosong");
            return false;
        }  else {
            etEmail.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String pw = etPassword.getEditText().getText().toString().trim();

        if (pw.isEmpty()) {
            etPassword.setError("Password tidak boleh kosong");
            return false;
        } else {
            etPassword.setError(null);
            return true;
        }
    }
    private void requestLogin(){
        Client.getClient().create(Service.class).loginRequest(Objects.requireNonNull(etEmail.getEditText()).getText().toString(), etPassword.getEditText().getText().toString())
                .enqueue(new Callback<LoginResponse>()
                {

                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.isSuccessful()){
                            loading.dismiss();
                            LoginResponse loginResponse = response.body();
                            Log.d("tes", loginResponse.getData().getToken());

                            sharedPrefManager.saveToken(SharedPrefManager.SP_TOKEN,loginResponse.getData().getToken());
                            sharedPrefManager.getSpToken();
                            Log.d("token", sharedPrefManager.getSpToken());


//                            sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class)
                                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        } else {
                            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(mContext);

                            dlgAlert.setMessage("Mohon Periksa Kembali");
                            dlgAlert.setTitle("Email Atau Password Anda Salah");
                            dlgAlert.setPositiveButton("OK", null);
                            dlgAlert.setCancelable(false);
                            dlgAlert.create().show();


                            dlgAlert.setPositiveButton("Ok",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    });
                            loading.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.toString());
                        loading.dismiss();
                    }
                });
    }
}
