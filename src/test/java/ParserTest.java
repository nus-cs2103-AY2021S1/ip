import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import exceptions.EmptyTodoException;
import exceptions.InvalidEventException;

import java.util.ArrayList;


public class ParserTest {
    @Test
    public void parseDateTest() throws InvalidEventException {
        assertEquals(Parser.getEventTest("event do /at 11/11/2020 23:59", new TaskList(new ArrayList<>())),
                "[E][Not Done] do (at: 11/11/2020 23:59)");
    }

    @Test
    public void parseTodoTest() throws EmptyTodoException {
        assertEquals(Parser.getTodo("todo help me out sir", new TaskList(new ArrayList<>())),
                "[T][Not Done] help me out sir");
    }
}
