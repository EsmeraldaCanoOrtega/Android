package com.example.personalproject.endpoints;

import android.content.Context;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class DatabaseProvider {

    private final Context context;

    private static FirebaseDatabase database;
    private static DatabaseReference databaseReference;

    public DatabaseProvider(Context context) {
        this.context = context;
        if(database == null){
            initialize();
        }
    }

    public void initialize() {
        try {
            // Read the JSON file from the assets folder
            InputStream inputStream = context.getAssets().open("google-services.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String jsonString = new String(buffer, StandardCharsets.UTF_8);

            JSONObject json = new JSONObject(jsonString);

            // Extract the values
            String projectId = json.getJSONObject("project_info").getString("project_id");
            String databaseUrl = json.getJSONObject("project_info").getString("storage_bucket");
            String apiKey = json.getJSONArray("client").getJSONObject(0).getJSONArray("api_key").getJSONObject(0).getString("current_key");
            String applicationId = json.getJSONArray("client").getJSONObject(0).getJSONObject("client_info").getString("mobilesdk_app_id");

            // Configure Firebase
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setProjectId(projectId)
                    .setDatabaseUrl(databaseUrl)
                    .setApiKey(apiKey)
                    .setApplicationId(applicationId)
                    .build();

            FirebaseApp.initializeApp(context, options);
            database = FirebaseDatabase.getInstance();
            databaseReference = database.getReference();

            Toast.makeText(context, "Firebase connected successfully", Toast.LENGTH_SHORT).show();

        } catch (IOException | JSONException e) {
            e.printStackTrace();
            Toast.makeText(context, "Firebase connection failed: " + e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public DatabaseReference getReference(String referencePath) {
        return databaseReference.child(referencePath);
    }

    public void close() {
        database.getApp().delete();
        Toast.makeText(context, "Firebase connection closed", Toast.LENGTH_SHORT).show();
    }
}
