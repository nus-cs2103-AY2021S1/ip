package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.InvalidUserCommandException;
import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;

class ParserTest {

    @Test
    void parseCommand_validExitCommand() {
        String validExitCommand = "bye";
        String expectedResponse = "Goodbye! Have a nice day :D";
        Ui ui = new Ui();
        Storage storage = new Storage("data/tasks");
        String actualResponse = Parser.parseCommands(validExitCommand, ui, storage);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void parseCommand_invalidExitCommand_exceptionThrown() {
        String invalidExitCommand = "bye bye";
        String expectedResponse = "Goodbye! Have a nice day :D";
        Ui ui = new Ui();
        Storage storage = new Storage("data/tasks");

        try {
            String actualResponse = Parser.parseCommands(invalidExitCommand, ui, storage);
            assertEquals(expectedResponse, actualResponse);
            fail(); // Should not reach this line
        } catch (InvalidUserCommandException e) {
            String expectedErrorMessage = "Sorry but I don't understand what 'bye bye' means :(";
            assertEquals(expectedErrorMessage, e.getMessage());
        }
    }

    @Test
    void parseCommand_validListCommand_noTasksInTaskListFile() {
        String validListCommand = "list";
        String expectedResponse = "List is empty :(";
        Ui ui = new Ui();
        Storage storage = new Storage("data/tasks");
        String actualResponse = Parser.parseCommands(validListCommand, ui, storage);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void parseCommand_validListCommand_tasksInTaskListFilePresent() {
        String validListCommand = "list";
        String expectedResponse = "Your current task list is as follows:\n";

        Ui ui = new Ui();
        Storage storage = new Storage("data/tasks");
        Parser.parseCommands("todo homework", ui, storage);

        expectedResponse += "1.[T]" + Task.STATUS_CROSS + " homework\n";
        String actualResponse = Parser.parseCommands(validListCommand, ui, storage);

        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void parseCommand_invalidUserCommand_exceptionThrown() {
        String invalidExitCommand = "hey there";
        String expectedResponse = "hello";
        Ui ui = new Ui();
        Storage storage = new Storage("data/tasks");

        try {
            String actualResponse = Parser.parseCommands(invalidExitCommand, ui, storage);
            assertEquals(expectedResponse, actualResponse);
            fail(); // Should not reach this line
        } catch (InvalidUserCommandException e) {
            String expectedErrorMessage = "Sorry but I don't understand what 'hey there' means :(";
            assertEquals(expectedErrorMessage, e.getMessage());
        }
    }

    @Test
    void parseCommand_emptyUserCommand_exceptionThrown() {
        String invalidExitCommand = "";
        String expectedResponse = "";
        Ui ui = new Ui();
        Storage storage = new Storage("data/tasks");

        try {
            String actualResponse = Parser.parseCommands(invalidExitCommand, ui, storage);
            assertEquals(expectedResponse, actualResponse);
            fail(); // Should not reach this line
        } catch (InvalidUserCommandException e) {
            String expectedErrorMessage = "Sorry but I don't understand what '' means :(";
            assertEquals(expectedErrorMessage, e.getMessage());
        }
    }
}
