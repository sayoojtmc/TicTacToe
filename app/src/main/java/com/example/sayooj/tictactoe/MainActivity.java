package com.example.sayooj.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{
    private Button[][] buttons=new Button[3][3];
    private Boolean player1turn=true;
    private int roundcount;
    private int player1point=0;
    private int player2point=0;

    private TextView textview1;
    private TextView textview2;
    Button reset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textview1 = findViewById(R.id.textview1);
        textview2 = findViewById(R.id.textview2);
        reset=findViewById(R.id.button_reset);

        int i, j;
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                final int x = i;
                final int y = j;

                String ButtonID = "button_" + i + j;
                int resID = getResources().getIdentifier(ButtonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Button b = (Button) v;
                        if (!b.getText().toString().equals("*")) {
                            return;
                        }
                        if (player1turn) {
                            b.setText("X");
                        } else {
                            b.setText("O");

                        }
                        if(checkwin())
                        {
                            if(player1turn){
                                player1point++;
                                textview1.setText("Player1:"+player1point);


                            }
                            else{
                                player2point++;
                                textview2.setText("player2:" + player2point);


                            }
                            reset();
                        }
                        if(roundcount==9)
                        {
                            reset();
                        }

                        roundcount++;

                        if(player1point==10)
                        {
                            Toast.makeText(getApplicationContext(), "Player1 wins!",
                                    Toast.LENGTH_SHORT).show();
                            reset();
                        }
                        else if(player2point==10)
                        {
                            Toast.makeText(getApplicationContext(), "Player2 wins!",
                                    Toast.LENGTH_SHORT).show();
                            reset();
                        }
                    }
                });
            }
        }
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
    }

                        void reset(){
                            for(int i=0;i<3;i++){
                                for(int j=0;j<3;j++){
                                    buttons[i][j].setText("*");
                                }
                            }
                        }

                        private boolean checkwin(){
                            String field[][]=new String[3][3];
                            for(int i=0;i<3;i++)
                            {
                                for(int j=0;j<3;j++)
                                    field[i][j]=buttons[i][j].getText().toString();
                            }
                            for(int i=0;i<3;i++) {
                                if (field[i][0].equals(field[i][1]) && field[i][1].equals(field[i][2]) && !field[i][0].equals("*")) {
                                    return true;
                                } else if (field[0][i].equals(field[1][i]) && field[1][i].equals(field[2][i]) && !field[0][i].equals("*")) {
                                    return true;
                                } else if (field[1][1].equals(field[2][2]) && field[0][0].equals(field[2][2]) && !field[1][1].equals( "*")) {
                                    return true;
                                } else if (field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[2][0].equals("*")) {
                                    return true;
                                }

                                }
                                player1turn=!player1turn;
                                return false;
                            }









                        }









