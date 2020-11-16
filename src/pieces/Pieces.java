package pieces;

import board.Board;
import board.Cell;

/**
 * an abstract class for all piece types such as pieces.Bishop
 * @author Yifei Li, yl1160,
 * @author Yifan Zhang, yz745
 *
 */
public abstract class Pieces implements Cloneable{
    private String type;
    private boolean isWhite;
    //private boolean isKilled;
    private String promoteRole = null;
    private boolean hasMoved = false;
    private boolean isFirstmove = true;
    private boolean promoted = false;
    private boolean castlingDone = false;
    private boolean underCheck = false;

    /**
     * constructor of piece class that initialize a piece with the color status and capture status
     * @param type Piece name
     * @param isWhite isWhite isWhite indicate weather a piece is white or not
     * @param promoteRole Piece(Pawn) will be promoted to what role
     * @param hasMoved weather this piece(King, Rook) has moved
     * @param isFirstmove weather this piece(Pawn) has moved
     * @param promoted weather this piece(Pawn) has promoted
     * @param underCheck weather this piece(King) is under check
     */
    public Pieces(String type,boolean isWhite,String promoteRole,boolean hasMoved,boolean isFirstmove,boolean promoted,boolean underCheck ){
        this.type = type;
        this.isWhite = isWhite;
        this.promoteRole = promoteRole;
        this.hasMoved = hasMoved;
        this.isFirstmove = isFirstmove;
        this.promoted = promoted;
        this.underCheck = underCheck;

    }
    //public void setType(String type){this.type = type;}

    /**
     * setter for underCheck
     * @param underCheck boolean to need to be updated
     */
    public void setUnderCheck(boolean underCheck){
        this.underCheck = underCheck;
    }

    /**
     * getter for underCheck
     * @return if this Piece(King) is under check
     */
    public boolean getUnderCheck(){
        return underCheck;
    }
    /**
     * getter for type
     * @return type name
     */
    public String getType(){return type;}

    /**
     * getter for piece color
     * @return true for white, false for black
     */
    public boolean isWhite(){
        return isWhite;
    }

    /**
     * getter for promotion role
     * @return String that indicate which role the pawn is going to promote
     */
    public String getPromoteRole() {return promoteRole;}

    /**
     * setter for pawn promotion
     * @param promoteRole Set promotion role to specific piece
     */
    public void setPromoteRole(String promoteRole) {
        this.promoteRole = promoteRole;
    }

    /**
     * setter for castling
     * @param castlingDone true after castling done, false by default
     */
    public void setCastlingDone(boolean castlingDone) {
        this.castlingDone = castlingDone;
    }

    /**
     * getter for castling
     * @return true if the King already done castling, false otherwise
     */
    public boolean getCastlingDone() {
        return castlingDone;
    }

    /**
     * setter for hasMoved, true if King has moved, false otherwise
     * @param hasMoved true if King has moved, false otherwise
     */
    public void setHasMoved(boolean hasMoved){
        this.hasMoved = hasMoved;
    }

    /**
     * getter for hasMoved
     * @return true if King has moved, false otherwise
     */
    public boolean getHasMoved(){
        return this.hasMoved;
    }

    /**
     * setter for firstMove, true if Pawn has moved, false otherwise
     * @param firstmove true if Pawn has moved, false otherwise
     */
    public void setFirstmove(boolean firstmove) {
        isFirstmove = firstmove;
    }

    /**
     * getter for firstMove, true if Pawn has moved, false otherwise
     * @return true if Pawn has moved, false otherwise
     */
    public boolean getFirstmove() {
        return isFirstmove;
    }

    /**
     * setter for promoted, true if Pawn has been promoted, false otherwise
     * @param promoted true if Pawn has been promoted, false otherwise
     */
    public void setPromoted(boolean promoted) {
        this.promoted = promoted;
    }

    /**
     * getter for promoted, true if Pawn has been promoted, false otherwise
     * @return true if Pawn has been promoted, false otherwise
     */
    public boolean getPromoted() {
        return promoted;
    }
    /**
     * an abstract method that detect weather a movement is valid for certain piece.
     * @param board board pass the current game board
     * @param start start pass the starting cell for certain movement
     * @param dest  dest pass the end cell for certain movement
     * @param promote store promoted role for Pawn
     * @return true if the movement is valid, false otherwise.
     */
    public abstract boolean rule(Board board, Cell start, Cell dest, String promote);
    //public abstract boolean checkValid(Board board, Cell start, Cell dest, String promote);

    /**
     * Each piece has a clone method, which make a deep copy of given piece
     * @return an deep copy instance for given clone
     */
    public abstract Pieces clone();



}
