import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import exceptions.EmptyTodoException;
import exceptions.InvalidEventException;



public class ParserTest {
    @Test
    public void parseDateTest() throws InvalidEventException {
        assertEquals(Parser.getEventTest("event do /at 11/11/2020 23:59").toString(),
                "[E][✗] do (at: Nov 11 2020 1159)");
    }

    @Test
    public void parseTodoTest() throws EmptyTodoException {
        assertEquals(Parser.getTodo("todo help me out sir").toString(),
                "[T][✗] help me out sir");
    }
}
