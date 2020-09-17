package duke.main;

import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void checkInput_doneIncomplete_exceptionThrown() {
        try {
            Parser.checkInput("done");
            fail();
        } catch (DukeException e) {
            assertEquals(" Oh no! Please specify which task to be marked as done.", e.getMessage());
        }
    }

    @Test
    public void checkInput_deleteIncomplete_exceptionThrown() {
        try {
            Parser.checkInput("delete");
            fail();
        } catch (DukeException e) {
            assertEquals(" Oh no! Please specify which task to be deleted.", e.getMessage());
        }
    }

    @Test
    public void checkInput_deadlineIncomplete_exceptionThrown() {
        try {
            Parser.checkInput("deadline");
            fail();
        } catch (DukeException e) {
            assertEquals(" Oh no! Please specify the description of a deadline.", e.getMessage());
        }
    }

    @Test
    public void checkInput_eventIncomplete_exceptionThrown() {
        try {
            Parser.checkInput("event");
            fail();
        } catch (DukeException e) {
            assertEquals(" Oh no! Please specify the description of an event.", e.getMessage());
        }
    }

    @Test
    public void checkInput_todoIncomplete_exceptionThrown() {
        try {
            Parser.checkInput("todo");
            fail();
        } catch (DukeException e) {
            assertEquals(" Oh no! Please specify the description of a todo.", e.getMessage());
        }
    }

    @Test
    public void checkInput_noInput_exceptionThrown() {
        try {
            Parser.checkInput("");
            fail();
        } catch (DukeException e) {
            assertEquals(" Sorry, no input was detected :(", e.getMessage());
        }
    }

    @Test
    public void checkInput_unknownInput_exceptionThrown() {
        try {
            Parser.checkInput("halo");
            fail();
        } catch (DukeException e) {
            assertEquals(" Oh no! Sorry, but I do not know what that means :(", e.getMessage());
        }
    }

    @Test
    public void checkInput_deleteOutOfList_exceptionThrown() {
        try {
            Parser.checkInput("delete", "-1");
            fail();
        } catch (DukeException e) {
            String s = " Oh no! Task number cannot be zero or negative"
                    + "\n" + " Please refer to your task list to find the"
                    + "\n" + " appropriate task number :)";
            assertEquals(s, e.getMessage());
        }
    }

    @Test
    public void checkInput_doneOutOfList_exceptionThrown() {
        try {
            Parser.checkInput("done", "-1");
            fail();
        } catch (DukeException e) {
            String s = " Oh no! Task number cannot be zero or negative"
                + "\n" + " Please refer to your task list to find the"
                + "\n" + " appropriate task number :)";
            assertEquals(s, e.getMessage());
        }
    }
}
