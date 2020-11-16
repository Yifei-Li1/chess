package pieces;

import board.Board;
import board.Cell;
import pieces.Pieces;

/**
 * Pawn object, one kind of Piece
 * @author Yifei Li,yl1160
 * @author Yifan Zhang,yz745
 */
public class Pawn extends Pieces {
    //private String type;


    /**
     * constructor for Pawn instance
     * @param type "Pawn"
     * @param isWhite true for white, false for black
     * @param promoteRole null by default, indicate what kind of pice this pawn going to promoted to.
     * @param isFirstmove true if never moved, false otherwise
     * @param promoted true if promoted, false otherwise
     */

    public Pawn(String type, boolean isWhite, String promoteRole,boolean isFirstmove,boolean promoted) {
        super(type, isWhite, promoteRole,false,isFirstmove,promoted,false);
        //this.type = type;
    }

    /**
     * make a deep copy of Pawn instance
     * @return a deep copy of given Pawn instance
     */

    public Pawn clone(){
        return new Pawn(this.getType(),this.isWhite(),this.getPromoteRole(),this.getFirstmove(),this.getPromoted());

    }





    @Override
    public boolean rule(Board board, Cell start, Cell dest, String promote) {
        boolean returnValue;
        if (dest.getPiece() != null && dest.getPiece().isWhite() == this.isWhite()) {
            //dest has same color as start
            returnValue = false;
        }
else{

        int x = dest.getRow() - start.getRow();             //pawn can only move forward
        int y = dest.getCol() - start.getCol();
        int absY = Math.abs(start.getCol() - dest.getCol());
        if (this.isWhite()) {
            //it's a white pawn, white pawn can only move from low row to high row

                if (this.getFirstmove()) {
                    //pawn's first move

                    if (absY == 0 && (x == 1 || x == 2)) {
                        if(x==1){       //check anything in between
                            if(!dest.getIfEmpty()) {
                                returnValue = false;
                            }
                            else{
                                this.setFirstmove(false);
                                returnValue = true;}
                        }
                        else { //x = 2
                            //in first move, if move forward, can't have any piece in between
                            if(!board.getCell(start.getRow()+1,start.getCol()).getIfEmpty()) {
                                returnValue =  false;}
                            else if(!dest.getIfEmpty()) {
                                returnValue =  false;}
                            else{
                                board.getCell(start.getRow()+1,start.getCol()).setEnpassingRound();
                                int row = start.getRow()+1;
                                //System.out.println("row: "+row+"  col: "+start.getCol());

                                setFirstmove(false);
                                //System.out.println("first move: "+getFirstmove());
                                returnValue =  true;
                            }

                        }


                    } else if((absY == 1 && x == 1)) {
                        //capture diagnosisly

                        if(dest.getIfEmpty()){
                            returnValue = false;}
                        else{
                            returnValue =  true;}
                    }else{
                        returnValue =  false;
                    }


                }

                else {
                    //not first move
                    if ((absY == 1 && x == 1)||(absY==0&&x==1)) {  //valid move
                        if(absY == 1 ){    //斜着走
                            if(dest.getIfEmpty()){  //斜着走必须吃
                                if(dest.getEnpassingRound()==2){
                                    returnValue = true;
                                }
                                else{
                                    returnValue =  false;
                                }

                            }
                            else{
                                returnValue =  true;
                            }
                        }
                        else{   //absY==0&&x==1
                            if(dest.getIfEmpty()){
                                returnValue =  true;
                            }
                            else{
                                returnValue =  false;
                            }

                        }
                        if (dest.getRow() == 7) {
                            //pawn reaches the top row
                            if (promote == null) {
                                setPromoteRole("Queen");
                            } else {
                                if (promote.contains("N")) setPromoteRole("Knight");
                                if (promote.contains("B")) setPromoteRole("Bishop");
                                if (promote.contains("R")) setPromoteRole("Rook");

                            }

                            setPromoted(true);
                        }
                    } else {   //
                        returnValue =  false;
                    }

                }
                //return returnValue;



        } else {
            //black pawn can only move from high row to low row


            if (this.getFirstmove()) {
                if (absY == 0 && (x == -1 || x == -2)) {
                    if (x == -1) {
                        if (!dest.getIfEmpty()) returnValue = false;
                        else {
                            this.setFirstmove(false);
                            returnValue = true;
                        }


                    } else { //x = -2
                        if (!board.getCell(start.getRow() - 1, start.getCol()).getIfEmpty()) returnValue = false;
                        else if (!dest.getIfEmpty()) returnValue = false;
                        else {
                            board.getCell(start.getRow() - 1, start.getCol()).setEnpassingRound();
                            this.setFirstmove(false);
                            returnValue = true;
                        }

                    }

                } else if (absY == 1 && x == -1) {
                    if (dest.getIfEmpty()) {
                        returnValue = false;
                    } else {
                        returnValue = true;
                    }
                } else {
                    returnValue = false;
                }
            } else {  //not black pawn's first move
                if ((absY == 1 && x == -1) || (absY == 0 && x == -1)) {  //valid move
                    if (absY == 1) {
                        if (dest.getIfEmpty()) {  //斜着走必须吃
                            if (dest.getEnpassingRound() == 2) {
                                returnValue = true;
                            } else {
                                returnValue = false;
                            }

                        } else {
                            returnValue = true;
                        }
                    } else {   //absY==0&&x==-1
                        if (dest.getIfEmpty()) {
                            returnValue = true;
                        } else {
                            returnValue = false;
                        }

                    }

                    if (dest.getRow() == 0) {
                        //pawn reaches the top row
                        if (promote == null) {
                            setPromoteRole("Queen");
                        } else {
                            if (promote.contains("N")) setPromoteRole("Knight");
                            if (promote.contains("B")) setPromoteRole("Bishop");
                            if (promote.contains("R")) setPromoteRole("Rook");

                        }
                        setPromoted(true);
                    }

                } else {   //invalid move
                    returnValue = false;
                }

            }

        }

        }
        return returnValue;

    }

}
