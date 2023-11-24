package com.example.personalproject.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personalproject.R;
import com.example.personalproject.model.Result;
import com.example.personalproject.ui.activities.MainActivity;
import com.example.personalproject.ui.fragments.AppBarFragment;
import com.google.android.material.imageview.ShapeableImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GamesRVAdapter extends RecyclerView.Adapter<GamesRVAdapter.GameViewHolder> {
    private List<Result> localDataSet;

    // ViewHolder para el RecyclerView
    public static class GameViewHolder extends RecyclerView.ViewHolder {
        private final Button button_favoriteNbg;
        private final TextView textView_title;
        private final ShapeableImageView imageView_background;

        // Constructor para inicializar las vistas
        public GameViewHolder(View view) {
            super(view);
            button_favoriteNbg = view.findViewById(R.id.button_favoriteNbg);
            textView_title = view.findViewById(R.id.textView_title);
            imageView_background = view.findViewById(R.id.imageView_background);
        }

        // getters
        public Button getButtonFavNBG() {
            return button_favoriteNbg;
        }

        public TextView getTextViewTitle() {
            return textView_title;
        }

        public ShapeableImageView getImageViewBackground() {
            return imageView_background;
        }
    }

    // Constructor para el adaptador
    public GamesRVAdapter(List<Result> games) {
        localDataSet = games;
    }

    // Infla el diseño para cada elemento en el RecyclerView
    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.game, viewGroup, false);

        return new GameViewHolder(view);
    }

    // Vincula datos a las vistas en cada ViewHolder
    @Override
    public void onBindViewHolder(GameViewHolder viewHolder, final int position) {
        // Establece el título del juego
        viewHolder.getTextViewTitle().setText(localDataSet.get(position).getName());

        // Carga la imagen de fondo usando la biblioteca Picasso
        Picasso.get()
                .load(localDataSet.get(position).getBackgroundImage())
                .placeholder(R.drawable.image_not_found_nbg)
                .error(R.drawable.image_not_found_nbg)
                .centerCrop()
                .fit()
                .into(viewHolder.getImageViewBackground());

        // TODO: Establecer un OnClickListener para el botón de favoritos si es necesario
        // viewHolder.getButtonFavNBG().setOnClickListener();
    }

    // Devuelve la cantidad de elementos en el conjunto de datos
    @Override
    public int getItemCount() {
        if (localDataSet == null)
            return 0;
        else
            return localDataSet.size();
    }

    // Actualiza el conjunto de datos con una nueva lista de juegos
    public void setLocalDataSet(List<Result> games){
        localDataSet = games;
    }
}
