package logic.pieceDisposition;

public enum BoardDispositionEnum {
    BASIC_POSITION_OF_PIECES_8X8("src/main/java/logic/pieceDisposition/Basic_Position_Of_pieces.sp");
    private final BoardDispositionSetting boardDispositionSetting;

    BoardDispositionEnum(String settingFilePath) {
        this.boardDispositionSetting = new BoardDispositionSetting(settingFilePath);
    }

    public BoardDispositionSetting getBoardDispositionSetting() {
        return boardDispositionSetting;
    }
}
