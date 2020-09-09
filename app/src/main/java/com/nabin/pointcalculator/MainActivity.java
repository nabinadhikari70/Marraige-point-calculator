package com.nabin.pointcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button start;
    private TextView player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start=findViewById(R.id.button);
        player=findViewById(R.id.playerName);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String playerNo=player.getText().toString();

                if (TextUtils.isEmpty(playerNo)){
                    player.setError("Required Field");
                    return;
                }
                int playerInt=Integer.parseInt(playerNo);

                if (playerInt<2 || playerInt>5){
                    player.setError("Player number error");
                    return;
                }

                Intent intent = new Intent(MainActivity.this,CalcActivity.class);
                intent.putExtra("key",playerNo);
                startActivity(intent);
            }
        });
    }

}
