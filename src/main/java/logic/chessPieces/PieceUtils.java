package logic.chessPieces;

import logic.Board;
import logic.Move;

import java.util.ArrayList;

class PieceUtils {
    protected static ArrayList<Move> getAllMovesInLine(int xPosition, int yPosition, int xDirection, int yDirection, Board board) {
        if(xDirection>1) throw new IllegalArgumentException("direction should be 1 or -1 or 0");
        if(xDirection<-1) throw new IllegalArgumentException("direction should be 1 or -1 or 0");
        if(yDirection>1) throw new IllegalArgumentException("direction should be 1 or -1 or 0");
        if(yDirection<-1) throw new IllegalArgumentException("direction should be 1 or -1 or 0");
        int xStartPosition = xPosition;
        int yStartPosition = yPosition;
        ArrayList<Move> moves = new ArrayList<>();
        while(xPosition+xDirection>=0 && xPosition+xDirection< board.getWidth() && yPosition+yDirection>=0 && yPosition+yDirection<board.getHeight()){
            xPosition+=xDirection;
            yPosition+=yDirection;
            moves.add(new Move(xStartPosition,yStartPosition ,xPosition, yPosition));
        }
        return moves;
    }
    protected static ArrayList<Move> filterMovesBeforeAPiece(ArrayList<Move> moves, Board board) {
        ArrayList<Move> filteredMoves = new ArrayList<>();
        for(Move move : moves) {
            PieceColor currColor = board.getPieceColorOnMoveEnd(move);
            if(currColor==null){
                filteredMoves.add(move);
            }else{
                filteredMoves.add(move);
                break;
            }
        }
        return filteredMoves;
    }
    protected static ArrayList<Move> filterMovesBasedOnColor(ArrayList<Move> moves, Board board,PieceColor color) {
        ArrayList<Move> filteredMoves = new ArrayList<>();
        for(Move move : moves) {
            if(board.getPieceColorOnMoveEnd(move)==null){
                filteredMoves.add(move);
                continue;
            }
            if(board.getPieceColorOnMoveEnd(move)==color) continue;
            filteredMoves.add(move);
        }
        return filteredMoves;
    }
    protected static ArrayList<Move> filterMovesUnderAttackOfColor(ArrayList<Move> moves, Board board,PieceColor color) {
        Boolean[][] attacks = color.equals(PieceColor.White) ? board.getBlackAttack() : board.getWhiteAttack();

        ArrayList<Move> filteredMoves = new ArrayList<>();
        for(Move move : moves) {
            if(!attacks[move.getYEnd()][move.getXEnd()])
                filteredMoves.add(move);
        }
        return filteredMoves;
    }
}
