package com.example.personalproject.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personalproject.R;
import com.example.personalproject.ui.adapters.GamesRVAdapter;
import com.example.personalproject.ui.fragments.AppBarFragment;
import com.example.personalproject.model.GameListResponse;
import com.example.personalproject.endpoints.RetrofitBuilder;
import com.example.personalproject.endpoints.apis.GameAPI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView_games;
    private GamesRVAdapter gamesRVAdapter;
    private Button button_next;
    private Button button_prev;

    private int currentPage = 1;
    private int nextPage = 1;
    private int previousPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView_games = findViewById(R.id.recyclerView_games);
        button_next = findViewById(R.id.button_next);
        button_prev = findViewById(R.id.button_previous);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_view, AppBarFragment.class, null)
                    .commit();
        }
        configAdapter();
        setEvents();
        if (gamesRVAdapter.getItemCount() == 0) {
            getGames(currentPage);
        }
    }

    private void configAdapter() {
        gamesRVAdapter = new GamesRVAdapter(new ArrayList<>());
        recyclerView_games.setAdapter(gamesRVAdapter);
    }

    private void setEvents() {
        button_next.setOnClickListener(v -> getGames(nextPage));
        button_prev.setOnClickListener(v -> getGames(previousPage));
    }

    private void setCurrentPage(String url) {
        // Define un patrón de expresión regular(regex) para extraer información de la URL
        // He utilizado https://regex101.com para probar el regex
        String pattern = "^.*(page=.).*$";
        Pattern r = Pattern.compile(pattern);

        // Crea un objeto Matcher para aplicar el patrón a la URL
        Matcher m = r.matcher(url);

        // Verifica si el patrón coincide con la URL
        if (m.find()) {
            // Extrae el número de página de la URL y actualiza las variables de página
            nextPage = Integer.parseInt(m.group(1).split("=")[1]);
            currentPage = nextPage - 1;
            previousPage = nextPage == 2 ? 1 : nextPage - 2;
        }
    }

    private void getGames(int page) {
        // Obtiene una instancia del servicio GameAPI a través de Retrofit
        GameAPI service = RetrofitBuilder.getInstance().create(GameAPI.class);

        // Configura los parámetros para la solicitud de la lista de juegos
        Map<String, Object> params = new HashMap<>();
        params.put("key", "79749ec4d2534d149d3506e39b93e280");
        params.put("page", page);
        params.put("page_size", 20);

        // Realiza una solicitud asincrónica para obtener la lista de juegos
        service.listGames(params)
                // Intenté con .execute pero la aplicación se detiene porque era sincrona
                .enqueue(new Callback<>() {
                    @Override
                    public void onResponse(@NonNull Call<GameListResponse> call,
                                           @NonNull Response<GameListResponse> response) {
                        // Maneja la respuesta exitosa
                        if (response.isSuccessful()) {
                            // Obtiene la respuesta en formato GameListResponse
                            GameListResponse gml = response.body();
                            if (gml.getResults() != null) {
                                // Actualiza el conjunto de datos local en el adaptador y notifica cambios
                                gamesRVAdapter.setLocalDataSet(gml.getResults());
                                gamesRVAdapter.notifyDataSetChanged();
                                // Establece la página actual para futuras solicitudes
                                setCurrentPage(gml.getNext());
                            }
                        } else {
                            // Muestra un mensaje de error si la respuesta no fue exitosa
                            Toast.makeText(MainActivity.this,
                                    response.message(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<GameListResponse> call,
                                          @NonNull Throwable t) {
                        // Muestra un mensaje de error en caso de fallo en la solicitud
                        Toast.makeText(MainActivity.this,
                                t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

}