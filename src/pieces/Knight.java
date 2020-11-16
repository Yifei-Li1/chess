package pieces;

import board.Board;
import board.Cell;
import pieces.Pieces;

/**
 * Knight object, one kind of piece
 * @author Yifan Zhang, yz745
 */
public class Knight extends Pieces {
    private String type;

    /**
     * Constructor for Knight piece
     * @param type type for Knight is always "Knight"
     * @param isWhite true for white, false for black
     */
    public Knight(String type,boolean isWhite){
        super(type,isWhite,null,false,false,false,false);
        //his.type = type;
    }

    /**
     * make a deep copy of given Knight
     * @return a deep copy of given instance
     */
    public Knight clone(){
        return new Knight(this.getType(),this.isWhite());

    }

    @Override
    public boolean rule(Board board, Cell start, Cell dest, String promote) {
        if (dest.getPiece()!=null&&dest.getPiece().isWhite() == this.isWhite()) {
            return false;
        }
        int x = Math.abs(start.getRow() - dest.getRow());
        int y = Math.abs(start.getCol() - dest.getCol());
        if(x * y == 2){
            //pieces.Knight can only move 1 * 2 or 2 * 1
            return true;
        }
        else{
            return false;
        }
    }

}
