package com.example.niels.tic_tac_toe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.example.niels.tic_tac_toe.Tile.CIRCLE;
import static com.example.niels.tic_tac_toe.Tile.CROSS;
import static com.example.niels.tic_tac_toe.Tile.INVALID;

public class MainActivity extends AppCompatActivity {

    Game game;
    private ArrayList<Integer> idList = new ArrayList<>();
    private boolean playing = true;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        game = new Game();
        TextView log = findViewById(R.id.textView);
        log.setText("Turn: player one");

    }

    public void tileClicked(View view) {
        int id = view.getId();
        String tag = view.getTag().toString();
        int place = Integer.parseInt(tag);
        TextView log = findViewById(R.id.textView);

        if (playing) {
            idList.add(id);

            int row = place / 3;
            int column = place % 3;

            Tile tile = game.draw(row, column);
            System.out.println(tile);
            switch (tile) {
                case CROSS:
                    view.setBackgroundResource(R.drawable.cross);
                    log.setText("Turn: player two");
                    break;
                case CIRCLE:
                    view.setBackgroundResource(R.drawable.circle);
                    log.setText("Turn: player one");
                    break;
                case INVALID:
                    if (game.playerOneTurn) {
                        game.playerOneTurn = true;
                    } else {
                        game.playerOneTurn = false;
                    }
                    break;
            }
            game.movesPlayed += 1;

            if (game.gameWon() == CROSS) {
                playing = false;
                log.setText("PLAYER ONE WON");
            }

            else if (game.gameWon() == CIRCLE) {
                playing = false;
                log.setText("PLAYER TWO WON");
            }
        }

    }

    public void resetClicked(View view) {

            game = new Game();
            for (int id : idList) {
                ImageButton button = findViewById(id);
                button.setBackgroundResource(R.drawable.square);
            }
            idList.clear();
            playing = true;
            TextView log = findViewById(R.id.textView);
            log.setText("Turn: player one");
        }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable("game", game);
        outState.putIntegerArrayList("idList", idList);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        idList = savedInstanceState.getIntegerArrayList("idList");
        game = (Game) savedInstanceState.getSerializable("game");

        for (int id : idList) {
            ImageButton button = findViewById(id);
            String tag = button.getTag().toString();
            int place = Integer.parseInt(tag);
            int row = place / 3;
            int column = place % 3;
            if (game.board[row][column] == CROSS) {
                button.setBackgroundResource(R.drawable.cross);
            }
            else {
                button.setBackgroundResource(R.drawable.circle);
            }

        }

    }
}
