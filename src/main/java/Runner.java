import logic.BoardController;
import logic.BoardControllerBuilder;
import logic.Move;
import logic.chessPieces.PieceColor;
import logic.pieceDisposition.BoardDispositionEnum;

import java.io.*;

import static java.lang.System.*;

public class Runner {

    public static void main(String[] args) {


        BoardControllerBuilder builder = new BoardControllerBuilder();
        builder.setBoardDispositionSetting(BoardDispositionEnum.BASIC_POSITION_OF_PIECES_8X8.getBoardDispositionSetting());
        BoardController boardController = builder.build();
        GameController gameController = new GameController(boardController);
        out.println(gameController.getBoard());
        BufferedReader scanner = null;
        try {
            scanner = new BufferedReader(new InputStreamReader(System.in));
            while(true){
                String moveString = scanner.readLine();
                String[] moveArgs = moveString.split(" ");
                Move move = new Move(Integer.parseInt(moveArgs[0]),Integer.parseInt(moveArgs[1]),Integer.parseInt(moveArgs[2]),Integer.parseInt(moveArgs[3]));
                gameController.makeAMove(move);
                System.out.println(gameController.getBoard());
                if (gameController.isGameInDraw()){
                    out.println("Draw");
                    return;
                }
                PieceColor colorOfWinning = gameController.whoWonTheGame();
                if(colorOfWinning!=null){
                    out.println(colorOfWinning +" Won");
                    return;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
