package chess;
import board.*;
import pieces.Bishop;
import pieces.Knight;
import pieces.Queen;
import pieces.Rook;

import java.util.Scanner;

/**
 * Chess class represent the actual game include initialize an empty board and manage the game play
 * @author Yifei Li,yl1160
 * @author Yifan Zhang,yz745
 */
public class Chess {
    /**
     * ifValidMove method return a boolean value that indicate whether a command is valid
     * @param table table passes through the current game board
     * @param target cell that need to be moved
     * @param dest  destination cell
     * @param isWhite boolean value that indicate the color of player
     * @param promote String that indicate promote rule
     * @return boolean value that indicate whether a command is valid
     */

    public static boolean ifValidMove(Board table, String target, String dest,boolean isWhite, String promote){
        //relate input string to actual cell
        char TtempC = target.charAt(0);
        int TtempR = Integer.parseInt(String.valueOf(target.charAt(1)))-1;
        char DtempC = dest.charAt(0);
        int DtempR = Integer.parseInt(String.valueOf(dest.charAt(1)))-1;

        Cell targetCell = table.getCell(TtempR,TtempC-'a'+1-1);
        Cell destCell = table.getCell(DtempR,DtempC-'a'+1-1);
        //check if the target cell color match
        if(isWhite == targetCell.getPiece().isWhite()){

            return targetCell.getPiece().rule(table,targetCell,destCell,promote);
        }
        else{
            //System.out.println("move a wrong color");
            return false;      //move a wrong color piece
        }

    }

