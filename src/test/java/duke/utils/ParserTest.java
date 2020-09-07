package duke.utils;

import duke.exceptions.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    private static final char FROWN = '\u2639';
    Parser parser = new Parser();

    @Test
    void extractAction() {
        try {
            String commandLine1 = "todo borrow book";
            String[] split = parser.extractAction(commandLine1);
            assertEquals("todo", split[0]);
            assertEquals("borrow book", split[1]);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    void extractAction_emptyCommand_exceptionThrown() {
        try {
            String commandLine = "";
            parser.extractAction(commandLine);
            fail();
        } catch (DukeException e) {
            assertEquals(FROWN + " OOPS!!! The command cannot be empty!", e.getMessage());
        }
    }

    @Test
    void extractAction_wrongSpelling_exceptionThrown() {
        try {
            String command = "top up";
            parser.extractAction(command);
            fail();
        } catch (DukeException e) {
            assertEquals(FROWN + " OOPS!!! Check if you have spelled correctly!", e.getMessage());
        }
    }


    @Test
    void extractTime() {
        try {
            String command = "return book /by 2020-08-23";
            String[] split = parser.extractDate(command);
            assertEquals("return book", split[0]);
            assertEquals("2020-08-23", split[1]);
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    void extractTime_timeNotGiven_exceptionThrown() {
        try {
            String command = "return book";
            parser.extractDate(command);
            fail();
        } catch (DukeException e) {
            assertEquals(e.getMessage(), FROWN + " OOPS!!! Seems you forgot to supply the time!\n" +
                    "Simply add '/by <time>' for deadline OR '/at <time>' for event behind your command");
        }
    }

    @Test
    void extractTime_contentNotGiven_exceptionThrown() {
        try {
            String command = "/by 2020-08-23";
            parser.extractDate(command);
            fail();
        } catch (DukeException e) {
            assertEquals(FROWN + " OOPS!!! Seems you forgot to supply the main content!", e.getMessage());
        }
    }

    @Test
    void extractTime_wrongDateFormat_exceptionThrown() {
        try {
            String command = "return book /by tomorrow";
            parser.extractDate(command);
            fail();
        } catch (DukeException e) {
            assertEquals(FROWN + " OOPS!!! The format for your date is incorrect."
                    + " Use yyyy-MM-dd format instead", e.getMessage());
        }
    }
}
