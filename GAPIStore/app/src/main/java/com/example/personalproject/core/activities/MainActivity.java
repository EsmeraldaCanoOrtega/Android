package com.example.personalproject.core.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.personalproject.R;
import com.example.personalproject.core.fragments.AppBarFragment;
import com.example.personalproject.core.model.GameListResponse;
import com.example.personalproject.endpoints.RetrofitBuilder;
import com.example.personalproject.endpoints.apis.GameAPI;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_view, AppBarFragment.class, null)
                    .commit();
        }
        setEvents();
        getGames();
    }

    private void getGames() {
        GameAPI service = RetrofitBuilder.getInstance().create(GameAPI.class);
        //Response<List<Game>> resp = service.listGames().execute();
        service.listGames("79749ec4d2534d149d3506e39b93e280")
                .enqueue(new Callback<>() {
                    @Override
                    public void onResponse(Call<GameListResponse> call, Response<GameListResponse> response) {
                        if (response.isSuccessful()) {
                            GameListResponse gml = response.body();


                        } else {
                            Toast.makeText(MainActivity.this,
                                    "Se ha cagado encima", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<GameListResponse> call, Throwable t) {
                        Toast.makeText(MainActivity.this,
                                "Me cague encima", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void setEvents() {
    }


}