    /**
     * move target piece to destination, including promote pawn and castling
     * @param table Current chess board
     * @param target a String that indicate the location of target piece
     * @param dest  a String that indicate the location of destination piece
     */
    public static void move(Board table, String target, String dest){
        char TtempC = target.charAt(0);
        int TtempR = Integer.parseInt(String.valueOf(target.charAt(1)))-1;
        char DtempC = dest.charAt(0);
        int DtempR = Integer.parseInt(String.valueOf(dest.charAt(1)))-1;


        Cell targetCell = table.getCell(TtempR,TtempC-'a'+1-1).clone();
        Cell destCell = table.getCell(DtempR,DtempC-'a'+1-1).clone();
        //System.out.println("First move:  "+table.getCell(TtempR,TtempC-'a'+1-1).getPiece().getFirstmove());
        table.setCell(targetCell,DtempR,DtempC-'a'+1-1);
        table.getCell(DtempR,DtempC-'a'+1-1).setCol(DtempC-'a'+1-1);
        table.getCell(DtempR,DtempC-'a'+1-1).setRow(DtempR);
        table.getCell(DtempR,DtempC-'a'+1-1).setBlack(destCell.getBlack());
        table.getCell(DtempR,DtempC-'a'+1-1).setEnpassingRound(destCell.getEnpassingRound());


        //System.out.println("row:"+table.getCell(DtempR,DtempC-'a'+1-1).getRow());
        //System.out.println("col:"+table.getCell(DtempR,DtempC-'a'+1-1).getCol());
        //System.out.println("First move:  "+table.getCell(DtempR,DtempC-'a'+1-1).getPiece().getFirstmove());
        table.getCell(TtempR,TtempC-'a'+1-1).removePiece();
        if(table.getCell(DtempR,DtempC-'a'+1-1).getPiece().getType().equals("Pawn")){
            //check if the pawn get promoted

            if(table.getCell(DtempR,DtempC-'a'+1-1).getPiece().getPromoteRole()!=null){
                String promotion = table.getCell(DtempR,DtempC-'a'+1-1).getPiece().getPromoteRole();
                boolean isWhite = table.getCell(DtempR,DtempC-'a'+1-1).getPiece().isWhite();
                //System.out.println("I'm here");
                if(promotion.equals("Queen")){

                    table.getCell(DtempR,DtempC-'a'+1-1).setPiece(new Queen("Queen",isWhite));
                    if(isWhite) {
                        table.getCell(DtempR, DtempC - 'a' + 1 - 1).setName("wQ");
                    }
                    else{
                        table.getCell(DtempR, DtempC - 'a' + 1 - 1).setName("bQ");
                    }
                }
                if(promotion.equals("Knight")){
                    table.getCell(DtempR,DtempC-'a'+1-1).setPiece(new Knight("Knight",isWhite));
                    if(isWhite) {
                        table.getCell(DtempR, DtempC - 'a' + 1 - 1).setName("wN");
                    }
                    else{
                        table.getCell(DtempR, DtempC - 'a' + 1 - 1).setName("bN");
                    }
                }
                if(promotion.equals("Bishop")){
                    table.getCell(DtempR,DtempC-'a'+1-1).setPiece(new Bishop("Bishop",isWhite));
                    if(isWhite) {
                        table.getCell(DtempR, DtempC - 'a' + 1 - 1).setName("wB");
                    }
                    else{
                        table.getCell(DtempR, DtempC - 'a' + 1 - 1).setName("bB");
                    }
                }
                if(promotion.equals("Rook")){
                    table.getCell(DtempR,DtempC-'a'+1-1).setPiece(new Rook("Rook",isWhite,false));
                    if(isWhite) {
                        table.getCell(DtempR, DtempC - 'a' + 1 - 1).setName("wR");
                    }
                    else{
                        table.getCell(DtempR, DtempC - 'a' + 1 - 1).setName("bR");
                    }
                }
            }
            //check if empassing happened
            if(table.getCell(DtempR,DtempC-'a'+1-1).getEnpassingRound()==2){
                //empassing
                boolean isWhite = table.getCell(DtempR,DtempC-'a'+1-1).getPiece().isWhite();
                if(isWhite){    //last move was black move
                    table.getCell(DtempR-1,DtempC-'a'+1-1).removePiece();
                    table.getCell(DtempR-1,DtempC-'a'+1-1).resetEnpassingRound();
                }
                else{       //last move was white move
                    table.getCell(DtempR+1,DtempC-'a'+1-1).removePiece();
                    table.getCell(DtempR+1,DtempC-'a'+1-1).resetEnpassingRound();
                }

            }

        }
        if(table.getCell(DtempR,DtempC-'a'+1-1).getPiece().getType().equals("King")){
            //check if castling

            int stepMove = Math.abs(DtempC-'a'+1-1-(TtempC-'a'+1-1));
            int y = DtempC-'a'+1-1-(TtempC-'a'+1-1);
            if(stepMove==2){
                //castling, check direction
                //System.out.println("castling");
                if(y == 2){       //right cast

                    Cell castRook = table.getCell(DtempR,DtempC-'a'+1-1+1).clone(); //rook copy
                    Cell destTemp = table.getCell(DtempR,DtempC-'a'+1-1-1).clone(); //dest copy
                    table.setCell(castRook,DtempR,DtempC-'a'+1-1-1);
                    table.getCell(DtempR,DtempC-'a'+1-1-1).setRow(DtempR);
                    table.getCell(DtempR,DtempC-'a'+1-1-1).setCol(DtempC-'a'+1-1-1);
                    table.getCell(DtempR,DtempC-'a'+1-1-1).setBlack(destTemp.getBlack());
                    table.getCell(DtempR,DtempC-'a'+1-1+1).removePiece();
                }
                else{   //y = -2, left cast
                    Cell castRook = table.getCell(DtempR,DtempC-'a'+1-1-2).clone();
                    Cell destTemp = table.getCell(DtempR,DtempC-'a'+1-1+1).clone();
                    table.setCell(castRook,DtempR,DtempC-'a'+1-1+1);
                    table.getCell(DtempR,DtempC-'a'+1-1+1).setRow(DtempR);
                    table.getCell(DtempR,DtempC-'a'+1-1+1).setCol(DtempC-'a'+1-1+1);
                    table.getCell(DtempR,DtempC-'a'+1-1+1).setBlack(destTemp.getBlack());
                    table.getCell(DtempR,DtempC-'a'+1-1-2).removePiece();
                }
            }
        }

    }

