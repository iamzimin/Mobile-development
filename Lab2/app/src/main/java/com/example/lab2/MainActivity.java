package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.Surface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button plus;
    private Button button1;
    private Button button2;
    private Button button3;
    private ListView listView;
    private EditText editText;
    private SparseBooleanArray mCheckStates;

    private String[] output;
    private int countSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getWindowManager().getDefaultDisplay().getRotation() == Surface.ROTATION_0) // return // 1 горизонт // 0 вертикаль
        {
            String languageToLoad  = "ru";
            Locale locale = new Locale(languageToLoad);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
            this.setContentView(R.layout.activity_main);
        }
        else
        {
            String languageToLoad  = "en";
            Locale locale = new Locale(languageToLoad);
            Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
            this.setContentView(R.layout.activity_main);
        }

        listView = findViewById(R.id.listView);
        plus = findViewById(R.id.plus);
        editText = findViewById(R.id.text);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);


        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, List.getItems());
        listView.setAdapter(adapter);


        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String add = editText.getText().toString();
                if (!add.isEmpty()) {
                    adapter.add(add);
                    adapter.notifyDataSetChanged();
                    editText.setText("");
                }
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                SparseBooleanArray chosen = ((ListView) parent).getCheckedItemPositions();
                countSelected = 0;
                for (int i = 0; i < chosen.size(); i++) {
                    if (chosen.valueAt(i)) {
                        countSelected++;
                    }
                }
            }
        }); //подсчёт количества выбранных

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SparseBooleanArray checkedItemPositions = listView.getCheckedItemPositions();
                for (int i = 0; i < listView.getCount(); i++)
                    checkedItemPositions.append(i, true);

                countSelected = listView.getCount();
                adapter.notifyDataSetChanged();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SparseBooleanArray checkedItemPositions = listView.getCheckedItemPositions();
                for(int i = 0; i < listView.getCount(); i++)
                    checkedItemPositions.append(i, false);

                countSelected = 0;
                adapter.notifyDataSetChanged();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                output = new String[countSelected];
                SparseBooleanArray checkedItemPositions = listView.getCheckedItemPositions();
                int id = 0;
                for(int i = 0; i < listView.getCount(); i++) {
                    if(checkedItemPositions.get(i)) {
                        output[id++] = List.getItems().get(i);
                    }
                }
                String s = String.join(" ", output);
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

