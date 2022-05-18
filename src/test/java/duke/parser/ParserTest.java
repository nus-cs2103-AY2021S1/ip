package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.exceptions.DukeException;

public class ParserTest {

    private static Parser parser = new Parser();

    @Test
    public void parseUserInput_correctInput_correctCommand() {
        String[] userInput = new String[] {
            "todo sleep",
            "deadline assignment /by 30 Aug 2020 2359",
            "done 2",
            "list",
            "bye",
            "delete 3",
            "event meeting /at 24 Aug 2020 1000",
        };

        Command[] expectedCommands = new Command[] {
            new TodoCommand("sleep"),
            new DeadlineCommand("assignment", "30 Aug 2020 2359"),
            new DoneCommand(2),
            new ListCommand(),
            new ByeCommand(),
            new DeleteCommand(3),
            new EventCommand("meeting", "24 Aug 2020 1000")
        };

        try {
            for (int i = 0; i < userInput.length; i++) {
                Command c = parser.parse(userInput[i]);
                assertEquals(expectedCommands[i], c);
            }
        } catch (DukeException e) {
            e.printStackTrace();
            fail("test should not have thrown an exception");
        }
    }

    @Test
    public void parseUserInput_emptyTaskBody_throwsException() {
        String[] userInput = new String[] {
            "todo ",
            "deadline ",
            "event ",
        };
        for (String input : userInput) {
            assertThrows(DukeException.class, () -> parser.parse(input));
        }
    }

}
