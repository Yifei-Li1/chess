package pieces;

import board.Board;
import board.Cell;
import pieces.Pieces;
/**
 * Queen object, one kind of piece
 * @author Yifei Li,yl1160
 * @author Yifan Zhang,yz745
 */
public class Queen extends Pieces {
    //private String type;

    /**
     * constructor for Queen object
     * @param type "Queen"
     * @param isWhite white is true, black is false
     */
    public Queen(String type,boolean isWhite){
        super(type,isWhite,null,false,false,false,false);
        //this.type = type;
    }

    /**
     * deep copy of Queen object
     * @return deep copy of Queen object
     */
    public Queen clone(){
        return new Queen(this.getType(),this.isWhite());

    }
    @Override
    public boolean rule(Board board, Cell start, Cell dest, String promote) {
        if (dest.getPiece()!=null&&dest.getPiece().isWhite() == this.isWhite()) {
            return false;
        }
        int absX = Math.abs(start.getRow() - dest.getRow());
        int absY = Math.abs(start.getCol() - dest.getCol());
        int x = dest.getRow() - start.getRow();
        int y = dest.getCol() - start.getCol();
        if(((absX==0&&absY!=0)||(absX!=0&&absY==0))||(absX == absY)){
            //pieces.Queen can move like a rook or bishop
            boolean ifAnyThingInBetween = false;
            if(absX == absY){   //moving like a bishop
                for(int i = 1;i < absX;i++){
                    if(x>0&&y>0){   //dest is at bottom right
                        ifAnyThingInBetween = !board.getCell(start.getRow()+i,start.getCol()+i).getIfEmpty();
                    }
                    if(x>0&&y<0){   //dest is at bottom left
                        ifAnyThingInBetween = !board.getCell(start.getRow()+i,start.getCol()-i).getIfEmpty();
                    }
                    if(x<0&&y>0){   //dest is at top right
                        ifAnyThingInBetween = !board.getCell(start.getRow()-i,start.getCol()+i).getIfEmpty();
                    }
                    if(x<0&&y<0){   //dest is at top left
                        ifAnyThingInBetween = !board.getCell(start.getRow()-i,start.getCol()-i).getIfEmpty();
                    }
                    //System.out.println(board.getCell(start.getRow()+i,start.getCol()+i).getIfEmpty());
                    //System.out.println(board.getCell(2,4).getName());
                    if(ifAnyThingInBetween) break;  //once something in the way, break
                }
            }
            else{   //moving like a rook
                for(int i = 1;i < Math.abs(absX - absY);i++){
                    if(y==0&&x>0){  //dest is below target
                        ifAnyThingInBetween = !board.getCell(start.getRow()+i,start.getCol()).getIfEmpty();
                    }
                    if(y==0&&x<0){  //dest is above target
                        ifAnyThingInBetween = !board.getCell(start.getRow()-i,start.getCol()).getIfEmpty();
                    }
                    if(x==0&&y>0){  //dest is on the right of target
                        ifAnyThingInBetween = !board.getCell(start.getRow(),start.getCol()+i).getIfEmpty();
                    }
                    if(x==0&&y<0){
                        ifAnyThingInBetween = !board.getCell(start.getRow(),start.getCol()-i).getIfEmpty();
                    }
                    if(ifAnyThingInBetween) break;
                }
            }
            if(!ifAnyThingInBetween) {  //nothing between dest and start, valid move
                return true;
            }
            else{   //something between dest and start
                return false;
            }
        }
        else{   //queen is not moving like a queen
            return false;
        }

    }
}
