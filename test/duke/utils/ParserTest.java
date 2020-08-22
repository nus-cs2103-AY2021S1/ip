package duke.utils;

import duke.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    Parser parser = new Parser();

    @Test
    void extractAction() {
        try {
            String commandLine1 = "todo borrow book";
            String[] split = parser.extractAction(commandLine1);
            assertEquals(split[0], "todo");
            assertEquals(split[1], "borrow book");
        } catch (DukeException ignored) {}
    }

    @Test
    void extractAction_emptyCommand_exceptionThrown() {
        try {
            String commandLine = "";
            parser.extractAction(commandLine);
            fail();
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "☹ OOPS!!! The command cannot be empty!");
        }
    }

    @Test
    void extractAction_wrongSpelling_exceptionThrown() {
        try {
            String command = "top up";
            parser.extractAction(command);
            fail();
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "☹ OOPS!!! Check if you have spelled correctly!");
        }
    }


    @Test
    void extractTime() {
        try {
            String command = "return book /by 2020-08-23";
            String[] split = parser.extractDate(command);
            assertEquals("return book", split[0]);
            assertEquals("2020-08-23", split[1]);
        } catch (DukeException ignored) {}
    }

    @Test
    void extractTime_timeNotGiven_exceptionThrown() {
        try {
            String command = "return book";
            parser.extractDate(command);
            fail();
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "☹ OOPS!!! Seems you forgot to supply the time!\n" +
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
            assertEquals(e.getMessage(), "☹ OOPS!!! Seems you forgot to supply the main content!");
        }
    }

    @Test
    void extractTime_wrongDateFormat_exceptionThrown() {
        try {
            String command = "return book /by tomorrow";
            parser.extractDate(command);
            fail();
        } catch (DukeException e) {
            assertEquals(e.getMessage(), "☹ OOPS!!! The format for your date is incorrect. Use yyyy-MM-dd instead");
        }
    }
}