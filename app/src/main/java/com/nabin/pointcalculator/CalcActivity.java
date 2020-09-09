package com.nabin.pointcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class CalcActivity extends AppCompatActivity implements Dailog.DailogListener {

    private TextView player1Name;
    private TextView player2Name;
    private TextView player3Name;
    private TextView player4Name;
    private TextView player5Name;

    private TextView player1Score;
    private TextView player2Score;
    private TextView player3Score;
    private TextView player4Score;
    private TextView player5Score;


    private EditText player1ScE;
    private EditText player2ScE;
    private EditText player3ScE;
    private EditText player4ScE;
    private EditText player5ScE;

    private Button buttonCalc;
    int playerInt;

    int p1S=0;
    int p2S=0;
    int p3S=0;
    int p4S=0;
    int p5S=0;

    boolean p3=false;
    boolean p4=false;
    boolean p5=false;

    static int p1D=0;
    static int p2D=0;
    static int p3D=0;
    static int p4D=0;
    static int p5D=0;


    private Switch player1Win;
    private Switch player2Win;
    private Switch player3Win;
    private Switch player4Win;
    private Switch player5Win;

    private Switch player1Un;
    private Switch player2Un;
    private Switch player3Un;
    private Switch player4Un;
    private Switch player5Un;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);


        String number =getIntent().getStringExtra("key");
        playerInt=Integer.parseInt(number);

        player1Name=findViewById(R.id.player1Name);
        player2Name=findViewById(R.id.player2Name);
        player3Name=findViewById(R.id.player3Name);
        player4Name=findViewById(R.id.player4Name);
        player5Name=findViewById(R.id.player5Name);

        player1Score=findViewById(R.id.player1Score);
        player2Score=findViewById(R.id.player2Score);
        player3Score=findViewById(R.id.player3Score);
        player4Score=findViewById(R.id.player4Score);
        player5Score=findViewById(R.id.player5Score);

        player1ScE=findViewById(R.id.player1ScoreEntry);
        player2ScE=findViewById(R.id.player2ScoreEntry);
        player3ScE=findViewById(R.id.player3ScoreEntry);
        player4ScE=findViewById(R.id.player4ScoreEntry);
        player5ScE=findViewById(R.id.player5ScoreEntry);


        buttonCalc=findViewById(R.id.buttonCal);

        player1Win=findViewById(R.id.player1Switch);
        player2Win=findViewById(R.id.player2Switch);
        player3Win=findViewById(R.id.player3Switch);
        player4Win=findViewById(R.id.player4Switch);
        player5Win=findViewById(R.id.player5Switch);

        player1Un=findViewById(R.id.player1Seen);
        player2Un=findViewById(R.id.player2Seen);
        player3Un=findViewById(R.id.player3Seen);
        player4Un=findViewById(R.id.player4Seen);
        player5Un=findViewById(R.id.player5Seen);

        openDailog();
        reset();

        player1Win.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(player1Win.isChecked()==true){
                    player2Win.setChecked(false);
                    player3Win.setChecked(false);
                    player4Win.setChecked(false);
                    player5Win.setChecked(false);
                }
            }
        });

        player2Win.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(player2Win.isChecked()==true){
                    player1Win.setChecked(false);
                    player3Win.setChecked(false);
                    player4Win.setChecked(false);
                    player5Win.setChecked(false);
                }
            }
        });

        player3Win.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(player3Win.isChecked()==true){
                    player2Win.setChecked(false);
                    player1Win.setChecked(false);
                    player4Win.setChecked(false);
                    player5Win.setChecked(false);
                }
            }
        });

        player4Win.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(player4Win.isChecked()==true){
                    player1Win.setChecked(false);
                    player3Win.setChecked(false);
                    player2Win.setChecked(false);
                    player5Win.setChecked(false);
                }
            }
        });

        player5Win.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(player5Win.isChecked()==true){
                    player1Win.setChecked(false);
                    player3Win.setChecked(false);
                    player2Win.setChecked(false);
                    player4Win.setChecked(false);
                }
            }
        });

        buttonCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p1S=Integer.parseInt(player1ScE.getText().toString());
                p2S=Integer.parseInt(player2ScE.getText().toString());
                p3S=Integer.parseInt(player3ScE.getText().toString());
                p4S=Integer.parseInt(player4ScE.getText().toString());
                p5S=Integer.parseInt(player5ScE.getText().toString());
                if(player1Win.isChecked()==false && player2Win.isChecked()==false && player3Win.isChecked()==false && player4Win.isChecked()==false && player5Win.isChecked()==false ){
                    Toast.makeText(CalcActivity.this,"Please select winner!!",Toast.LENGTH_LONG).show();
                    return;
                }
                calculateScore();
                displayScore();
                reset();
            }

        });


    }




    public void openDailog(){
        Dailog dailog =  new Dailog();
        dailog.show(getSupportFragmentManager(),"Example dailog");
    }

    @Override
    public void applyTexts(String name1, String name2, String name3, String name4, String name5) {
        if(TextUtils.isEmpty(name1)){
            name1="player-1";
        }
        if(TextUtils.isEmpty(name2)){
            name2="player-2";
        }
        if(TextUtils.isEmpty(name3)){
            name3="player-3";
        }
        if(TextUtils.isEmpty(name4)){
            name4="player-4";
        }
        if(TextUtils.isEmpty(name5)){
            name5="player-5";
        }
        switch(playerInt){
            case 2:
                player1Name.setText(name1);
                player2Name.setText(name2);
                player3Name.setVisibility(View.INVISIBLE);
                player3ScE.setVisibility(View.INVISIBLE);
                player3Un.setVisibility(View.INVISIBLE);
                player3Win.setVisibility(View.INVISIBLE);
                player4Name.setVisibility(View.INVISIBLE);
                player4ScE.setVisibility(View.INVISIBLE);
                player4Un.setVisibility(View.INVISIBLE);
                player4Win.setVisibility(View.INVISIBLE);
                player5Name.setVisibility(View.INVISIBLE);
                player5ScE.setVisibility(View.INVISIBLE);
                player5Un.setVisibility(View.INVISIBLE);
                player5Win.setVisibility(View.INVISIBLE);
                break;

            case 3:
                player1Name.setText(name1);
                player2Name.setText(name2);
                player3Name.setText(name3);
                player4Name.setVisibility(View.INVISIBLE);
                player4ScE.setVisibility(View.INVISIBLE);
                player4Un.setVisibility(View.INVISIBLE);
                player4Win.setVisibility(View.INVISIBLE);
                player5Name.setVisibility(View.INVISIBLE);
                player5ScE.setVisibility(View.INVISIBLE);
                player5Un.setVisibility(View.INVISIBLE);
                player5Win.setVisibility(View.INVISIBLE);
                break;

            case 4:
                player1Name.setText(name1);
                player2Name.setText(name2);
                player3Name.setText(name3);
                player4Name.setText(name4);
                player5Name.setVisibility(View.INVISIBLE);
                player5ScE.setVisibility(View.INVISIBLE);
                player5Un.setVisibility(View.INVISIBLE);
                player5Win.setVisibility(View.INVISIBLE);
                break;

            case 5:
                player1Name.setText(name1);
                player2Name.setText(name2);
                player3Name.setText(name3);
                player4Name.setText(name4);
                player5Name.setText(name5);
                break;

            default:
                break;
        }


    }


    public void reset(){
        player1Win.setChecked(false);
        player2Win.setChecked(false);
        player3Win.setChecked(false);
        player4Win.setChecked(false);
        player5Win.setChecked(false);

        player1Un.setChecked(false);
        player2Un.setChecked(false);
        player3Un.setChecked(false);
        player4Un.setChecked(false);
        player5Un.setChecked(false);
        p1S=0;
        p2S=0;
        p3S=0;
        p4S=0;
        p5S=0;
    }

    private void calculateScore() {
        switch(playerInt){
            case 2:
                p3S=0;p3=true;
                p4S=0;p4=true;
                p5S=0;p5=true;
                break;

            case 3:
                p4S=0;p4=true;
                p5S=0;p5=true;
                break;

            case 4:
                p5S=0;p5=true;
                break;

            default:
                break;
        }

        if(player1Un.isChecked()==true){
            p1S=0;
        }
        if(player2Un.isChecked()==true){
            p2S=0;
        }
        if(player3Un.isChecked()==true){
            p3S=0;
        }
        if(player4Un.isChecked()==true){
            p4S=0;
        }
        if(player5Un.isChecked()==true){
            p5S=0;
        }

        int tot=p1S+p2S+p3S+p4S+p5S;

        if(player1Win.isChecked()==true){

            if(player2Un.isChecked()==true){
                p2S=-(tot+10);
            }
            else{
                p2S=(p2S*playerInt)-(tot+3);
            }

            if(player3Un.isChecked()==true){
                p3S=-(tot+10);
            }
            else if(p3==true){
                p3S=0;
            }
            else{
                p3S=(p3S*playerInt)-(tot+3);
            }

            if(player4Un.isChecked()==true){
                p4S=-(tot+10);
            }
            else if (p4==true){
                p4S=0;
            }
            else{
                p4S=(p4S*playerInt)-(tot+3);
            }

            if(player5Un.isChecked()==true){
                p5S=-(tot+10);
            }
            else if(p5==true){
                p5S=0;
            }
            else{
                p5S=(p5S*playerInt)-(tot+3);
            }
            p1S=-(p2S+p3S+p4S+p5S);
        }

        if(player2Win.isChecked()==true){
            if(player1Un.isChecked()==true){
                p1S=-(tot+10);
            }
            else{
                p1S=(p1S*playerInt)-(tot+3);
            }

            if(player3Un.isChecked()==true){
                p3S=-(tot+10);
            }
            else if (p3==true){
                p3S=0;
            }
            else{
                p3S=(p3S*playerInt)-(tot+3);
            }

            if(player4Un.isChecked()==true){
                p4S=-(tot+10);
            }
            else if (p4==true){
                p4S=0;
            }
            else{
                p4S=(p4S*playerInt)-(tot+3);
            }

            if(player5Un.isChecked()==true){
                p5S=-(tot+10);
            }
            else if (p5==true){
                p5S=0;
            }
            else{
                p5S=(p5S*playerInt)-(tot+3);
            }
            p2S=-(p1S+p3S+p4S+p5S);
        }

        if(player3Win.isChecked()==true){
            if(player2Un.isChecked()==true){
                p2S=-(tot+10);
            }
            else{
                p2S=(p2S*playerInt)-(tot+3);
            }

            if(player1Un.isChecked()==true){
                p1S=-(tot+10);
            }
            else{
                p1S=(p1S*playerInt)-(tot+3);
            }

            if(player4Un.isChecked()==true){
                p4S=-(tot+10);
            }
            else if (p4==true){
                p4S=0;
            }
            else{
                p4S=(p4S*playerInt)-(tot+3);
            }

            if(player5Un.isChecked()==true){
                p5S=-(tot+10);
            }
            else if (p5==true){
                p5S=0;
            }
            else{
                p5S=(p5S*playerInt)-(tot+3);
            }
            p3S=-(p2S+p1S+p4S+p5S);
        }

        if(player4Win.isChecked()==true){
            if(player2Un.isChecked()==true){
                p2S=-(tot+10);
            }
            else{
                p2S=(p2S*playerInt)-(tot+3);
            }

            if(player3Un.isChecked()==true){
                p3S=-(tot+10);
            }
            else{
                p3S=(p3S*playerInt)-(tot+3);
            }

            if(player1Un.isChecked()==true){
                p1S=-(tot+10);
            }
            else{
                p1S=(p1S*playerInt)-(tot+3);
            }

            if(player5Un.isChecked()==true){
                p5S=-(tot+10);
            }
            else if (p5==true){
                p5S=0;
            } else{
                p5S=(p5S*playerInt)-(tot+3);
            }
            p4S=-(p2S+p3S+p1S+p5S);
        }

        if(player5Win.isChecked()==true){
            if(player2Un.isChecked()==true){
                p2S=-(tot+10);
            }
            else{
                p2S=(p2S*playerInt)-(tot+3);
            }

            if(player3Un.isChecked()==true){
                p3S=-(tot+10);
            }
            else{
                p3S=(p3S*playerInt)-(tot+3);
            }

            if(player4Un.isChecked()==true){
                p4S=-(tot+10);
            }
            else{
                p4S=(p4S*playerInt)-(tot+3);
            }

            if(player1Un.isChecked()==true){
                p1S=-(tot+10);
            }else{
                p1S=(p1S*playerInt)-(tot+3);
            }
            p5S=-(p2S+p3S+p4S+p1S);
        }
    }

    @SuppressLint("ResourceAsColor")
    private void displayScore() {
        p1D=p1S+p1D;
        p2D=p2S+p2D;
        p3D=p3S+p3D;
        p4D=p4S+p4D;
        p5D=p5S+p5D;


        if(p1D<0){
            player1Score.setTextColor(Color.RED);
        }
        else {
            player1Score.setTextColor(Color.GREEN);
        }
        if(p2D<0){
            player2Score.setTextColor(Color.RED);
        }
        else {
            player2Score.setTextColor(Color.GREEN);
        }
        if(p3D<0){
            player3Score.setTextColor(Color.RED);
        }
        else {
            player3Score.setTextColor(Color.GREEN);
        }
        if(p4D<0){
            player4Score.setTextColor(Color.RED);
        }
        else {
            player4Score.setTextColor(Color.GREEN);
        }
        if(p5D<0){
            player5Score.setTextColor(Color.RED);
        }
        else {
            player5Score.setTextColor(Color.GREEN);
        }
        switch(playerInt){
            case 2:
                player1Score.setText(Integer.toString(p1D));
                player2Score.setText(Integer.toString(p2D));
                break;

            case 3:
                player1Score.setText(Integer.toString(p1D));
                player2Score.setText(Integer.toString(p2D));
                player3Score.setText(Integer.toString(p3D));
                break;

            case 4:
                player1Score.setText(Integer.toString(p1D));
                player2Score.setText(Integer.toString(p2D));
                player3Score.setText(Integer.toString(p3D));
                player4Score.setText(Integer.toString(p4D));
                break;

            case 5:
                player1Score.setText(Integer.toString(p1D));
                player2Score.setText(Integer.toString(p2D));
                player3Score.setText(Integer.toString(p3D));
                player4Score.setText(Integer.toString(p4D));
                player5Score.setText(Integer.toString(p5D));
                break;

            default:
                break;
        }
    }
}
