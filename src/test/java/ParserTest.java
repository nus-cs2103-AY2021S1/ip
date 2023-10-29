import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.Parser;
import duke.backend.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ParserTest {
    private static final String FILEPATH = "data/test.txt";
    private static final String BYE_INPUT = "bye";
    private static final String LIST_INPUT = "list";
    private static final String FIND_INPUT = "find this";

    private static final String BYE_OUTPUT = "Duke: Bye. Hope to see you again!";
    private static final String LIST_OUTPUT = "Duke: Here are your tasks:\n"
            + "1. [T][1] this\n2. [D][0] this - 2020-09-15\n3. [E][1] this - 2020-09-15\n";
    private static final String FIND_OUTPUT = "Duke: Here are the matching tasks found:\n"
            + "[T][1] this\n[D][0] this - 2020-09-15\n[E][1] this - 2020-09-15\n";

    @Test
    public void parseBye() throws IOException, DukeException {
        Storage storage = new Storage(FILEPATH);
        String message = Parser.parseInput(BYE_INPUT, new Ui(), new TaskList(storage.loadFile()), storage);
        assertEquals(message, BYE_OUTPUT);
    }

    @Test
    public void parseList() throws IOException, DukeException {
        Storage storage = new Storage(FILEPATH);
        String message = Parser.parseInput(LIST_INPUT, new Ui(), new TaskList(storage.loadFile()), storage);
        assertEquals(message, LIST_OUTPUT);
    }

    @Test
    public void parseFind() throws IOException, DukeException {
        Storage storage = new Storage(FILEPATH);
        String message = Parser.parseInput(FIND_INPUT, new Ui(), new TaskList(storage.loadFile()), storage);
        assertEquals(message, FIND_OUTPUT);
    }
}
