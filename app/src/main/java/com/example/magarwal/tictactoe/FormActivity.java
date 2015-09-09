package com.example.magarwal.tictactoe;

/**
 * Created by magarwal on 8/9/2015.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class FormActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form);

        final Button cancel = (Button) findViewById(R.id.cancel);
        final Button startPlayBoard = (Button) findViewById(R.id.start1);
        final EditText player1 = (EditText) findViewById(R.id.editText);
        final EditText player2 = (EditText) findViewById(R.id.editText1);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player1.getText().clear();
                player2.getText().clear();
            }
        });

        startPlayBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playBoardActivity = new Intent(FormActivity.this,
                        PlayBoardActivity.class);

                playBoardActivity.putExtra("player1", player1.getText().toString());
                playBoardActivity.putExtra("player2", player2.getText().toString());
                playBoardActivity.putExtra(Constants.NoOfPlayers, getIntent().getIntExtra(Constants.NoOfPlayers,2));
                // Use the Intent to start the HelloAndroid Activity
                startActivity(playBoardActivity);
            }
        });
    }
}
