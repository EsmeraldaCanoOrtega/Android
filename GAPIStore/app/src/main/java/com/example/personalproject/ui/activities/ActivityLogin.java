package com.example.personalproject.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
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
    /**Crea un ActivityResultLauncher que registre una devolución de llamada
     para el contrato de resultados de Actividad de FirebaseUI:*/
    private final ActivityResultLauncher<Intent> signInLauncher = registerForActivityResult(
            new FirebaseAuthUIActivityResultContract(), this::onSignInResult);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() == null) {
            signInLauncher.launch(getSingInIntent());
        } else {
            loadMainActivity(auth.getCurrentUser());
        }

    }
/**Metodo que devuelve un intent para iniciar el flujo de acceso de FirebaseUI,
 * configurado a nuestra preferencia*/
    private Intent getSingInIntent() {
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.GoogleBuilder().build(),
                new AuthUI.IdpConfig.PhoneBuilder().build());

        return AuthUI.getInstance(auth.getApp())
                .createSignInIntentBuilder()
                .setLogo(R.drawable.app_icon_nbg)
                //https://stackoverflow.com/questions/24742732/android-studio-action-bar-remove
                // Porque me aparecia una action bar morado con el nombre de mi aplicacion
                .setTheme(R.style.CustomAppBarTheme)
                .setIsSmartLockEnabled(false)
                .setAvailableProviders(providers)
                .build();
    }
    /**Cuando se complete el flujo de acceso, recibirás el resultado en onSignInResult:*/
    private void onSignInResult(FirebaseAuthUIAuthenticationResult result) {
        try {
            if (result.getResultCode() == RESULT_OK) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    loadMainActivity(user);
                }else
                    Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    /**Aqui como en la practica m08 he metido en el bundle o en lote estos datos*/
    private void loadMainActivity(FirebaseUser user) {
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("user_name", user.getDisplayName());
        i.putExtra("user_picture", user.getPhotoUrl());
        startActivity(i);
    }
}