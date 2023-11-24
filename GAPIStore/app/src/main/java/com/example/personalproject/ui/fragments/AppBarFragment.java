package com.example.personalproject.ui.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.personalproject.R;
import com.example.personalproject.ui.activities.ActivityLogin;
import com.example.personalproject.utils.Rounded;
import com.firebase.ui.auth.AuthUI;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.Objects;

public class AppBarFragment extends Fragment {

    private Toolbar toolbar_app;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Indica que este fragmento tiene un menú de opciones
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Infla la vista del fragmento y configura la barra de herramientas
        View view = inflater.inflate(R.layout.app_bar, container, false);
        setUpToolbar(view);
        // Llena la barra de herramientas con la información del usuario
        fillLayout();
        return view;
    }

    private void setUpToolbar(View view) {
        // Configura la barra de herramientas y la establece como la barra de acciones de la actividad
        toolbar_app = view.findViewById(R.id.app_bar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar_app);
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater menuInflater) {
        // Infla el menú de la barra de herramientas
        menuInflater.inflate(R.menu.app_bar_menu, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Maneja las acciones del menú de la barra de herramientas
        if (item.getItemId() == R.id.app_bar_filterButton) {
            // Muestra un mensaje de "En desarrollo" al presionar el botón de filtro
            Toast.makeText(this.requireContext(), "En desarrollo", Toast.LENGTH_SHORT).show();
        }
        if (item.getItemId() == R.id.app_bar_logoutButton) {
            // Cierra la sesión del usuario
            logOut();
        }
        return super.onOptionsItemSelected(item);
    }

    private void fillLayout() {
        // Carga la imagen del usuario en la barra de herramientas y configura el nombre de usuario
        Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                // Convierte el bitmap en un Drawable y lo establece como ícono de navegación en la barra de herramientas
                Drawable d = new BitmapDrawable(getResources(), bitmap);
                toolbar_app.setNavigationIcon(d);
                // Establece el nombre de usuario como título de la barra de herramientas
                toolbar_app.setTitle(requireActivity().getIntent().getExtras().getString("user_name"));
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                // En caso de error al cargar la imagen, establece un ícono de error en la barra de herramientas
                toolbar_app.setNavigationIcon(errorDrawable);
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                // Muestra un ícono de carga mientras se carga la imagen del usuario
                toolbar_app.setNavigationIcon(placeHolderDrawable);
            }
        };

        // Utilizo Picasso para cargar y manipular la imagen del usuario
        Picasso.get()
                .load((Uri) requireActivity().getIntent().getExtras().getParcelable("user_picture"))
                .placeholder(R.drawable.image_not_found_nbg)
                .error(R.drawable.image_not_found_nbg)
                .resize(128, 128)
                .transform(new Rounded())
                .centerCrop()
                .into(target);
    }

    private void logOut() {
        // Cierra la sesión del usuario utilizando Firebase AuthUI
        AuthUI.getInstance()
                .signOut(this.requireContext())
                .addOnCompleteListener(task -> {
                    // Al completarse el cierre de sesión, inicia la actividad de inicio de sesión
                    Intent i = new Intent(this.getContext(), ActivityLogin.class);
                    startActivity(i);
                });
    }
}
