package com.example.lab6;

import android.os.Bundle;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import androidx.annotation.Nullable;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    EditText userName;
    EditText password;

    TextView loginSuccess;

    public LoginActivity(){}
    public LoginActivity(Context context){}


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin=findViewById(R.id.btnLogin);
        userName=findViewById(R.id.edtUsername);
        password=findViewById(R.id.edtPassword);
        loginSuccess=findViewById(R.id.loginTxt);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String result = validate(
                        userName.getText().toString(),
                        password.getText().toString()
                );

                loginSuccess.setText(result);
                loginSuccess.setVisibility(View.VISIBLE);
            }
        });
    }
    public String validate(String userName, String password)
    {
        if(userName.equals("admin") && password.equals("admin"))
            return "Login was successful";
        else
            return "Invalid login!";
    }
}
