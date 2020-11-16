package board;
import pieces.*;

/**
 * Cells represent each block in the chess board. Each board.Cell contain the coordinate
 * of row,col,col. Contains the name of the piece such as "wp","bk".
 * Empty indicator to check if each cell is empty or not.
 * A char to indicate its a white cell or a black board.
 * And each board.Cell could store a piece.
 * Each Cell store the en passant status for current cell.
 * @author yl1160
 */
public class Cell implements Cloneable{
    private char black;     //if it's a black block
    private boolean ifEmpty;
    private int row;
    private int col;
    private char colC;
    private Pieces piece;    //role
    private String name;        //name is only for display
    private int enpassingRound;
    /*enpassingRound is set to one when Pawn move over this cell in its first step
    once enpassingRound is set to one, every round it increments.
    */

    /**
     * setter for en passant number that increment number by one
     */
    public void setEnpassingRound(){
        this.enpassingRound = this.enpassingRound+ 1;
    }

    /**
     * setter for en passant number
     * @param i number to update
     */
    public void setEnpassingRound(int i){enpassingRound = i;}

    /**
     * getter for en passant number
     * @return en passant number
     */
    public int getEnpassingRound(){
        return enpassingRound;
    }

    /**
     * reset en passant number to 0
     */
    public void resetEnpassingRound(){
        enpassingRound = 0;
    }

    /**
     * setter for piece number
     * @param name name to update
     */
    public void setName(String name){
        this.ifEmpty = false;
        this.name = name;
    }

    /**
     * Cell constructor
     * @param r row number
     * @param c column number
     * @param isBlack color of the cell
     */
    public Cell(int r, int c, boolean isBlack){
        setRow(r);
        setCol(c);
        this.ifEmpty = true;
        if (isBlack){
            this.black = '#';
        }else{
            this.black =  ' ';
        }
    }

    /**
     * setter for row number.
     * @param row row number
     */

    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Setter for column, column is represent by letter from a to h.
     * @param col column number
     */
    public void setCol(int col){
        this.col = col;
        if(col == 0) this.colC = 'a';
        if(col == 1) this.colC = 'b';
        if(col == 2) this.colC = 'c';
        if(col == 3) this.colC = 'd';
        if(col == 4) this.colC = 'e';
        if(col == 5) this.colC = 'f';
        if(col == 6) this.colC = 'g';
        if(col == 7) this.colC = 'h';
    }

    /**
     * setter for piece
     * @param name Piece instance
     */
    public void setPiece(Pieces name){
        this.ifEmpty = false;
        this.piece = name;
    }

    /**
     * setter for ifEmpty indicator
     * @param ifEmpty A boolean that indicate weather a cell is empty or not.
     */
    public void setIfEmpty(boolean ifEmpty){
        this.ifEmpty = ifEmpty;
    }

    /**
     * Remove the Piece in this Cell.
     */
    public void removePiece(){
        this.ifEmpty = true;
        this.piece = null;
    }

    /**
     * setter for color
     * @param black char that indicate color ' ' for white '#' for black
     */
    public void setBlack(char black){this.black = black;}

    /**
     * getter for piece
     * @return Piece in this Cell
     */
    public Pieces getPiece(){
        return piece;
    }

    /**
     * getter for name
     * @return Name that indicate the piece in this Cell such as "wP" is white Pawn.
     */
    public String getName(){
        return name;
    }

    /**
     * getter for row number
     * @return row number
     */
    public int getRow(){
        return row;
    }

    /**
     * getter for column number
     * @return column number
     */
    public int getCol(){
        return col;
    }

    /**
     * getter for isEmpty
     * @return true if there is a Piece in the cell, false otherwise.
     */
    public boolean getIfEmpty(){
        return ifEmpty;
    }

    /**
     * getter for color indicator
     * @return ' ' for empty cell, '#' for black cell
     */
    public char getBlack(){
        return black;
    }

    /**
     * make a deep copy of Cell instance
     * @return Cell instance
     */
    @Override

    public Cell clone(){
        try {
            Cell cell = (Cell)super.clone();
            if(!this.getIfEmpty())
            cell.piece = this.piece.clone();

            return cell;
        } catch (CloneNotSupportedException e){
            throw new AssertionError();
        }
    }

}