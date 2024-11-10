package logic.chessPieces;

import logic.Board;
import logic.Move;

import java.util.ArrayList;

public abstract class Piece {
    protected int xPosition;
    protected int yPosition;
    protected final PieceColor pieceColor;
    protected Boolean isMoved;

    public Piece(int xPosition, int yPosition, PieceColor pieceColor) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.pieceColor = pieceColor;
        isMoved=false;
    }

    protected abstract ArrayList<Move> getAllMoves(Board board);
    protected abstract ArrayList<Move> getAllAttacksOfPiece(Board board);

    public ArrayList<Move> getAllMovesInBounds(Board board) {
        return filterOutOfBound(getAllMoves(board),board);
    }
    public ArrayList<Move> getAllAttacksOfPieceInBounds(Board board) {
        return filterOutOfBound(getAllAttacksOfPiece(board),board);
    }
    public void moveToMoveEnd(Move move) {
        xPosition= move.getXEnd();
        yPosition= move.getYEnd();
    }
    public void moveToMoveStart(Move move){
        xPosition= move.getXStart();
        yPosition= move.getYStart();
    }
    protected ArrayList<Move> filterOutOfBound(ArrayList<Move> moves,Board board){
        ArrayList<Move> allLegitMoves = new ArrayList<>();
        for(Move move: moves){
            if(move.getXEnd()>=0 && move.getXEnd()< board.getWidth() && move.getYEnd()>=0 && move.getYEnd()<board.getHeight())
                allLegitMoves.add(move);
        }
        return allLegitMoves;
    }
    public PieceColor getPieceColor() {
        return pieceColor;
    }

    public Boolean getMoved() {
        return isMoved;
    }

    public void setMovedAsTrue() {
        this.isMoved=true;
    }
    public int getXPosition() {
        return xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

}
