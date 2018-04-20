package com.example.niels.tic_tac_toe;

import java.io.Serializable;

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

    public Game() {
        board = new Tile[BOARD_SIZE][BOARD_SIZE];
        for(int i=0; i<BOARD_SIZE; i++)
            for(int j=0; j<BOARD_SIZE; j++)
                board[i][j] = BLANK;

        playerOneTurn = true;
    }

    public Tile draw(int row, int column) {
        /*
        Checks if the clicked space is blank and then updates the board and returns the tile
        depending on who's turn it is
        */
        if (board[row][column] == BLANK) {

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

        // If tile was already filled, return INVALID
        return INVALID;
    }

    public Tile gameWon() {
        // Checks if the game is won and by who, returns CROSS for player 1 and CIRCLE for player 2
        int check_hor = 0;
        int check_ver = 0;
        int check_diag = 0;
        int check_diag1 = 0;

        // First checks if CIRCLE has won, then CROSS
        List<Tile> tiles = Arrays.asList(CIRCLE, CROSS);
        for (Tile tile : tiles) {
            for (int i = 0; i < BOARD_SIZE; i++) {
                for (int j = 0; j < BOARD_SIZE; j++) {

                    // Checks if win on horizontal
                    if (board[i][j] == tile) {
                        check_hor += 1;
                    }

                    // Checks if win on vertical
                    if (board[j][i] == tile) {
                        check_ver += 1;
                    }

                    // Checks if win on diagonal from top left to bottom right
                    if (board[j][j] == tile) {
                        check_diag += 1;
                    }

                    // Checks if win on diagonal from bottom left to top right
                    if (board[2 - j][j] == tile) {
                        check_diag1 += 1;
                    }
                }

                // Returns the tile of the winner if there is a winner
                if (check_hor == 3 || check_ver == 3 || check_diag == 3 || check_diag1 == 3) {
                    return tile;
                }

                // If there is no winner, set all counters back to 0
                check_hor = 0;
                check_ver = 0;
                check_diag = 0;
                check_diag1 = 0;
            }
        }

     return INVALID;
    }


}
