import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ParserTest {
    @Test
    public void checkBye() {
        assertTrue(Parser.checkBye("bye"));
    }

    @Test
    public void checkDone() {
        assertTrue(Parser.checkDone("done"));
    }

    @Test
    public void checkList() {
        assertTrue(Parser.checkList("list"));
    }

    @Test
    public void checkDel() {
        assertTrue(Parser.checkDel("delete"));
    }
}
