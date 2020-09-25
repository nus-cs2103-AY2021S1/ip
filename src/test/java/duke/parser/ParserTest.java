package duke.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import duke.exception.UnknownCommandException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.textui.Ui;

public class ParserTest {

    private Parser parser;

    @BeforeEach
    void init() {
        parser = new Parser();
    }

    @Test
    @DisplayName("Testing unknown command")
    public void parse_invalidInput_unknownCommandExceptionThrown() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage();
        assertThrows(UnknownCommandException.class, () -> parser.parse("abcdefg").execute(tasks, ui, storage));
    }
}
