package pieces;

import board.Board;
import board.Cell;
import pieces.Pieces;

/**
 * King object, one kind of piece
 * @author Yifei Li, yl1160
 * @author Yifan Zhang, yz
 */
public class King extends Pieces implements Cloneable{

    //private boolean hasMoved = false;
     //king can only castling once

    /**
     * constructor for King object
     * @param type "King"
     * @param isWhite true for white, false for black
     * @param hasMoved true if not moved, false otherwise
     * @param underCheck true if King is under check, false otherwise
     */
    public King(String type,boolean isWhite,boolean hasMoved,boolean underCheck){
        super(type,isWhite,null,hasMoved,false,false,underCheck);

    }



    @Override
    public boolean rule(Board board, Cell start, Cell dest, String promote) {
        if (dest.getPiece()!=null&&dest.getPiece().isWhite() == this.isWhite()) {
            return false;
        }
        int absX = Math.abs(start.getRow() - dest.getRow());
        int absY = Math.abs(start.getCol() - dest.getCol());

        if(absX + absY == 1||(absX==1&&absY==1)){
            //pieces King can move one step at a time
            setHasMoved(true);
            return true;
        }
        else if(absX==0&&absY==2&&!getHasMoved()&&!getCastlingDone()&&!getUnderCheck()){      //check if this is a castling move
            //System.out.println("this is a castling move");
            if(isValidCastling(board,start,dest)){          //valid Castling move
                setCastlingDone(true);
                return true;
            }
            else{                                           //invalid castling move
                return false;
            }
        }
        else{
            return false;
        }
    }

    /**
     * check if king can castling
     * @param board current chess board
     * @param start king location
     * @param dest king destination
     * @return true if valid, false otherwise
     */
    public boolean isValidCastling(Board board,Cell start,Cell dest){
        int y = dest.getCol() - start.getCol();
        boolean rookInP = false;    //rook in place
        boolean anythingInBetween = false;
        //check if rook is in corresponding position
        if(y==2&&dest.getCol()+1<=8&&!board.getCell(dest.getRow(),dest.getCol()+1).getIfEmpty()&&board.getCell(dest.getRow(),dest.getCol()+1).getPiece().getType().equals("Rook")
        &&!board.getCell(dest.getRow(),dest.getCol()+1).getPiece().getHasMoved() ){
            //right castling
            //System.out.println("right castling");
            rookInP = true;
            for(int i = 1;i < 3;i++){
                anythingInBetween = !board.getCell(start.getRow(),start.getCol()+i).getIfEmpty();
                if(anythingInBetween) break;
            }
        }
        else if(y==-2&&dest.getCol()-2>=0&&!board.getCell(dest.getRow(),dest.getCol()-2).getIfEmpty()&&board.getCell(dest.getRow(),dest.getCol()-2).getPiece().getType().equals("Rook")
        &&!board.getCell(dest.getRow(),dest.getCol()-2).getPiece().getHasMoved()){
            //left castling
            rookInP = true;
            for(int i = 1;i < 4;i++){
                anythingInBetween = !board.getCell(start.getRow(),start.getCol()-i).getIfEmpty();
                if(anythingInBetween) break;
            }
        }
        if(rookInP&&!anythingInBetween){    //rook is in position, and nothing in between
            //System.out.println("can castling");
            setCastlingDone(true);
            setHasMoved(true);
            return true;
        }
        else{   //rook not in correct position not moved
            return false;
        }


    }
    public King clone(){
        return new King(this.getType(),this.isWhite(),this.getHasMoved(),this.getUnderCheck());

    }
}
