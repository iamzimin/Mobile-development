package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EditName extends AppCompatActivity {

    Button sumbit, back;
    TextView score;
    GameActivity gameActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_name);

        sumbit = findViewById(R.id.sumbit);
        back = findViewById(R.id.back);
        score = findViewById(R.id.yourScore);

        sumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(EditName.this, GameActivity.class);
                startActivity(i);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
    }
}
