package duke.parser;

import duke.command.*;

import duke.exceptions.DukeException;
import duke.exceptions.EmptyBodyException;
import duke.parser.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


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
            assertThrows(DukeException.class, () -> {
                parser.parse(input);
            });
        }
    }

}
