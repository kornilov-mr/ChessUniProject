package logic.chessPieces;

import logic.Board;
import logic.Move;

import java.util.ArrayList;

public class Bishop extends Piece {
    public Bishop(int xPosition, int yPosition, PieceColor pieceColor) {
        super(xPosition, yPosition, pieceColor);
    }

    @Override
    protected ArrayList<Move> getAllMoves(Board board) {
        ArrayList<Move> moves = getAllAttacksOfPieceInBounds(board);
        return PieceUtils.filterMovesBasedOnColor(moves,board,pieceColor);
    }

    @Override
    protected ArrayList<Move> getAllAttacksOfPiece(Board board) {
        ArrayList<Move> moves = new ArrayList<>();
        ArrayList<Move> tempMoves;
        tempMoves = PieceUtils.getAllMovesInLine(xPosition,yPosition,1,1, board);
        moves.addAll(PieceUtils.filterMovesBeforeAPiece(tempMoves,board));

        tempMoves = PieceUtils.getAllMovesInLine(xPosition,yPosition,-1,1, board);
        moves.addAll(PieceUtils.filterMovesBeforeAPiece(tempMoves,board));

        tempMoves = PieceUtils.getAllMovesInLine(xPosition,yPosition,1,-1, board);
        moves.addAll(PieceUtils.filterMovesBeforeAPiece(tempMoves,board));

        tempMoves = PieceUtils.getAllMovesInLine(xPosition,yPosition,-1,-1, board);
        moves.addAll(PieceUtils.filterMovesBeforeAPiece(tempMoves,board));
        return moves;
    }

    @Override
    public String toString() {
        return "bi"+ (pieceColor.equals(PieceColor.Black)? "B" : "W") ;
    }
}
