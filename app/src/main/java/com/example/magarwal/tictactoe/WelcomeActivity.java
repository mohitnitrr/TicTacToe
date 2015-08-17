package com.example.magarwal.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        final Button startButton = (Button) findViewById(R.id.start);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent formActivityIntent = new Intent(WelcomeActivity.this,
                        FormActivity.class);

                // Use the Intent to start the HelloAndroid Activity
                startActivity(formActivityIntent);
            }
        });
    }
}
