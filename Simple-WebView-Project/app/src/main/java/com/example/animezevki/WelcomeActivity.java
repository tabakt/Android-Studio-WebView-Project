package com.example.animezevki;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        ImageView animeIzleButton = findViewById(R.id.animeIzleButton);
        ImageView animeFilmIzleButton = findViewById(R.id.animeFilmIzleButton);

        // Anime İzle butonuna tıklama olayı
        animeIzleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                intent.putExtra("url", "https://www.google.com/");
                startActivity(intent);
            }
        });

        // Anime Film İzle butonuna tıklama olayı
        animeFilmIzleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                intent.putExtra("url", "https://www.google.com/"); // webview ile hangi sayfayı göstermek istiyorsanız onu yazın.
                startActivity(intent);
            }
        });
    }
}
