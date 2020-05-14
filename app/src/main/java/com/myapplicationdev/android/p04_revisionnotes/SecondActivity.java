package com.myapplicationdev.android.p04_revisionnotes;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

	ListView lv;
	ArrayAdapter aa;
	ArrayList<Note> notes;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		//TODO implement the Custom ListView

		lv = (ListView)findViewById(R.id.lv);

		DBHelper db = new DBHelper(SecondActivity.this);
		ArrayList<Note> data = db.getAllNotes();
		db.close();

		if (data.size() > 0) {
			aa = new RevisionNotesArrayAdapter(this, R.layout.row, data);
			lv.setAdapter(aa);
		}
	}


}
