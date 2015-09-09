package com.example.magarwal.tictactoe;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by magarwal on 8/10/2015.
 */

public class PlayBoardActivity extends Activity {

    int count = 0;
    int arr[][] = new int[3][3];
    int cross_image = R.drawable.system_cross;
    int dot_image = R.drawable.system_dot;
    String player1Name;
    String player2Name;
    int playerCount;

    TextView currentPlayerName=null;


     ImageButton b1;
    ImageButton b2;
     ImageButton b3;
     ImageButton b4;
    ImageButton b5 ;
    ImageButton b6;
    ImageButton b7 ;
    ImageButton b8 ;
    ImageButton b9;

    View.OnClickListener button_listener = new View.OnClickListener() {
        public void onClick(View v) {
            int currentPlayer = 0;
            ImageButton imageButton = (ImageButton) v;
            imageButton.setClickable(false);
            if (count % 2 == 0) {
                imageButton.setImageResource(cross_image);
                currentPlayerName.setText(player2Name);
                currentPlayer = 1 ;

                if(playerCount==1){
                    count++;
                    updateAndCheckResult(imageButton, 1);
                    if(count==9)
                        game_draw();

                    else{
                    TicTacToeMinMaxImplementation ticTacToeMinMaxImplementation = new TicTacToeMinMaxImplementation();
                    int position = ticTacToeMinMaxImplementation.callMinMax(arr);
                    ImageButton imbt =(ImageButton)findViewById(R.id.playboard_button1);

                    int id  = imbt.getId();

                    if(position<=3)
                        position =position + id -1;
                    else
                        position = position+id;


                    imbt = (ImageButton)findViewById(position);
                    imbt.setImageResource(dot_image);
                    imbt.setClickable(false);

                    imageButton =imbt;

                    currentPlayer=2;
                    currentPlayerName.setText(player1Name);
                    }
                }

            } else {
                imageButton.setImageResource(dot_image);
                currentPlayerName.setText(player1Name);
                currentPlayer = 2;
            }

            updateAndCheckResult(imageButton, currentPlayer);
            count++;
            if(count==9)
                game_draw();
        }
    };

    private void game_draw() {
        CharSequence message ="Its a tie , play again";
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message).setPositiveButton("New Game", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // reset the game environment.
                start_new_game();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();

    }


    void updateAndCheckResult(ImageButton imageButton, int currentPlayer) {

        CharSequence button_id = (CharSequence) imageButton.getTag();
        int position = button_id.charAt(0) - 48;
        boolean result = false;


        if (position <= 3)
            arr[0][(position-1)%3] = currentPlayer;
        else if (position <= 6)
            arr[1][(position-1)%3] = currentPlayer;
        else
            arr[2][(position-1)%3] = currentPlayer;

        result = checkResult(currentPlayer);
        if (result) {
            showWinner(currentPlayer);
        }

    }

    private void showWinner(int currentPlayer) {
        CharSequence message = "";
        if (currentPlayer == 1)
            message = message + player1Name;
        else
            message = message + player2Name;

        message = message + " " + "wins";

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message).setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // reset the game environment.
                start_new_game();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();

    }

    private boolean checkResult(int currentPlayer) {
        boolean winningMove = true;

        //check for horizontal

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (arr[i][j] != currentPlayer) {
                    winningMove = false;
                    break;
                }

            }
            if (winningMove)
                return winningMove;
            winningMove = true;

        }

        //check for vertical

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (arr[j][i] != currentPlayer) {
                    winningMove = false;
                    break;
                }

            }

            if (winningMove)
                return winningMove;
            winningMove = true;
        }

        //check for forward diagonal

        for (int i = 0; i < 3; i++) {
            if (arr[i][i] != currentPlayer) {
                winningMove = false;
                break;
            }
        }
        if (winningMove)
            return winningMove;

        winningMove = true;


        //check for backward diagonal

        for (int i = 0, j = 2; i < 3; i++, j--) {
            if (arr[i][j] != currentPlayer) {
                winningMove = false;
                break;
            }
        }
        if (winningMove)
            return winningMove;

        return false;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        start_new_game();

    }

    private void start_new_game() {
        setContentView(R.layout.playboard);

        Bundle extras = getIntent().getExtras();

        player1Name = getIntent().getStringExtra("player1").toString();
        player2Name = getIntent().getStringExtra("player2").toString();
        playerCount = getIntent().getIntExtra(Constants.NoOfPlayers,2);

        final TextView player1 = (TextView) findViewById(R.id.playboard_textView1);
        final TextView player2 = (TextView) findViewById(R.id.playboard_textView3);

        //final TextView currentPlayer =(TextView)findViewById(R.id.CurrentPlayer);
        currentPlayerName =(TextView)findViewById(R.id.CurrentPlayerName);

        player1.setText(player1Name);
        player2.setText(player2Name);
        currentPlayerName.setText(player1Name);

        b1 = (ImageButton) findViewById(R.id.playboard_button1);
        b2 = (ImageButton) findViewById(R.id.playboard_button2);
        b3 = (ImageButton) findViewById(R.id.playboard_button3);
        b4 = (ImageButton) findViewById(R.id.playboard_button4);
        b5 = (ImageButton) findViewById(R.id.playboard_button5);
        b6 = (ImageButton) findViewById(R.id.playboard_button6);
        b7 = (ImageButton) findViewById(R.id.playboard_button7);
        b8 = (ImageButton) findViewById(R.id.playboard_button8);
        b9 = (ImageButton) findViewById(R.id.playboard_button9);

        b1.setOnClickListener(button_listener);
        b2.setOnClickListener(button_listener);
        b3.setOnClickListener(button_listener);
        b4.setOnClickListener(button_listener);
        b5.setOnClickListener(button_listener);
        b6.setOnClickListener(button_listener);
        b7.setOnClickListener(button_listener);
        b8.setOnClickListener(button_listener);
        b9.setOnClickListener(button_listener);

        b1.setClickable(true);
        b2.setClickable(true);
        b3.setClickable(true);
        b4.setClickable(true);
        b5.setClickable(true);
        b6.setClickable(true);
        b7.setClickable(true);
        b8.setClickable(true);
        b9.setClickable(true);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arr[i][j] = 0;
            }
        }

        count =0;
    }
}
