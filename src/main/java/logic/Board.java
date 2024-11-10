package logic;

import logic.chessPieces.*;
import logic.pieceDisposition.BoardDispositionSetting;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class Board {
    private final int height;
    private final int width;

    private final Piece whiteKing;
    private final Piece blackKing;


    private final Piece[][] pieces;
    private Boolean[][] whiteAttack;
    private Boolean[][] blackAttack;

    private boolean isThereAnyPossibleBlackMoves;
    private boolean isThereAnyPossibleWhiteMoves;

    public Board(BoardDispositionSetting boardDispositionSetting) {
        this.height = boardDispositionSetting.getHeight();
        this.width = boardDispositionSetting.getWidth();
        this.pieces = boardDispositionSetting.getPieces();
        this.whiteKing = boardDispositionSetting.getWhiteKing();
        this.blackKing = boardDispositionSetting.getBlackKing();
        calculateTilesUnderAttack();
        calculateIsThereMoves();
    }

    public void calculateIsThereMoves() {
        isThereAnyPossibleBlackMoves = false;
        isThereAnyPossibleWhiteMoves = false;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Piece piece = getPieceOnTile(i, j);
                if (piece == null) continue;
                if (piece.getPieceColor().equals(PieceColor.Black) && !isThereAnyPossibleBlackMoves) {

                    ArrayList<Move> moves = piece.getAllMovesInBounds(this);
                    for (Move move : moves) {
                        if (!isKingOfColorUnderAttackAfterMove(PieceColor.Black, move)) {
                            isThereAnyPossibleBlackMoves = true;
                            break;
                        }
                    }
                } else if (piece.getPieceColor().equals(PieceColor.White) && !isThereAnyPossibleWhiteMoves) {
                    ArrayList<Move> moves = piece.getAllMovesInBounds(this);
                    for (Move move : moves) {
                        if (!isKingOfColorUnderAttackAfterMove(PieceColor.White, move)) {
                            isThereAnyPossibleWhiteMoves = true;
                            break;
                        }
                    }
                }
            }
        }
    }

    public void calculateTilesUnderAttack() {
        Boolean[][] blackAttack = new Boolean[width][height];
        Boolean[][] whiteAttack = new Boolean[width][height];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                blackAttack[i][j] = false;
                whiteAttack[i][j] = false;
            }
        }
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                Piece piece = getPieceOnTile(i, j);
                if (piece == null) continue;
                if (piece.getPieceColor().equals(PieceColor.Black)) {
                    ArrayList<Move> Attacks = piece.getAllAttacksOfPieceInBounds(this);
                    for (Move move : Attacks) {
                        blackAttack[move.getYEnd()][move.getXEnd()] = true;
                    }
                } else {
                    ArrayList<Move> Attacks = piece.getAllAttacksOfPieceInBounds(this);
                    for (Move move : Attacks) {
                        whiteAttack[move.getYEnd()][move.getXEnd()] = true;
                    }
                }
            }
        }
        this.blackAttack = blackAttack;
        this.whiteAttack = whiteAttack;
    }

    public ArrayList<Move> castlingMovesAvailableForKingOfColor(PieceColor color) {
        return color.equals(PieceColor.Black) ? castlingMovesForBlack() : castlingMovesForWhite();
    }

    public ArrayList<Move> castlingMovesForWhite() {
        if (whiteKing.getMoved()) return new ArrayList<>();
        ArrayList<Move> moves = new ArrayList<>();

        Piece leftRook = pieces[0][0];
        if (leftRook instanceof Rook && !leftRook.getMoved()) {
            if (!blackAttack[1][0] && !blackAttack[2][0] && !blackAttack[3][0] &&
                    pieces[1][0] == null && pieces[2][0] == null && pieces[3][0] == null)
                moves.add(new CastingMove(4, 0, 2, 0,(Rook) leftRook,3,0));
        }
        Piece rightRook = pieces[7][0];
        if (leftRook instanceof Rook && !rightRook.getMoved()) {
            if (!blackAttack[6][0] && !blackAttack[5][0] &&
                    pieces[6][0] == null && pieces[5][0] == null)
                moves.add(new CastingMove(4, 0, 6, 0,(Rook) rightRook,5,0));
        }
        return moves;
    }

    public ArrayList<Move> castlingMovesForBlack() {
        if (blackKing.getMoved()) return new ArrayList<>();
        ArrayList<Move> moves = new ArrayList<>();

        Piece leftRook = pieces[0][7];
        if (leftRook != null && !leftRook.getMoved()) {
            if (!whiteAttack[1][7] && !whiteAttack[2][7] && !whiteAttack[3][7] &&
                    pieces[1][7] == null && pieces[2][7] == null && pieces[3][7] == null)
                moves.add(new CastingMove(4, 7, 2, 7,(Rook) leftRook,3,7));
        }
        Piece rightRook = pieces[7][7];
        if (rightRook != null && !rightRook.getMoved()) {
            if (!whiteAttack[6][7] && !whiteAttack[5][7] &&
                    pieces[6][7] == null && pieces[5][7] == null)
                moves.add(new CastingMove(4, 7, 6, 7,(Rook) rightRook,5,7));
        }
        return moves;
    }

    public Boolean isKingOfColorUnderAttack(PieceColor color) {
        if (color.equals(PieceColor.Black)) {
            Piece kind = blackKing;
            return whiteAttack[kind.getYPosition()][kind.getXPosition()];
        } else {
            Piece kind = whiteKing;
            return blackAttack[kind.getYPosition()][kind.getXPosition()];
        }
    }
    public void makeAMove(Move move){
        makeAMoveWithRecalculating(move);
        Piece tempPiece = pieces[move.getYEnd()][move.getXEnd()];
        calculateIsThereMoves();
        tempPiece.setMovedAsTrue();
    }
    public void makeACastlingMove(CastingMove castingMove){
        makeAMove(castingMove);
        makeAMove(new Move(castingMove.getRook().getXPosition(),castingMove.getRook().getYPosition(),
                castingMove.getxRookEnd(),castingMove.getyRookEnd()));


    }
    private void makeAMoveWithRecalculating(Move move) {
        Piece tempPiece = getPieceOnMoveStart(move);
        if (tempPiece == null) throw new RuntimeException("piece on move start doesn't exist");

        pieces[move.getYStart()][move.getXStart()] = null;
        pieces[move.getYEnd()][move.getXEnd()] = tempPiece;
        tempPiece.moveToMoveEnd(move);

        calculateTilesUnderAttack();
    }

    public Boolean isKingOfColorUnderAttackAfterMove(PieceColor color, Move move) {
        makeAMoveWithRecalculating(move);
        boolean result = isKingOfColorUnderAttack(color);
        makeAMoveWithRecalculating(move.getReversedMove());
        return result;
    }

    @Nullable
    public Piece getPieceOnMoveStart(Move move) {
        return pieces[move.getYStart()][move.getXStart()];
    }

    @Nullable
    public Piece getPieceOnMoveEnd(Move move) {
        return pieces[move.getYEnd()][move.getXEnd()];
    }

    @Nullable
    public PieceColor getPieceColorOnMoveStart(Move move) {
        Piece piece = getPieceOnTile(move.getXStart(), move.getYStart());
        if (piece == null) return null;
        return piece.getPieceColor();
    }

    @Nullable
    public PieceColor getPieceColorOnMoveEnd(Move move) {
        Piece piece = getPieceOnTile(move.getXEnd(), move.getYEnd());
        if (piece == null) return null;
        return piece.getPieceColor();
    }

    @Nullable
    public Piece getPieceOnTile(int x, int y) {
        return pieces[y][x];
    }

    @Nullable
    public PieceColor getPieceColorOnTile(int x, int y) {
        Piece piece = getPieceOnTile(x, y);
        if (piece == null) return null;
        return piece.getPieceColor();
    }

    public boolean areCoordsInBounds(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) return true;
        return false;
    }

    public boolean isThereAnyPossibleBlackMoves() {
        return isThereAnyPossibleBlackMoves;
    }

    public boolean isThereAnyPossibleWhiteMoves() {
        return isThereAnyPossibleWhiteMoves;
    }

    public Piece[][] getPieces() {
        return pieces;
    }

    public Boolean[][] getWhiteAttack() {
        return whiteAttack;
    }

    public Boolean[][] getBlackAttack() {
        return blackAttack;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<width;i++){
            for(int j=0;j<height;j++){
                Piece piece = pieces[i][j];
                if(piece == null){
                    sb.append("--- ");
                }else{
                    sb.append(pieces[i][j].toString()).append(" ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
