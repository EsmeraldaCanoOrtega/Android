package com.example.personalproject.core.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import com.example.personalproject.R;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class ActivityLogin extends AppCompatActivity {

    private FirebaseAuth auth;


    private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
            new FirebaseAuthUIActivityResultContract(), this::onSignInResult);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setEvents();

        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() == null) {
            signInLauncher.launch(getSingInIntent());
        } else {
            loadMainActivity(auth.getCurrentUser());
        }
    }

    private void setEvents() {
    }

    private Intent getSingInIntent() {
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.GoogleBuilder().build(),
                new AuthUI.IdpConfig.PhoneBuilder().build());

        return AuthUI.getInstance(auth.getApp())
                .createSignInIntentBuilder()
                .setLogo(R.drawable.app_icon_nbg)
                .setTheme(R.style.CustomAppBarTheme)
                .setIsSmartLockEnabled(false)
                .setAvailableProviders(providers)
                .build();
    }

    private void onSignInResult(FirebaseAuthUIAuthenticationResult result) {
        try {
            IdpResponse response = result.getIdpResponse();
            if (result.getResultCode() == RESULT_OK) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null)
                    loadMainActivity(user);
                else
                    Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Sa muerto xD", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }


    private void loadMainActivity(FirebaseUser user) {
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("user_name", user.getDisplayName());
        i.putExtra("user_picture", user.getPhotoUrl());
        startActivity(i);
    }
}