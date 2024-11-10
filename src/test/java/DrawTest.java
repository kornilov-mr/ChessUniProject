import logic.BoardController;
import logic.BoardControllerBuilder;
import logic.pieceDisposition.BoardDispositionSetting;
import org.junit.Assert;
import org.junit.Test;

public class DrawTest {

    @Test
    public void testDraw() {
        BoardControllerBuilder builder = new BoardControllerBuilder();
        builder.setBoardDispositionSetting(new BoardDispositionSetting("src/test/java/DispositionsForTesting/TableWithDraw.sp"));
        BoardController boardController = builder.build();
        Assert.assertTrue(boardController.isGameInDraw());
    }

}
