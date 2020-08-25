package command;

import exceptions.CommandException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Test
    public void testList() throws CommandException {
        assertEquals(Parser.parseCommand("list"), Command.LIST_CMD);
    }

    @Test
    public void testTodo() throws CommandException {
        assertEquals(Parser.parseCommand("todo foo"), Command.TODO_CMD);
    }

    @Test
    public void testExit() throws CommandException {
        assertEquals(Parser.parseCommand("bye"), Command.EXIT_CMD);
    }

    @Test
    public void testInvalidCommand() {
        assertThrows(CommandException.class, () -> Parser.parseCommand("blah blah"));
    }

}
