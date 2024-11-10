package logic.chessPieces;

import logic.Board;
import logic.Move;

import java.util.ArrayList;

public class Knight extends Piece {

    public Knight(int xPosition, int yPosition, PieceColor color) {
        super(xPosition, yPosition,color);
    }

    @Override
    protected ArrayList<Move> getAllMoves(Board board) {
        ArrayList<Move> moves = getAllAttacksOfPieceInBounds(board);
        return PieceUtils.filterMovesBasedOnColor(moves,board,pieceColor);
    }


    @Override
    protected ArrayList<Move> getAllAttacksOfPiece(Board board) {
        ArrayList<Move> moves = new ArrayList<>();
        moves.add(new Move(xPosition,yPosition ,xPosition + 2, yPosition + 1));
        moves.add(new Move(xPosition,yPosition ,xPosition + 1, yPosition + 2));
        moves.add(new Move(xPosition,yPosition ,xPosition - 2, yPosition - 1));
        moves.add(new Move(xPosition,yPosition ,xPosition - 1, yPosition - 2));
        moves.add(new Move(xPosition,yPosition ,xPosition - 2, yPosition + 1));
        moves.add(new Move(xPosition,yPosition ,xPosition - 1, yPosition + 2));
        moves.add(new Move(xPosition,yPosition ,xPosition + 2, yPosition - 1));
        moves.add(new Move(xPosition,yPosition ,xPosition + 1, yPosition - 2));
        return moves;
    }
    @Override
    public String toString() {
        return "kn"+ (pieceColor.equals(PieceColor.Black)? "B" : "W") ;
    }
}
