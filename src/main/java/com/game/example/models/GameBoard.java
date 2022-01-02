package com.game.example.models;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class GameBoard {
    char[][] board;
    int boardSize;
    Queue<Player> nextTurn;
    Scanner input;

    GameBoard(int boardSize, Player[] player) {
        this.boardSize = boardSize;
        this.board = new char[boardSize][boardSize];
        initialise(board);
        nextTurn = new LinkedList<Player>();
        nextTurn.offer(player[0]);
        nextTurn.offer(player[1]);
        input = new Scanner(System.in);
    }

    private void initialise(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            Arrays.fill(board[i], '-');
        }
    }

    private void printBoard(){
        for(char[] row : board){
            for(char col:row){
                System.out.print(col);
            }
            System.out.println();
        }
    }

    public void startGame(){
        int count=0;
        while(true){
            count++;
            if(count==(boardSize*boardSize)){
                System.out.println("Match Draw");
                break;
            }
            Player p = nextTurn.poll();
            System.out.println(p.getPlayerName() +"Please enter position");
            int x = input.nextInt();
            int y = input.nextInt();
            while(!validate(x,y)){
                System.out.println("Please enter correct input");
                 x = input.nextInt();
                 y = input.nextInt();
            }
          board[x-1][y-1] = p.getPlayerSymbol();
            printBoard();
            if(count>=boardSize && checkEndGame(p,x,y)) break;
            nextTurn.offer(p);
        }
    }

    private boolean checkEndGame(Player p,int x,int y){
        String winString="";
        StringBuilder sb = new StringBuilder();
        for(int i =0;i<boardSize;i++){
            sb.append(p.getPlayerSymbol());
        }
        winString = sb.toString();

        String rowString="";
        String colString="";
        String diagonalString="";
        String reverseDiagonalString="";

        for(int i =0;i<boardSize;i++){
            rowString +=board[x-1][i];
            colString+=board[i][y-1];
            if(x==y){
                diagonalString+=board[i][i];
            }

            if((x+y)== board.length+1){
                reverseDiagonalString += board[board.length-1-i][i];
            }
        }

        if(winString.equals(rowString) || winString.equals(colString) || winString.equals(diagonalString) || winString.equals(reverseDiagonalString)){
            System.out.println(p.getPlayerName()+" has won the Game");
            return true;
        }

        return false;
    }

    private boolean validate(int x,int y){
        if(x<0 || y<0 || x>boardSize || y>boardSize)
            return false;

        if(board[x-1][y-1]!='-')
            return false;

        return true;
    }
}
