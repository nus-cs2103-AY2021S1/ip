package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.task.TaskList;

public class ParserTest {

    @Test
    public void emptyCommand_errorThrown() {
        Parser parser = new Parser(new TaskList());
        try {
            parser.processCommand("");
            fail();
        } catch (DukeException e) {
            assertEquals("Sorry, I did not understand: .\n"
                    + "Use \"help\" to look at available commands.", e.getMessage());
        }
    }

    @Test
    public void event_badDate_errorThrown() {
        Parser parser = new Parser(new TaskList());
        try {
            parser.processCommand("event testing /at 22-03-2020");
            fail();
        } catch (DukeException e) {
            assertEquals("Please write your date in the format \"dd/MM/yyyy\"", e.getMessage());
        }
    }

    @Test
    public void deadline_badDate_errorThrown() {
        Parser parser = new Parser(new TaskList());
        try {
            parser.processCommand("deadline testing /at 22-03-2020");
            fail();
        } catch (DukeException e) {
            assertEquals("Please write your date in the format \"dd/MM/yyyy\"", e.getMessage());
        }
    }

    @Test
    public void event_noDate_errorThrown() {
        Parser parser = new Parser(new TaskList());
        try {
            String result = parser.processCommand("event testing /at");
            fail();
        } catch (DukeException e) {
            assertEquals("Please write your date in the format \"dd/MM/yyyy\"", e.getMessage());
        }
    }

    @Test
    public void deadline_noDate_errorThrown() {
        Parser parser = new Parser(new TaskList());
        try {
            String result = parser.processCommand("deadline testing /at");
            fail();
        } catch (DukeException e) {
            assertEquals("Please write your date in the format \"dd/MM/yyyy\"", e.getMessage());
        }
    }


}
