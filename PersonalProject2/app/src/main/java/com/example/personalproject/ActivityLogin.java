package com.example.personalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class ActivityLogin extends AppCompatActivity {

    private Button button_login;
    private EditText edittext_name;
    private EditText edittext_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button_login = findViewById(R.id.button_login);
        edittext_name = findViewById(R.id.edittext_name);
        edittext_password = findViewById(R.id.edittext_password);
        setEvents();
    }

    private void setEvents() {
        button_login.setOnClickListener(view -> checkLogin());
    }

    private void checkLogin() {
        String nicename = "alberto";
        String nicepassword = "alberto";
        String name = edittext_name.getText().toString();
        String password = edittext_name.getText().toString();

        //check a base de datos con estos parametros
        if (name.equals(nicename) && password.equals(nicepassword)) {
            loadMainActivity();
        } else {
            clear();
            Toast.makeText(this, R.string.login_incorrect_message, Toast.LENGTH_SHORT).show();
        }
        //
    }

    private void loadMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        clear();
    }

    private void clear(){
        edittext_name.setText("");
        edittext_password.setText("");
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(Objects.requireNonNull(getCurrentFocus()).getWindowToken(), 0);
    }
}