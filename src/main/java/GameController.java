import logic.BoardController;
import logic.Move;
import logic.MoveIsNotValidException;
import logic.chessPieces.PieceColor;

public class GameController {


    private PieceColor currentColorToMove= PieceColor.White;
    private final BoardController boardController;

    public GameController(BoardController boardController) {
        this.boardController = boardController;
    }

    public void makeAMove(Move move){
        try {
            boardController.makeAMove(move,currentColorToMove);
        } catch (MoveIsNotValidException e){
            System.out.println("Move isn't valid");
        }
        changeColor();
    }
    public String getBoard(){
        return boardController.getBoardAsString();
    }
    private void changeColor(){
        currentColorToMove = currentColorToMove.equals(PieceColor.Black)? PieceColor.Black: PieceColor.White;
    }
    public boolean isGameInDraw(){
        return boardController.isGameInDraw();
    }
    public PieceColor whoWonTheGame(){
        return boardController.whoWonTheGame();
    }


}
