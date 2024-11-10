package logic.pieceDisposition;

import logic.chessPieces.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BoardDispositionSetting {

    private final File settingFile;
    private final Piece[][] pieces;

    private int height;
    private int width;
    private Piece whiteKing;
    private Piece blackKing;
    public BoardDispositionSetting(String settingFilePath) {
        this.settingFile = new File(settingFilePath);
        this.pieces = getPiecesFromSettingFile(settingFile);
    }
    private Piece[][] getPiecesFromSettingFile(File settingFile) {
        if(!settingFile.exists()) throw new RuntimeException("Setting file "+settingFile.getAbsolutePath()+" does not exist");
        try (BufferedReader reader = new BufferedReader(new FileReader(settingFile))) {
            String configLine = reader.readLine();
            String[] size = configLine.split(" ");
            this.width = Integer.parseInt(size[0]);
            this.height = Integer.parseInt(size[1]);
            Piece[][] pieces = new Piece[width][height];
            int lineCount = 0;
            while(reader.ready()) {
                String line = reader.readLine();
                String[] piecesInLine = line.trim().split(" ");
                for (int i = 0; i < piecesInLine.length; i++) {
                    switch (piecesInLine[i]) {
                        case "roW":
                            pieces[lineCount][i] = new Rook(i,lineCount, PieceColor.White);
                            break;
                        case "knW":
                            pieces[lineCount][i] = new Knight(i,lineCount, PieceColor.White);
                            break;
                        case "biW":
                            pieces[lineCount][i] = new Bishop(i,lineCount, PieceColor.White);
                            break;
                        case "quW":
                            pieces[lineCount][i] = new Queen(i,lineCount, PieceColor.White);
                            break;
                        case "kiW":
                            pieces[lineCount][i] = new King(i,lineCount, PieceColor.White);
                            this.whiteKing= pieces[lineCount][i];
                            break;
                        case "paW":
                            pieces[lineCount][i] = new Pawn(i,lineCount, PieceColor.White);
                            break;
                        case "roB":
                            pieces[lineCount][i] = new Rook(i,lineCount, PieceColor.Black);
                            break;
                        case "knB":
                            pieces[lineCount][i] = new Knight(i,lineCount, PieceColor.Black);
                            break;
                        case "biB":
                            pieces[lineCount][i] = new Bishop(i,lineCount, PieceColor.Black);
                            break;
                        case "quB":
                            pieces[lineCount][i] = new Queen(i,lineCount, PieceColor.Black);
                            break;
                        case "kiB":
                            pieces[lineCount][i] = new King(i,lineCount, PieceColor.Black);
                            this.blackKing= pieces[lineCount][i];
                            break;
                        case "paB":
                            pieces[lineCount][i] = new Pawn(i,lineCount, PieceColor.Black);
                            break;
                    }
                }
                lineCount++;

            }
            return pieces;
        }catch (IOException e) {
            throw new RuntimeException("Error reading setting file "+settingFile.getAbsolutePath(),e);
        }
    }

    public Piece[][] getPieces() {
        return pieces;
    }

    public Piece getWhiteKing() {
        return whiteKing;
    }

    public Piece getBlackKing() {
        return blackKing;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