    /**
     * Update en passant status of current board. Each cell have a en passant value, if it's 2, means the pawn
     * can be captured by en passant. If it's 3, means pawn can no longer be captured by en passant.
     * @param table current board
     */
    public static void empasingUpdate(Board table){
        for(int i = 0;i < 8;i++){
            for(int j = 0;j < 8;j++){
                if(table.getCell(i,j).getEnpassingRound()>=3) table.getCell(i,j).resetEnpassingRound();
                if(table.getCell(i,j).getEnpassingRound()==2||table.getCell(i,j).getEnpassingRound()==1) table.getCell(i,j).setEnpassingRound();

                //System.out.print(table.getCell(i,j).getEnpassingRound());
            }
            //System.out.println();
        }
    }

    /**
     * Check if current player's move put the other player into check.
     * @param table current board
     * @param whiteMove indicate the most recent player that moves, true for white, false for black
     * @return boolean value that indicate if this move put the other player into check
     * @throws CloneNotSupportedException clone board not supported
     */
    public static boolean ifCheck(Board table,boolean whiteMove) throws CloneNotSupportedException {   //if this move put the other side in check
        //for every piece on this side, if its next move can capture opponent's king, return true
        boolean isCheck = false;

        for(int i = 0;i < 8;i++){
            for(int j = 0;j < 8;j++){
                if(!table.getCell(i,j).getIfEmpty()){
                    if(table.getCell(i,j).getPiece().isWhite()==whiteMove){ //same color
                            isCheck = tryEveryMove(table, i, j, whiteMove);
                            if(isCheck) {

                                break;
                            }
                    }
                }
            }
            if(isCheck) break;
        }

        return isCheck;
    }

    /**
     * Check if certain piece's next move can capture enemy's King.
     * @param table current board
     * @param row row number of that piece
     * @param col col number of that piece
     * @param whiteMove color of that piece
     * @return true if that piece can capture enemy's King, false otherwise.
     * @throws CloneNotSupportedException clone board not supported
     */
    public static boolean tryEveryMove(Board table,int row,int col,boolean whiteMove) throws CloneNotSupportedException {

        Board tempTable = table.clone();

        for(int i = 0; i <8 ; i++){
            for(int j = 0; j < 8; j++){
                if(ifValidMove(tempTable,stringConvert(row,col),stringConvert(i,j),whiteMove,null)){
                    //a valid move, check if the dest is opponent's king
                    //System.out.println("trying "+stringConvert(row,col) + " to "+ stringConvert(i,j));
                    if((!tempTable.getCell(i,j).getIfEmpty()) && tempTable.getCell(i,j).getPiece().getType().equals("King")){
                        //System.out.println("result is true" );
                        //table.getCell(i,j).getPiece().setUnderCheck(true);
                        return true;
                        //System.out.println("Why I can still go there");
                    }
                }
            }

        }
        return false;

    }

    /**
     * Check if current player's move put the other player into checkmate. This method is called when one player
     * put the other player into check. The other player need to find a way to escape from check. If there are no way
     * to escape, return true
     * @param table current board
     * @param whiteMove color of current player
     * @return true if current player checkmate the other player, false otherwise.
     * @throws CloneNotSupportedException Clone board can not be done
     */
    public static boolean ifCheckmate(Board table,boolean whiteMove) throws CloneNotSupportedException {
        boolean isCheckmate = true;
        for(int i = 0;i < 8;i++){
            for(int j = 0;j < 8;j++){
                if(!table.getCell(i,j).getIfEmpty()&&table.getCell(i,j).getPiece().isWhite()!=whiteMove){   //opposite color
                    isCheckmate = moveAndCheck(table,i,j,!whiteMove);
                    if(!isCheckmate) return false;
                }
            }
        }
        return isCheckmate;
    }

