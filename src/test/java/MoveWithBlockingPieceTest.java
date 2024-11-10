import logic.Board;
import logic.Move;
import logic.chessPieces.*;
import logic.pieceDisposition.BoardDispositionSetting;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class MoveWithBlockingPieceTest {
    private static final Board blockingPawnBoard = new Board(new BoardDispositionSetting("src/test/java/DispositionsForTesting/TableWithBlockingPawns.sp"));
    private static final Board boardWithKings = new Board(new BoardDispositionSetting("src/test/java/DispositionsForTesting/TableWithBlockingPawnsWithKings.sp"));


    @Test
    public void testPawnMovesInFrontOfDifferentPawnInOneTile(){
        String movesExpected = "[Move{xStart=3, yStart=4, xEnd=3, yEnd=3}]";
        Pawn pawn = new Pawn(3,4, PieceColor.Black);
        ArrayList<Move> moves =  pawn.getAllMovesInBounds(blockingPawnBoard);
        System.out.println(moves);
        Assert.assertEquals(movesExpected, moves.toString());
    }
    @Test
    public void testPawnMovesWithAttackPawnDiagonally(){
        String movesExpected = "[Move{xStart=3, yStart=3, xEnd=2, yEnd=2}]";
        Pawn pawn = new Pawn(3,3, PieceColor.Black);
        ArrayList<Move> moves =  pawn.getAllMovesInBounds(blockingPawnBoard);
        System.out.println(moves);
        Assert.assertEquals(movesExpected, moves.toString());
    }
    @Test
    public void testKnightMoves(){
        String movesExpected = "[Move{xStart=3, yStart=4, xEnd=5, yEnd=5}, Move{xStart=3, yStart=4, xEnd=4, yEnd=6}, Move{xStart=3, yStart=4, xEnd=1, yEnd=3}, Move{xStart=3, yStart=4, xEnd=2, yEnd=2}, Move{xStart=3, yStart=4, xEnd=1, yEnd=5}, Move{xStart=3, yStart=4, xEnd=2, yEnd=6}, Move{xStart=3, yStart=4, xEnd=5, yEnd=3}]";
        Knight knight = new Knight(3,4,PieceColor.Black);
        ArrayList<Move> moves =  knight.getAllMovesInBounds(blockingPawnBoard);
        Assert.assertEquals(movesExpected, moves.toString());
    }
    @Test
    public void testBishopMoves(){
        String movesExpected = "[Move{xStart=3, yStart=4, xEnd=4, yEnd=5}, Move{xStart=3, yStart=4, xEnd=5, yEnd=6}, Move{xStart=3, yStart=4, xEnd=6, yEnd=7}, Move{xStart=3, yStart=4, xEnd=2, yEnd=5}, Move{xStart=3, yStart=4, xEnd=1, yEnd=6}, Move{xStart=3, yStart=4, xEnd=0, yEnd=7}, Move{xStart=3, yStart=4, xEnd=4, yEnd=3}, Move{xStart=3, yStart=4, xEnd=2, yEnd=3}, Move{xStart=3, yStart=4, xEnd=1, yEnd=2}]";
        Bishop bishop = new Bishop(3,4,PieceColor.Black);
        ArrayList<Move> moves =  bishop.getAllMovesInBounds(blockingPawnBoard);
        Assert.assertEquals(movesExpected, moves.toString());
    }
    @Test
    public void testKingMoves(){
        String movesExpected = "[Move{xStart=3, yStart=1, xEnd=4, yEnd=0}, Move{xStart=3, yStart=1, xEnd=2, yEnd=2}, Move{xStart=3, yStart=1, xEnd=2, yEnd=0}, Move{xStart=3, yStart=1, xEnd=3, yEnd=0}, Move{xStart=3, yStart=1, xEnd=2, yEnd=1}, Move{xStart=3, yStart=1, xEnd=4, yEnd=1}]";
        King king = new King(3,1,PieceColor.Black);
        ArrayList<Move> moves =  king.getAllMovesInBounds(boardWithKings);
        Assert.assertEquals(movesExpected, moves.toString());
    }
    @Test
    public void testRookMoves(){
        String movesExpected = "[Move{xStart=3, yStart=4, xEnd=3, yEnd=5}, Move{xStart=3, yStart=4, xEnd=3, yEnd=6}, Move{xStart=3, yStart=4, xEnd=3, yEnd=7}, Move{xStart=3, yStart=4, xEnd=2, yEnd=4}, Move{xStart=3, yStart=4, xEnd=3, yEnd=3}, Move{xStart=3, yStart=4, xEnd=3, yEnd=2}, Move{xStart=3, yStart=4, xEnd=4, yEnd=4}, Move{xStart=3, yStart=4, xEnd=5, yEnd=4}, Move{xStart=3, yStart=4, xEnd=6, yEnd=4}, Move{xStart=3, yStart=4, xEnd=7, yEnd=4}]";
        Rook rook = new Rook(3,4,PieceColor.Black);
        ArrayList<Move> moves =  rook.getAllMovesInBounds(blockingPawnBoard);
        Assert.assertEquals(movesExpected, moves.toString());
    }
    @Test
    public void testQueenMoves(){
        String movesExpected = "[Move{xStart=3, yStart=4, xEnd=3, yEnd=5}, Move{xStart=3, yStart=4, xEnd=3, yEnd=6}, Move{xStart=3, yStart=4, xEnd=3, yEnd=7}, Move{xStart=3, yStart=4, xEnd=2, yEnd=4}, Move{xStart=3, yStart=4, xEnd=3, yEnd=3}, Move{xStart=3, yStart=4, xEnd=3, yEnd=2}, Move{xStart=3, yStart=4, xEnd=4, yEnd=4}, Move{xStart=3, yStart=4, xEnd=5, yEnd=4}, Move{xStart=3, yStart=4, xEnd=6, yEnd=4}, Move{xStart=3, yStart=4, xEnd=7, yEnd=4}, Move{xStart=3, yStart=4, xEnd=4, yEnd=5}, Move{xStart=3, yStart=4, xEnd=5, yEnd=6}, Move{xStart=3, yStart=4, xEnd=6, yEnd=7}, Move{xStart=3, yStart=4, xEnd=2, yEnd=5}, Move{xStart=3, yStart=4, xEnd=1, yEnd=6}, Move{xStart=3, yStart=4, xEnd=0, yEnd=7}, Move{xStart=3, yStart=4, xEnd=4, yEnd=3}, Move{xStart=3, yStart=4, xEnd=2, yEnd=3}, Move{xStart=3, yStart=4, xEnd=1, yEnd=2}]";
        Queen queen = new Queen(3,4,PieceColor.Black);
        ArrayList<Move> moves =  queen.getAllMovesInBounds(blockingPawnBoard);
        Assert.assertEquals(movesExpected, moves.toString());
    }
}