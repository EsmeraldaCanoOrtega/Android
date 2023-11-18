package com.example.personalproject.ui.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;


import androidx.annotation.NonNull;
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
        String pattern = "^.*(page=.).*$";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(url);
        if(m.find()){
            nextPage = Integer.parseInt(m.group(1).split("=")[1]);
            currentPage = nextPage - 1;
            previousPage = nextPage == 2 ? 1 : nextPage - 2;
        }
    }

    private void getGames(int page) {
        GameAPI service = RetrofitBuilder.getInstance().create(GameAPI.class);
        Map<String, Object> params = new HashMap<>();
        params.put("key", "79749ec4d2534d149d3506e39b93e280");
        params.put("page", page);
        params.put("page_size", 20);
        service.listGames(params)
                .enqueue(new Callback<>() {
                    @Override
                    public void onResponse(@NonNull Call<GameListResponse> call,
                                           @NonNull Response<GameListResponse> response) {
                        if (response.isSuccessful()) {
                            GameListResponse gml = response.body();
                            if (gml.getResults() != null) {
                                gamesRVAdapter.setLocalDataSet(gml.getResults());
                                gamesRVAdapter.notifyDataSetChanged();
                                setCurrentPage(gml.getNext());
                            }
                        } else {
                            Toast.makeText(MainActivity.this,
                                    response.message(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<GameListResponse> call,
                                          @NonNull Throwable t) {
                        Toast.makeText(MainActivity.this,
                                t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


}