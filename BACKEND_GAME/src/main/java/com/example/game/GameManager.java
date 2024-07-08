package com.example.game;
import org.springframework.stereotype.Service;
import java.util.Scanner;
//The @Service annotation marks the GameManager class as a Spring-managed bean. This tells Spring to create an instance of this class and manage its lifecycle.
@Service
public class GameManager {
    private static final int size=4;
    final int[][]board;
    boolean isGameOver;
    public GameManager(){
        board=new int[size][size];
        isGameOver=false;
        com.example.game.TileInitializer.initialize(board);
    }
    public void startGame(){
        Scanner sc=new Scanner(System.in);
        while(!isGameOver){
            printBoard();
            System.out.println("Enter the move :W/A/S/D");
            char ch=sc.next().charAt(0);
            boolean moved=false;
            switch(ch){
                case 'W':
                    moved= moveUp();break;
                case 'A':
                    moved= moveLeft();break;
                case 'S':
                    moved=moveDown();
                    break;
                case 'D':
                    moved=moveRight();
                    break;
                default:
                    System.out.println("Invalid move!");

            }
            if (moved) {
                com.example.game.TileInitializer.addTile(board);
                if(checkWin()){
                    isGameOver=true;
                    printBoard();
                    System.out.println("Congratulations! You've reached 2048!");
                }
                else if(isOver()){
                    isGameOver=true;
                    printBoard();
                    System.out.println("Game Over! No more valid moves.");
                }
//
//
            }
        }
    }

    private boolean checkWin(){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == 2048) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean isOver(){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
                if (i < size - 1 && board[i][j] == board[i + 1][j]) {
                    return false;
                }
                if (j < size - 1 && board[i][j] == board[i][j + 1]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean moveUp(){
        boolean moved=false;
        for(int col=0;col<size;col++){
            //if already merged not merge again
            boolean isMerged=false;
            int index=0;
            int[]newCol=new int[size];
            for(int row=0;row<size;row++){
                if(board[row][col]!=0){
                    if(index>0 && newCol[index-1]==board[row][col] && !isMerged){  //check if curr and stored in newCol are equal so merge
                        newCol[index-1]*=2;
                        moved=true;
                        isMerged=true;

                    }
                    else{
                        newCol[index]=board[row][col];
                        if(row!=index){
                            moved=true; //suppose 2 2 4 8 is col and new arr 4 0 0 0 row=2 and ind=1,so we have to move 4 up
                        }
                        isMerged=false;
                        index++;
                    }
                }
            }
            for(int i=0;i<size;i++){
                board[i][col]=newCol[i];
            }

        }
        return moved;
    }
    public boolean moveDown(){
        boolean moved=false;
        for(int col=0;col<size;col++){
            boolean isMerged=false;
            int idx=size-1;
            int[]newCol=new int[size];
            for(int row=size-1;row>=0;row--){
                if (board[row][col] != 0) {
                    if (idx < size-1 && newCol[idx + 1] == board[row][col] && !isMerged) {
                        newCol[idx + 1] *= 2;
                        moved = true;
                        isMerged=true;
                    } else {
                        newCol[idx] = board[row][col];
                        if (idx != row) {
                            moved = true;
                        }
                        idx--;
                        isMerged=false;
                    }
                }

            }
            for(int i=0;i<size;i++){
                board[i][col]=newCol[i];
            }

        }
        return moved;
    }
    public boolean moveLeft(){
        boolean moved=false;
        for(int row=0;row<size;row++){
            boolean isMerged=false;
            int idx=0;
            int[]newRow=new int[size];
            for(int col=0;col<size;col++){
                if(board[row][col]!=0) {
                    if (idx > 0 && newRow[idx - 1] == board[row][col] && !isMerged) {
                        newRow[idx-1] *= 2;
                        moved = true;
                        isMerged=true;
                    } else {
                        newRow[idx] = board[row][col];
                        if (idx != col) {
                            moved = true;
                        }
                        isMerged=false;
                        idx++;
                    }
                }
            }
            for(int col=0;col<size;col++){
                board[row][col]=newRow[col];
            }
        }
        return moved;
    }
    public boolean moveRight(){
        boolean moved=false;
        for(int row=0;row<size;row++){
            boolean isMerged=false;
            int idx=size-1;
            int[]newRow=new int[size];
            for(int col=size-1;col>=0;col--){
                if(board[row][col]!=0){
                    if(idx<size-1 && board[row][col]==newRow[idx+1] && !isMerged){
                        newRow[idx+1]*=2;
                        moved=true;
                        isMerged=true;
                    }
                    else{
                        newRow[idx]=board[row][col];
                        if(idx!=col){
                            moved=true;
                        }
                        isMerged=false; //as agr phele 2 2 4 4 h to 4 4 0 4 newCol h ye to ab to hm merge kr skte h
                        idx--;
                    }
                }
            }
            for(int i=0;i<size;i++){
                board[row][i]=newRow[i];
            }
        }
        return moved;
    }


    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(getColoredTile(board[i][j]) + " ");
            }
            System.out.println();
        }
    }
    public int[][] getBoard() {
        return board;
    }

    private String getColoredTile(int value) {
        String color;
        switch (value) {
            case 2:
                color = "\u001B[30m"; // Black
                break;
            case 4:
                color = "\u001B[34m"; // Blue
                break;
            case 8:
                color = "\u001B[32m"; // Green
                break;
            case 16:
                color = "\u001B[36m"; // Cyan
                break;
            case 32:
                color = "\u001B[33m"; // Yellow
                break;
            case 64:
                color = "\u001B[31m"; // Red
                break;
            case 128:
                color = "\u001B[35m"; // Magenta
                break;
            case 256:
                color = "\u001B[90m"; // Bright Black (Dark Gray)
                break;
            case 512:
                color = "\u001B[94m"; // Bright Blue
                break;
            case 1024:
                color = "\u001B[92m"; // Bright Green
                break;
            case 2048:
                color = "\u001B[96m"; // Bright Cyan
                break;
            default:
                color = "\u001B[0m"; // Reset
        }
        return color + value + "\u001B[0m";
    }
    public void addTile() {
        com.example.game.TileInitializer.addTile(board);
    }
}