    /**
     * Let certain piece move to a valid location and see if the move can save their own King from being check.
     * @param table current board
     * @param row row number of the piece
     * @param col column number of the piece
     * @param whiteMove color of of the piece
     * @return true if can escape, false otherwise.
     * @throws CloneNotSupportedException error
     */
    public static boolean moveAndCheck(Board table,int row,int col,boolean whiteMove) throws CloneNotSupportedException {
        boolean returnValue = true;
        Board tempBoard1 = table.clone();
        for(int i = 0;i < 8;i++){
            for(int j = 0;j < 8;j++){
                if(ifValidMove(tempBoard1,stringConvert(row,col),stringConvert(i,j),whiteMove,null)){
                    Board tempBoard = table.clone();
                    move(tempBoard,stringConvert(row,col),stringConvert(i,j));
                    if(!ifCheck(tempBoard,!whiteMove)){
                        returnValue = false;
                    }
                }
            }
        }
        return returnValue;
    }

    /**
     * Find the King of given color, update King's underCheck to given boolean
     * @param table current table
     * @param whiteMove true for white, false for black
     * @param update boolean need to be update
     */
    public static void updateCheck(Board table, boolean whiteMove,boolean update){
        for(int i = 0;i < 8;i++){
            for(int j = 0;j < 8;j++){
                if(!table.getCell(i,j).getIfEmpty()&&table.getCell(i,j).getPiece().isWhite()==whiteMove
                        &&table.getCell(i,j).getPiece().getType().equals("King")){
                    table.getCell(i,j).getPiece().setUnderCheck(update);
                    //System.out.println("white: "+whiteMove+" undercheck: "+table.getCell(i,j).getPiece().getUnderCheck()
                     //   +'\n'+"hasmoved: "+table.getCell(i,j).getPiece().getHasMoved()+
                     //       "isCastlingDone: "+ table.getCell(i,j).getPiece().getCastlingDone());

                }
            }
        }
    }

    /**
     * String covert method that convert row number and col number to String. For example, row 1 col 0 is "a2"
     * @param row given row number
     * @param col given column number
     * @return String that can be parse by other method
     */
    public static String stringConvert(int row,int col){
        String result;
        char colC = 'a';
        if(col == 0) colC = 'a';
        if(col == 1) colC = 'b';
        if(col == 2) colC = 'c';
        if(col == 3) colC = 'd';
        if(col == 4) colC = 'e';
        if(col == 5) colC = 'f';
        if(col == 6) colC = 'g';
        if(col == 7) colC = 'h';
        row = row + 1;
        result = String.valueOf(colC);
        String temp = Integer.toString(row);
        result = result + temp;
        return result;
    }

