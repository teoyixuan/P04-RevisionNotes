package com.myapplicationdev.android.p04_revisionnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText et;
    RadioGroup rg;
    Button btnInsert, btnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.editTextNote);
        rg = findViewById(R.id.radioGroupStars);
        btnInsert = findViewById(R.id.buttonInsertNote);
        btnShow = findViewById(R.id.buttonShowList);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String noteContent = et.getText().toString();
                // radio button
                int radioButtonID = rg.getCheckedRadioButtonId();
                View radioButton = (RadioButton) findViewById(radioButtonID);
                int idx = rg.indexOfChild(radioButton);
                RadioButton r = (RadioButton) rg.getChildAt(idx);
                String selectedText = r.getText().toString();
                int selected = Integer.parseInt(selectedText);

                DBHelper db = new DBHelper(MainActivity.this);
                db.insertNote(noteContent, selected);

                ArrayList<String> data = db.getNoteContent();
                db.close();

                for(int i = 0; i < data.size(); i++){
                    Log.d("Database Content", i +". "+data.get(i));
                }

                Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(i);
            }
        });
    }
}
