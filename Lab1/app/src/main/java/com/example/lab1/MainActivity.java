package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MainActivity extends AppCompatActivity {

    EditText field1, field2;
    Button sumBtn, minBtn, mulBtn, devBtn;

    private void solve(int s) {
        float x = Float.parseFloat(field1.getText().toString());
        float y = Float.parseFloat(field2.getText().toString());
        Intent i = new Intent(MainActivity.this, ResultActivity.class);
        float z = 0;
        if (s == 1) {
            z = x + y;
        }
        else if (s == 2) {
            z = x - y;
        }
        else if (s == 3) {
            z = x * y;
        }
        else if (s == 4) {
            z = x / y;
        }

        String decimal = String.valueOf(z);
        int count = 0;
        for (int q = decimal.length() - 1; q != 0; q--)
        {
            if (decimal.charAt(q) == '0')
                count++;
            else if (decimal.charAt(q) == '.') {
                count++;
                break;
            }
            else
                break;
        }

        String out = decimal.substring(0, decimal.length() - count);
        i.putExtra("Result", out);
        startActivity(i);
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        field1 = findViewById(R.id.field1);
        field2 = findViewById(R.id.field2);
        sumBtn = findViewById(R.id.sumBtn);
        minBtn = findViewById(R.id.minBtn);
        mulBtn = findViewById(R.id.mulBtn);
        devBtn = findViewById(R.id.devBtn);

        sumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                solve(1);
            }
        });

        minBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                solve(2);
            }
        });

        mulBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                solve(3);
            }
        });

        devBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                solve(4);
            }
        });
    }
}