    /**
     * Main process that manage the game. Including initializing chess board, parse user input, check if move is
     * valid, check if move can put themself into check, update en passant value, check if check, check if checkmate,
     * parse draw and resign.
     * @param args default input
     * @throws CloneNotSupportedException Clone has error
     */
    public static void main(String[] args) throws CloneNotSupportedException {
        Board board = new Board();
        board.show();

        String userInput = "";
        Scanner in;
        String target = "";
        String dest = "";
        String promoteRole = null;
        String drawHolder =null;
        boolean ifValid;
        boolean ifCheck;
        boolean ifCheckmate = false;
        boolean ifDraw = false;
        boolean whiteMove = true;
        boolean selfCheck;
        while(!ifCheckmate) {
            ifCheck = false;
            selfCheck = false;
            target = "";
            dest = "";
            promoteRole = "";
            if(whiteMove)
            System.out.print("White's move: ");
            else
                System.out.print("Black's move: ");
            in = new Scanner(System.in);
            userInput = in.nextLine();
            int m1 = userInput.indexOf(' ');
            int m2 = userInput.indexOf(' ', m1 + 1);
            int m3 = userInput.indexOf(' ',m2 + 1); //only happen when promote and draw
            if(m1<0) {
                //player resign
                promoteRole = userInput;
            }
            else{
                //at least 2 input
                target = userInput.substring(0, m1);         //only one input, either draw, or resign
                if (m2 < 0) {
                    //only 2 input
                    //no promotion or default promotion
                    dest = userInput.substring(m1 + 1);
                    promoteRole = null;
                }
                else {
                    //at least 3 input
                    //promote pawn to a specific piece or ask for draw
                    if(m3<0){   //only 3 input
                        dest = userInput.substring(m1 + 1, m2);
                        promoteRole = userInput.substring(m2 + 1);
                    }
                    else{   //4 input, promote and draw happen same time
                        dest = userInput.substring(m1 + 1, m2);
                        promoteRole = userInput.substring(m2 + 1, m3);
                        drawHolder = userInput.substring(m3 + 1);
                    }

                }
            }
            if (promoteRole != null){
                if (promoteRole.contains("resign")) {
                    if (whiteMove) {          //white resign, black win
                        System.out.println("Black wins");
                    } else {                   //black resign, white win
                        System.out.println("White wins");
                    }
                    break;
                }
                if(drawHolder == null){
                    if (promoteRole.contains("draw")) {
                        if (!ifDraw) ifDraw = true;
                            //promoteRole = null;

                        else {       //the previous player said draw, game end
                            System.out.println("Result:draw");
                            break;
                        }
                    }
                    else{   //only three input, and the third input is not draw, ifDraw reset
                        ifDraw = false;
                    }
                }
                else{   //drawHolder != null, drawHolder can only contain "draw"
                    if(!ifDraw) ifDraw = true;
                    else{
                        System.out.println("Result:draw");
                        break;
                    }
                }
            }
            else{   //promoteRole is null, only 2 input, ifDraw reset
                ifDraw = false;
            }

            //if promoteRole work as a third input that contains "draw", promoteRole will be reset
            if(promoteRole != null&&promoteRole.contains("draw")) promoteRole = null;


            //System.out.println("whiteMove: "+whiteMove);


            ifValid = ifValidMove(board,target,dest,whiteMove,promoteRole);

            if(!ifValid){                            //invalid command
                System.out.println("Illegal move, try again");
            }

            else{
                /*create a copy of current board, if player's move
                put himself in check, print illegal move and reverse the move
                */

                //System.out.println("valid move");
                //board.showEnpass();
                Board tempBoard = board.clone();
                move(board,target,dest);
                //tempBoard.show();
                //board.showEnpass();
                empasingUpdate(board);

                /*after each move, if the move will turn own King into check,
                move should be reversed and printout Illegal move.*/
                selfCheck = ifCheck(board,!whiteMove);

                if(selfCheck){
                    board = tempBoard;
                    System.out.println("Illegal move, try again");
                }


                else{
                    updateCheck(board, whiteMove,false);
                    //board.show();
//                    if(whiteMove){
//                        System.out.println("White's move: "+ target +"\t"+ dest);
//                    }
//                    else{
//                        System.out.println("Black's move: "+ target +"\t"+ dest);
//                    }
                    ifCheck = ifCheck(board,whiteMove);
                    //System.out.println("check?: "+ifCheck);
                    if(ifCheck){   //check if Check
                        updateCheck(board, !whiteMove,true);
                        ifCheckmate = ifCheckmate(board,whiteMove);
                        if(ifCheckmate){   //check if Checkmate
                            System.out.print("Checkmate ");
                            if(whiteMove) System.out.println("White wins");
                            else{
                                System.out.println("Black wins");
                            }
                        }
                        else{
                            System.out.println("Check");
                        }
                    }
                    updateCheck(board, !whiteMove,false);
                    board.show();
                    //board.showEnpass();
                    whiteMove = !whiteMove;

                }

            }


        }







    }
}

