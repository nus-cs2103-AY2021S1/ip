import exceptions.EmptyTodoException;
import exceptions.InvalidEventException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void parseDateTest() throws InvalidEventException {
        assertEquals(Parser.getEventTest("event do /at 11/11/2020 23:59").toString(), "[E][✗] do (at: Nov 11 2020 1159)");
    }

    @Test
    public void parseTodoTest() throws EmptyTodoException {
        assertEquals(Parser.getTodo("todo help me out sir").toString(), "[T][✗] help me out sir");
    }

    @Test
    public void parseDoneTest() {
        Storage store = new Storage("data/duke.txt");
        TaskList list = store.load();
        list.updateStatus(1);
        String check = "[D][✓] help me (by: Feb 02 2020 0923)\n" +
                "[E][✓] help (at: Mar 02 2020 1145)\n" +
                "[E][✗] do (at: Jan 01 1111 1133)\n";
        assertEquals(check, list.toString());
    }
}
