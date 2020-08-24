package duke;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.ListCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void parseCommand_unkownCommand_exceptionThrown() {
        try {
            Command c = Parser.parse("asd");
        } catch (DukeException e) {
            assertTrue(e.getMessage().contains("I'm sorry, but I don't know what that means :-("));
        }
    }

    @Test
    public void parseCommand_byeCommand_byeCommandObjectCreated() {
        try {
            Command c = Parser.parse("bye");
            assertTrue(c instanceof ByeCommand);

        } catch (DukeException e) {
            System.out.println("Test failed");
        }
    }

    @Test
    public void parseCommand_listCommand_listCommandObjectCreated() {
        try {
            Command c = Parser.parse("list");
            assertTrue(c instanceof ListCommand);

        } catch (DukeException e) {
            System.out.println("Test failed");
        }
    }

    @Test
    public void parseCommand_unspecifiedDoneCommand_exceptionThrown() {
        try {
            Command c = Parser.parse("done");
        } catch (DukeException e) {
            assertTrue(e.getMessage().contains("Please enter a valid task number for me to mark as done."));
        }
    }
}
