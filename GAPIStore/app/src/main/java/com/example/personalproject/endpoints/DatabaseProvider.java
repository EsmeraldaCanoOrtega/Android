package com.example.personalproject.endpoints;

import android.app.Activity;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class DatabaseProvider {

    private final Activity activityRef;

    private static FirebaseDatabase database;
    private static DatabaseReference databaseReference;

    public DatabaseProvider(Activity activityRef) {
        this.activityRef = activityRef;
        if (database == null) {
            initialize();
        }
    }

    public void initialize() {
        try {
            InputStream inputStream = activityRef.getBaseContext().getAssets().open("google-services.json");
            //GoogleCredentials gcredentials = GoogleCredentials.fromStream(inputStream);

            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String jsonString = new String(buffer, StandardCharsets.UTF_8);

            JSONObject json = new JSONObject(jsonString);

            String projectId = json.getJSONObject("project_info").getString("project_id");
            String storageBucket = json.getJSONObject("project_info").getString("storage_bucket");
            String apiKey = json.getJSONArray("client").getJSONObject(0).getJSONArray("api_key").getJSONObject(0).getString("current_key");
            String applicationId = json.getJSONArray("client").getJSONObject(0).getJSONObject("client_info").getString("mobilesdk_app_id");
            String databaseURL = json.getJSONObject("project_info").getString("database_url");


            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setApiKey(apiKey)
                    .setApplicationId(applicationId)
                    .setProjectId(projectId)
                    .setStorageBucket(storageBucket)
                    .setDatabaseUrl(databaseURL)
                    .build();

            FirebaseApp.initializeApp(activityRef.getApplicationContext(), options, "Personal");
            database = FirebaseDatabase.getInstance();
            databaseReference = database.getReference();
        } catch (Exception ex) {
            Toast.makeText(activityRef.getApplicationContext(), ex.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public DatabaseReference getReference(String referencePath) {
        return databaseReference.child(referencePath);
    }

    public void close() {
        database.getApp().delete();
    }
}