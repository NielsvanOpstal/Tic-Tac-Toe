package com.example.niels.tic_tac_toe;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.niels.tic_tac_toe.Tile.BLANK;
import static com.example.niels.tic_tac_toe.Tile.CIRCLE;
import static com.example.niels.tic_tac_toe.Tile.CROSS;
import static com.example.niels.tic_tac_toe.Tile.INVALID;

public class Game implements Serializable {
    final public int BOARD_SIZE = 3;
    public Tile[][] board;
    public Boolean playerOneTurn;  // true if player 1's turn, false if player 2's turn
    public int movesPlayed;
    private Boolean gameOver;

    private int check_hor;
    private int check_ver;
    private int check_diag;
    private int check_diag1;

    public Game() {
        board = new Tile[BOARD_SIZE][BOARD_SIZE];
        for(int i=0; i<BOARD_SIZE; i++)
            for(int j=0; j<BOARD_SIZE; j++)
                board[i][j] = BLANK;

        playerOneTurn = true;
        gameOver = false;
    }

    public Tile draw(int row, int column) {

        if (board[row][column] == BLANK) {

            System.out.println(row);
            System.out.println(column);

            if (playerOneTurn) {
                board[row][column] = CROSS;
                playerOneTurn = false;
                return CROSS;
            }
            else {
                board[row][column] = CIRCLE;
                playerOneTurn = true;
                return CIRCLE;
            }
        }
        return INVALID;
    }

    public Tile gameWon() {


        List<Tile> tiles = Arrays.asList(CIRCLE, CROSS);
        //Checks the horizontal and vertical possibilities to win
        for (Tile tile : tiles) {
            for (int i = 0; i < BOARD_SIZE; i++) {
                for (int j = 0; j < BOARD_SIZE; j++) {

                    if (board[i][j] == tile) {
                        check_hor += 1;
                    }
                    if (board[j][i] == tile) {
                        check_ver += 1;
                    }
                    if (board[j][j] == tile) {
                        check_diag += 1;
                    }
                    if (board[2 - j][j] == tile) {
                        check_diag1 += 1;
                    }
                }

                if (check_hor == 3 || check_ver == 3 || check_diag == 3 || check_diag1 == 3) {
                    return tile;
                }
                check_hor = 0;
                check_ver = 0;
                check_diag = 0;
                check_diag1 = 0;
            }
        }

     return INVALID;
    }


}
