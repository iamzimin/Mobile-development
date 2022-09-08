package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditName extends AppCompatActivity {

    Button sumbit, back;
    EditText enterNickname;
    String nickname;
    SaveNickname saveNickname;
    //TextView score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_name);

        sumbit = findViewById(R.id.sumbit);
        back = findViewById(R.id.back);
        enterNickname = findViewById(R.id.enterNickname);
        saveNickname = new SaveNickname();
        //score = findViewById(R.id.yourScore);

        sumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (enterNickname.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), getResources().getText(R.string.toastNickname), Toast.LENGTH_SHORT).show();
                    return;
                }

                nickname = enterNickname.getText().toString();
                saveNickname.setNickname(nickname);

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
