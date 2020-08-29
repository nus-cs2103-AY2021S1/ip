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

//    @Test
//    public void parseDoneTest() {
//        Storage store = new Storage("data/doke.txt");
//        TaskList list = store.load();
//        list.updateStatus(1);
//        String check = "[T][1]help me out\n" +
//                "[D][1]I need your help|23/02/2020 12:00\n" +
//                "[E][1]We need to talk about the tests|23/02/2020 12:00";
//        assertEquals(check, list.save());
//    }
}
