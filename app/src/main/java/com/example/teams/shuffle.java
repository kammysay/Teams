package com.example.teams;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class shuffle extends AppCompatActivity {

    private Button returnToMain;

    Button shuffleAgain;
    ArrayList<String> passedList = new ArrayList<String>();
    ListView players;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shuffle);

        shuffleAgain = (Button)findViewById(R.id.shuffleTeams);

        //START LIST STUFF
        players = (ListView)findViewById(R.id.shuffledView);
        passedList = getIntent().getStringArrayListExtra("players");
        //ArrayAdapter<String> adapter = new ArrayAdapter<>(shuffle.this, android.R.layout.simple_list_item_1, passedList);
        //players.setAdapter(adapter);

        if(passedList.size() >= 1){
            Collections.shuffle(passedList);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(shuffle.this, android.R.layout.simple_list_item_1, passedList);
            players.setAdapter(adapter);
        }
        shuffleAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Collections.shuffle(passedList);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(shuffle.this, android.R.layout.simple_list_item_1, passedList);
                players.setAdapter(adapter);
                //Toast.makeText(getApplicationContext(), "RESHUFFLING...", Toast.LENGTH_SHORT).show();
            }
        });
        //END LIST STUFF

        //RETURN TO activity_main
        returnToMain = (Button)findViewById(R.id.goBack2);
        returnToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });//end return func
    }
}
