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
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.app_bar, container, false);
        setUpToolbar(view);
        fillLayout();
        return view;
    }

    private void setUpToolbar(View view) {
        toolbar_app = view.findViewById(R.id.app_bar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            activity.setSupportActionBar(toolbar_app);
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.app_bar_menu, menu);
        super.onCreateOptionsMenu(menu, menuInflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.app_bar_filterButton) {
            Toast.makeText(this.requireContext(), "In development", Toast.LENGTH_SHORT).show();
        }
        if (item.getItemId() == R.id.app_bar_logoutButton) {
            logOut();
        }

        return super.onOptionsItemSelected(item);
    }

    private void fillLayout() {

        Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Drawable d = new BitmapDrawable(getResources(), bitmap);
                toolbar_app.setNavigationIcon(d);
                toolbar_app.setTitle(Objects.requireNonNull(requireActivity().getIntent().getExtras()).getString("user_name"));
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                toolbar_app.setNavigationIcon(errorDrawable);
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                toolbar_app.setNavigationIcon(placeHolderDrawable);

            }
        };


        Picasso.get()
                .load((Uri) Objects.requireNonNull(requireActivity().getIntent().getExtras()).getParcelable("user_picture"))
                .placeholder(R.drawable.image_not_found_nbg)
                .error(R.drawable.image_not_found_nbg)
                .resize(128, 128)
                .transform(new Rounded())
                .centerCrop()
                .into(target);
    }

    private void logOut() {
        AuthUI.getInstance()
                .signOut(this.requireContext())
                .addOnCompleteListener(task -> {
                    Intent i = new Intent(this.getContext(), ActivityLogin.class);
                    startActivity(i);
                });

    }
}
