package com.example.game;
import java.util.Random;

public class TileInitializer {
    private static final Random random = new Random();
    public static void initialize(int[][]board){
        addTile(board);
        addTile(board);

    }
    public static void addTile(int[][]board){
        int row,col;
        do{
            row= random.nextInt(board.length);
            col= random.nextInt(board.length);
        }while(board[row][col]!=0);
        board[row][col]=random.nextInt(10)<9?2:4;

    }
}
