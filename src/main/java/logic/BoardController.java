package logic;

import logic.chessPieces.Piece;
import logic.chessPieces.PieceColor;
import logic.pieceDisposition.BoardDispositionSetting;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class BoardController {

    private final Board board;
    public BoardController(BoardDispositionSetting boardDispositionSetting) {
        this.board = new Board(boardDispositionSetting);
    }
    public void makeAMove(Move move, PieceColor color) throws MoveIsNotValidException {
        if (!isMoveValid(move,color)) throw new MoveIsNotValidException();
        Piece tempPiece = board.getPieceOnMoveStart(move);
        if(tempPiece==null) throw new RuntimeException("piece on move start doesn't exist");
        if(move instanceof CastingMove){
            board.makeACastlingMove((CastingMove) move);
        }else{
            board.makeAMove(move);
        }
    }

    public boolean isMoveValid(Move move, PieceColor color) {
        Piece piece = board.getPieceOnMoveStart(move);
        if (piece == null) return false;
        ArrayList<Move> moves = piece.getAllMovesInBounds(board);
        for (Move currMove : moves) {
            if (currMove.equals(move)) return !board.isKingOfColorUnderAttackAfterMove(color,move);
        }
        return false;
    }

    public boolean isGameInDraw(){
        if(!board.isThereAnyPossibleBlackMoves() || !board.isThereAnyPossibleWhiteMoves()){
            if(!board.isKingOfColorUnderAttack(PieceColor.Black) && !board.isKingOfColorUnderAttack(PieceColor.White)){
                return true;
            }
        }
        return false;
    }
    @Nullable
    public PieceColor whoWonTheGame(){
        if(!board.isThereAnyPossibleBlackMoves() && board.isKingOfColorUnderAttack(PieceColor.Black)){
            return PieceColor.White;
        }
        if(!board.isThereAnyPossibleWhiteMoves() && board.isKingOfColorUnderAttack(PieceColor.White)){
            return PieceColor.Black;
        }
        return null;
    }
    public String getBoardAsString(){
        return board.toString();
    }
}
