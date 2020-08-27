package duke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParserTest {

    private Parser parser;

    @BeforeEach
    public void beforeEachTestMethod() {
        this.parser = new Parser(new Storage("../data", "../data/duke.txt", new Ui(), new TaskList()));
    }

    @Test
    public void processInput_unknownCommand_dukeExceptionThrown() {
        Assertions.assertThrows(DukeException.class, () -> this.parser.processInput("bye bye"));
    }

    @Test
    public void processInput_invalidDeleteCommand_dukeExceptionThrown() {
        Assertions.assertThrows(DukeException.class, () -> this.parser.processInput("delete"));
        Assertions.assertThrows(DukeException.class, () -> this.parser.processInput("delete a"));
    }

    @Test
    public void processInput_invalidDoneCommand_dukeExceptionThrown() {
        Assertions.assertThrows(DukeException.class, () -> this.parser.processInput("done"));
        Assertions.assertThrows(DukeException.class, () -> this.parser.processInput("done a"));
    }

    @Test
    public void processInput_invalidGetCommand_dukeExceptionThrown() {
        Assertions.assertThrows(DukeException.class, () -> this.parser.processInput("get"));
        Assertions.assertThrows(DukeException.class, () -> this.parser.processInput("get a"));
        Assertions.assertThrows(DukeException.class, () -> this.parser.processInput("get 01/09/2020"));
    }

    @Test
    public void processInput_invalidTodoCommand_dukeExceptionThrown() {
        Assertions.assertThrows(DukeException.class, () -> this.parser.processInput("todo"));
    }

    @Test
    public void processInput_invalidEventCommand_dukeExceptionThrown() {
        Assertions.assertThrows(DukeException.class, () -> this.parser.processInput("event project meeting"));
        Assertions.assertThrows(DukeException.class, () -> this.parser.processInput("event project meeting"));
        Assertions.assertThrows(DukeException.class, () -> this.parser.processInput("event project meeting /at"));
        Assertions.assertThrows(DukeException.class, () -> this.parser.processInput("event project meeting /at 01/09/2020"));
        Assertions.assertThrows(DukeException.class, () -> this.parser.processInput("event project meeting /by 01/09/2020 0800"));
    }

    @Test
    public void processInput_invalidDeadlineCommand_dukeExceptionThrown() {
        Assertions.assertThrows(DukeException.class, () -> this.parser.processInput("return book"));
        Assertions.assertThrows(DukeException.class, () -> this.parser.processInput("return book"));
        Assertions.assertThrows(DukeException.class, () -> this.parser.processInput("return book /by"));
        Assertions.assertThrows(DukeException.class, () -> this.parser.processInput("return book /by 01/09/2020"));
        Assertions.assertThrows(DukeException.class, () -> this.parser.processInput("return book /at 01/09/2020 1800"));
    }
}
