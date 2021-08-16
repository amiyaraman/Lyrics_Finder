package com.example.lyricsfinder;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView show_lyrics;
    EditText singer_name , song_name;
    Button search_lyrics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        show_lyrics= findViewById(R.id.show_lyrics);
        singer_name = findViewById(R.id.msingername);
        song_name = findViewById(R.id.msongname);
        search_lyrics = findViewById(R.id.srchlyrics);
//        https://api.lyrics.ovh/v1/Coldplay/Adventure of a Lifetime
        search_lyrics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://api.lyrics.ovh/v1/" + singer_name.getText().toString() +"/" + song_name.getText().toString();
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        show_lyrics.setText(response.toString());

                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        });
                requestQueue.add(jsonObjectRequest);


            }
        });

    }
}