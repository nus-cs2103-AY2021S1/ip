import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.Parser;
import duke.exception.DukeException;

import java.util.ArrayList;

public class ParserTest {

    @Test
    public void parser_invalidInput_throwsException() {
        TaskList tasks = new TaskList(new ArrayList<>());
        Ui ui = new Ui();
        Storage storage = new Storage();
        DukeException ex = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse("invalid input").execute(tasks, ui, storage);
        });
        String expected = "Oh no! Invalid Function!\n"
                + "Type 'help' for a list of all my functions and their commands.";
        assertEquals(expected, ex.getMessage());
    }

    @Test
    public void parser_missingUserInput_throwsException() {
        TaskList tasks = new TaskList(new ArrayList<>());
        Ui ui = new Ui();
        Storage storage = new Storage();
        DukeException ex = Assertions.assertThrows(DukeException.class, () -> {
            Parser.parse(" ").execute(tasks, ui, storage);
        });
        String expected = "Oh no! No input was entered! Please enter something!\n"
                + "Type 'help' for a list of all my functions and their commands.";
        assertEquals(expected, ex.getMessage());
    }

}