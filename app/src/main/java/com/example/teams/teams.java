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
import java.util.List;
import java.util.Random;

public class teams extends AppCompatActivity {

    private Button returnToMain;

    Button newTeams;
    ArrayList<String> passedList = new ArrayList<String>();
    ArrayList<String> teamList = new ArrayList<String>();
    ListView teams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);

        newTeams = (Button)findViewById(R.id.newTeams);
        teams = (ListView)findViewById(R.id.teamListView);

        //TEAM GENERATION
        passedList = getIntent().getStringArrayListExtra("players");
        Collections.shuffle(passedList);
        teamGen(passedList);

        newTeams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teamList.clear();
                Collections.shuffle(passedList);
                teamGen(passedList);
            }
        });

        returnToMain = (Button)findViewById(R.id.goBack1);
        returnToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });//end returnToMain
    }//END onCreate

    //Method to generate teams
    public void teamGen(ArrayList passedList){
        int count = 1;
        int playerCount = passedList.size();
        if(passedList.size() % 2 == 0) {//if even number of players
            for (int i = 1; i < playerCount; i = i+2) {
                String evenTeams = "TEAM " + count + ": " + passedList.get(i-1) + " + " + passedList.get(i);
                count++;
                teamList.add(evenTeams);
            }
        }else {//if odd number of players
            Random rnd = new Random();
            passedList.add(passedList.get(rnd.nextInt(playerCount-1)));   //Select one player at random to play on two teams
            for (int i = 1; i <= playerCount; i = i + 2) {
                String oddTeams = "TEAM " + count + ": " + passedList.get(i-1) + " + " + passedList.get(i);
                count++;
                teamList.add(oddTeams);
            }
            passedList.remove(passedList.size()-1); //remove the randomly added player, in case user requires new teams
        }
        //Displaying the generated teams in a ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(teams.this, android.R.layout.simple_list_item_1, teamList);
        teams.setAdapter(adapter);
    }
}
