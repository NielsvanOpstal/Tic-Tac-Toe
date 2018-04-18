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
    private List<Integer> idList = new ArrayList<>();
    private boolean playing = true;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        game = new Game();


    }

    public void tileClicked(View view) {
        int id = view.getId();
        String tag = view.getTag().toString();
        int place = Integer.parseInt(tag);
        TextView log = findViewById(R.id.textView2);

        if (playing) {
            idList.add(id);

            int row = place / 3;
            int column = place % 3;

            Tile tile = game.draw(row, column);
            System.out.println(tile);
            switch (tile) {
                case CROSS:
                    view.setBackgroundResource(R.drawable.cross);
                    break;
                case CIRCLE:
                    view.setBackgroundResource(R.drawable.circle);
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
        }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("game", game);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        game = (Game) savedInstanceState.getSerializable("game");
        int counter = 0;
        for (int i = 0; i < game.BOARD_SIZE; i++ ) {
            for (int j = 0; j < game.BOARD_SIZE; j++) {
                if (game.board[i][j] == CROSS) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("imageButton");
                    sb.append(counter);
                    String id = sb.toString();
                    System.out.print(id);
                    ImageButton button = findViewById(getResources().getIdentifier(id, "id", getPackageName()));
                    button.setBackgroundResource(R.drawable.cross);
                    }
                if (game.board[i][j] == CIRCLE) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("imageButton");
                    sb.append(counter);
                    String id = sb.toString();
                    ImageButton button = findViewById(getResources().getIdentifier(id, "id", getPackageName()));
                    button.setBackgroundResource(R.drawable.circle);
                }
                counter += 1;
            }
        }

    }
}
