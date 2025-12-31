package org.example;

import java.util.*;

/**
 * A professional implementation of a console-based 3x3 Tic-Tac-Toe game.
 */
public class TicTacToe {

    private static String[] board;
    private static String turn;

    // Constants for better maintainability
    private static final String PLAYER_X = "X";
    private static final String PLAYER_O = "O";
    private static final String DRAW = "draw";

    // Winning combinations: 3 rows, 3 columns, 2 diagonals
    private static final int[][] WINNING_COMBINATIONS = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Rows
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Columns
            {0, 4, 8}, {2, 4, 6}             // Diagonals
    };

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        board = new String[9];
        turn = PLAYER_X;
        String winner = null;

        // Initialize board with numbers 1-9
        for (int a = 0; a < 9; a++) {
            board[a] = String.valueOf(a + 1);
        }

        System.out.println("Welcome to 3x3 Tic Tac Toe.");
        printBoard();
        System.out.println("X will play first. Enter a slot number to place X in:");

        while (winner == null) {
            int numInput;

            try {
                numInput = in.nextInt();

                if (!(numInput > 0 && numInput <= 9)) {
                    System.out.println("Invalid input; re-enter slot number (1-9):");
                    continue;
                }

                // If the slot contains the original number, it is available
                if (board[numInput - 1].equals(String.valueOf(numInput))) {
                    board[numInput - 1] = turn;

                    // Switch turn
                    turn = turn.equals(PLAYER_X) ? PLAYER_O : PLAYER_X;

                    printBoard();
                    winner = checkWinner();
                } else {
                    System.out.println("Slot already taken; re-enter slot number:");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input; please enter a number (1-9):");
                in.nextLine(); // Clear buffer
            }
        }

        displayFinalResult(winner);
        in.close();
    }

    /**
     * Examines the board to determine if a player has won or if it's a draw.
     * @return The winner ("X" or "O"), "draw", or null if the game continues.
     */
    static String checkWinner() {
        // Check all winning combinations
        for (int[] combo : WINNING_COMBINATIONS) {
            if (board[combo[0]].equals(board[combo[1]]) &&
                    board[combo[1]].equals(board[combo[2]])) {
                return board[combo[0]];
            }
        }

        // Check for a draw (if any slot still contains a number, game is not over)
        for (int a = 0; a < 9; a++) {
            if (board[a].equals(String.valueOf(a + 1))) {
                System.out.println(turn + "'s turn; enter a slot number to place " + turn + " in:");
                return null;
            }
        }

        return DRAW;
    }

    /**
     * Prints the current state of the board to the console.
     */
    static void printBoard() {
        System.out.println("|---|---|---|");
        System.out.println("| " + board[0] + " | " + board[1] + " | " + board[2] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[3] + " | " + board[4] + " | " + board[5] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[6] + " | " + board[7] + " | " + board[8] + " |");
        System.out.println("|---|---|---|");
    }

    private static void displayFinalResult(String winner) {
        if (winner.equalsIgnoreCase(DRAW)) {
            System.out.println("It's a draw! Thanks for playing.");
        } else {
            System.out.println("Congratulations! " + winner + "'s have won! Thanks for playing.");
        }
    }
}