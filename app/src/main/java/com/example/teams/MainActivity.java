package com.example.teams;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Init buttons to switch between activities
    private Button teamAct;
    private Button shuffleAct;

    //Init vars for functionality on activity
    Button clear;
    Button add;
    ArrayList<String> players = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    EditText person;
    ListView team;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //CLEAR THE CURRENTLY ADDED PLAYERS FROM THE LIST
        clear = (Button)findViewById(R.id.clearButton);
        clear.setOnClickListener(new View.OnClickListener() {//start Anonymous Inner Class
            @Override
            public void onClick(View v) {   //there's probably a cleaner way to achieve the next three lines
                players.clear();
                adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, players);
                team.setAdapter(adapter);
            }
        }/*end A.I.C.*/);

        //MAIN ACTIVITY START
        //Setting vars init'd above
        person = (EditText)findViewById(R.id.userInput);
        team = (ListView)findViewById(R.id.lstView);
        add = (Button)findViewById(R.id.enterInput);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getPlayer = person.getText().toString();

                if( players.contains(getPlayer)) {
                    Toast.makeText(getBaseContext(), "ALREADY IN LIST", Toast.LENGTH_LONG).show();
                }else if(getPlayer == null || getPlayer.trim().equals("")){
                    Toast.makeText(getBaseContext(), "EMPTY FIELD", Toast.LENGTH_LONG).show();
                }else{
                    players.add(getPlayer);
                    adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, players);
                    team.setAdapter(adapter);
                    ((EditText)findViewById(R.id.userInput)).setText("");
                }
            }
        });
        //MAIN ACTIVITY END



        //START SWITCH ACTS
        teamAct = (Button)findViewById(R.id.toTeams);
        teamAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(players.size() <= 1){
                    Toast.makeText(getBaseContext(), "ADD PLAYERS", Toast.LENGTH_LONG).show();
                }else {
                    openTeams();
                    Toast.makeText(getBaseContext(), "GENERATING TEAMS...", Toast.LENGTH_SHORT).show();
                }
            }
        });
        shuffleAct = (Button)findViewById(R.id.toShuffle);
        shuffleAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(players.size() == 0){
                    Toast.makeText(getBaseContext(), "NO PLAYERS ADDED", Toast.LENGTH_LONG).show();
                }else {
                    openShuffle();
                    Toast.makeText(getBaseContext(), "SHUFFLING PLAYERS...", Toast.LENGTH_SHORT).show();
                }
            }
        });//END SWITCH ACTS

    }//end onCreate

    //open activity_teams
    public void openTeams(){
        Intent teamsIntent = new Intent(this, teams.class);
        teamsIntent.putStringArrayListExtra("players", players);
        startActivity(teamsIntent);
    }//end openTeams

    //open activity_shuffle
    public void openShuffle(){
        Intent shuffleIntent = new Intent(this, shuffle.class);
        shuffleIntent.putStringArrayListExtra("players", players);
        startActivity(shuffleIntent);
    }//end openTeams

    }//end class MainActivity
