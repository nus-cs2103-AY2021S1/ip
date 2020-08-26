package duke.command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserCommandsTest {

    @Test
    void testGetTodoCommandWord() {
        assertEquals("todo", UserCommands.TODO.getCommandWord());
    }

    @Test
    void testGetDeadlineCommandWord() {
        assertEquals("deadline", UserCommands.DEADLINE.getCommandWord());
    }
    
    @Test
    void testGetEventCommandWord() {
        assertEquals("event", UserCommands.EVENT.getCommandWord());
    }
    
    @Test
    void testGetDeleteCommandWord() {
        assertEquals("delete", UserCommands.DELETE.getCommandWord());
    }

    @Test
    void testGetDoneCommandWord() {
        assertEquals("done", UserCommands.DONE.getCommandWord());
    }

    @Test
    void testGetExitCommandWord() {
        assertEquals("bye", UserCommands.EXIT.getCommandWord());
    }

    @Test
    void testGetListCommandWord() {
        assertEquals("list", UserCommands.LIST.getCommandWord());
    }
}