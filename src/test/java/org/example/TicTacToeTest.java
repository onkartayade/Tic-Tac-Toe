package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;

class TicTacToeTest {

    // Helper to reset the board using Reflection since the board is private
    @BeforeEach
    void setup() throws Exception {
        Field boardField = TicTacToe.class.getDeclaredField("board");
        boardField.setAccessible(true);
        String[] newBoard = new String[9];
        for (int i = 0; i < 9; i++) {
            newBoard[i] = String.valueOf(i + 1);
        }
        boardField.set(null, newBoard);
    }

    @Test
    void testHorizontalWin() throws Exception {
        setBoard(new String[]{"X", "X", "X", "4", "5", "6", "7", "8", "9"});
        assertEquals("X", TicTacToe.checkWinner(), "Should detect horizontal win for X");
    }

    @Test
    void testVerticalWin() throws Exception {
        setBoard(new String[]{"O", "2", "3", "O", "5", "6", "O", "8", "9"});
        assertEquals("O", TicTacToe.checkWinner(), "Should detect vertical win for O");
    }

    @Test
    void testDiagonalWin() throws Exception {
        setBoard(new String[]{"X", "2", "3", "4", "X", "6", "7", "8", "X"});
        assertEquals("X", TicTacToe.checkWinner(), "Should detect diagonal win for X");
    }

    @Test
    void testDraw() throws Exception {
        setBoard(new String[]{"X", "O", "X", "X", "O", "O", "O", "X", "X"});
        assertEquals("draw", TicTacToe.checkWinner(), "Should detect a draw");
    }

    // Helper method to inject board states
    private void setBoard(String[] state) throws Exception {
        Field boardField = TicTacToe.class.getDeclaredField("board");
        boardField.setAccessible(true);
        boardField.set(null, state);
    }
}