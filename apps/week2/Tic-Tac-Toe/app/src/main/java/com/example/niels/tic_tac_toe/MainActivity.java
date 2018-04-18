package com.example.niels.tic_tac_toe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Game game;
    private List<Integer> idList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        game = new Game();

    }

    public void tileClicked(View view) {
        int id = view.getId();
        Button button = (Button) view;
        String tag = view.getTag().toString();
        int place = Integer.parseInt(tag);


        idList.add(id);

        int row = place / 3;
        int column = place % 3;

        Tile tile = game.draw(row, column);

        switch(tile) {
            case CROSS:
                button.setText("X");
                break;
            case CIRCLE:
                button.setText("O");
                break;
            case INVALID:
                if (game.playerOneTurn) {
                    game.playerOneTurn = false;
                }
                else{
                    game.playerOneTurn = true;
                }
                break;
        }

        game.movesPlayed += 1;

        if (game.movesPlayed > 1) {
            if (game.gameWon()) {
                resetClicked(view);
            }
        }


    }

    public void resetClicked(View view) {

            game = new Game();
            for (int id : idList) {
                Button button = findViewById(id);
                button.setText("");
            }

        }




}
