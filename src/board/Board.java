package board;

import pieces.*;

/**
 * board.Board represent the chess board itself which include the space and coordinate for each cell.
 * @author Yifei Li, yl1160,
 * @author Yifan Zhang, yz745
 */
public class Board implements Cloneable{
    int size = 8;

    private Cell[][] board;

    /**
     * default constructor that create a 8*8 chess board. Initializing each cell with name and piece.
     * @author Yifei Li, yl1160
     *         Yifan Zhang, yz745
     */


    public Board(){             //board constructor
        board = new Cell[size][size];
        for(int i = 0;i < size;i++){
            for(int j = 0;j < size;j++){

                board[i][j] = new Cell(i, j, i%2 == j%2);

            }
        }
        for(int i = 0;i < size;i++){
            for(int j = 0;j < size;j++) {
                if(i == 0||i == 1){
                    if(i == 0) {
                        if(j == 0||j == 7){
                            board[i][j].setName("wR");
                            board[i][j].setPiece(new Rook("Rook",true,false));
                            board[i][j].setIfEmpty(false);
                        }
                        else if(j == 1||j == 6){
                            board[i][j].setName("wN");
                            board[i][j].setPiece(new Knight("Knight",true));
                            board[i][j].setIfEmpty(false);
                        }
                        else if(j == 2||j == 5){
                            board[i][j].setName("wB");
                            board[i][j].setPiece(new Bishop("Bishop",true));
                            board[i][j].setIfEmpty(false);
                        }
                        else if(j == 3){
                            board[i][j].setName("wQ");
                            board[i][j].setPiece(new Queen("Queen",true));
                            board[i][j].setIfEmpty(false);
                        }
                        else if(j == 4){
                            board[i][j].setName("wK");
                            board[i][j].setPiece(new King("King",true,false,false));
                            board[i][j].setIfEmpty(false);
                        }
                    }
                    else if(i == 1){
                        board[i][j].setName("wP");
                        board[i][j].setPiece(new Pawn("Pawn",true,null,true,false));
                    }
                }
                if(i == 6||i == 7){
                    if(i == 7) {
                        if(j == 0||j == 7){
                            board[i][j].setName("bR");
                            board[i][j].setPiece(new Rook("Rook",false,false));
                            board[i][j].setIfEmpty(false);
                        }
                        else if(j == 1||j == 6){
                            board[i][j].setName("bN");
                            board[i][j].setPiece(new Knight("Knight",false));
                            board[i][j].setIfEmpty(false);
                        }
                        else if(j == 2||j == 5){
                            board[i][j].setName("bB");
                            board[i][j].setPiece(new Bishop("Bishop",false));
                            board[i][j].setIfEmpty(false);
                        }
                        else if(j == 3){
                            board[i][j].setName("bQ");
                            board[i][j].setPiece(new Queen("Queen",false));
                            board[i][j].setIfEmpty(false);
                        }
                        else if(j == 4){
                            board[i][j].setName("bK");
                            board[i][j].setPiece(new King("King",false,false,false));
                            board[i][j].setIfEmpty(false);
                        }
                    }
                    else if(i == 6){
                        board[i][j].setName("bP");
                        board[i][j].setPiece(new Pawn("Pawn",false,null,true,false));

                    }
                }
            }
        }
    }

    /**
     *
     * @param x x coordinates, row number of chess board
     * @param y y coordinates, column number of chess board
     * @return The cell with row x and column y
     */
    public Cell getCell(int x,int y){
        return board[x][y];
    }

    /**
     *
     * @param cell Cell instance that need to put in certain place
     * @param x x coordinates, row number of chess board
     * @param y y coordinates, column number of chess board
     */
    public void setCell(Cell cell,int x, int y){
        board[x][y] = cell;
    }


    /**
     * a method that print the chess board. should be used after every valid input.
     */
    public void show(){
        String alpha = "abcdefgh";
        for(int i = size-1;i >= 0;i--){
            for(int j = 0;j < size;j++){
                if(board[i][j].getIfEmpty()){
                    System.out.print(board[i][j].getBlack() + "\t");
                }else{
                    System.out.print(board[i][j].getName() + "\t");
                }

                //System.out.print(" " + board[i][j].getRow() + board[i][j].getCol());
            }
            System.out.println(" "+(i+1));
        }
        for (int i = 0; i < size; i ++){
            System.out.print(alpha.charAt(i) + "\t");
        }
        System.out.println();
    }


    /**
     * make a deep clone of the board instance
     * @return return a board instance
     * @throws CloneNotSupportedException when clone is not supported
     */
    public Board clone() throws CloneNotSupportedException {

        //Board b = (Board)super.clone();
        Board b = new Board();
        for (int i = 0; i < this.board.length; i ++) {
            for (int j = 0; j < this.board[0].length; j ++) {
                b.board[i][j] = this.board[i][j].clone();
            }

        }
        return b;

    }


}
