package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class UserCommandsTest {

    @Test
    void getCommandWord_todoCommandWord() {
        assertEquals("todo", UserCommands.TODO.getCommandWord());
    }

    @Test
    void getCommandWord_deadlineCommandWord() {
        assertEquals("deadline", UserCommands.DEADLINE.getCommandWord());
    }

    @Test
    void getCommandWord_eventCommandWord() {
        assertEquals("event", UserCommands.EVENT.getCommandWord());
    }

    @Test
    void getCommandWord_deleteCommandWord() {
        assertEquals("delete", UserCommands.DELETE.getCommandWord());
    }

    @Test
    void getCommandWord_doneCommandWord() {
        assertEquals("done", UserCommands.DONE.getCommandWord());
    }

    @Test
    void getCommandWord_exitCommandWord() {
        assertEquals("bye", UserCommands.EXIT.getCommandWord());
    }

    @Test
    void getCommandWord_findCommandWord() {
        assertEquals("find", UserCommands.FIND.getCommandWord());
    }

    @Test
    void getCommandWord_listCommandWord() {
        assertEquals("list", UserCommands.LIST.getCommandWord());
    }
}
