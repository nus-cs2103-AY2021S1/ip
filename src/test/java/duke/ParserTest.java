package duke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParserTest {

    private Parser parser;

    @BeforeEach
    public void beforeEachTestMethod() {
        Storage storage = new Storage("../data", "../data/duke.txt", new Ui(), new TaskList());
        this.parser = new Parser(storage);
    }

    @Test
    public void testByeCommand_extraUserInput_dukeExceptionThrown() {
        Assertions.assertThrows(DukeInputException.class, () -> this.parser.processInput("bye bye"));
    }

    @Test
    public void testListCommand_extraUserInput_dukeExceptionThrown() {
        Assertions.assertThrows(DukeInputException.class, () -> this.parser.processInput("list 1"));
    }

    @Test
    public void testDeleteCommand_invalidFormat_dukeExceptionThrown() {
        Assertions.assertThrows(DukeInputException.class, () -> this.parser.processInput("delete"));
        Assertions.assertThrows(DukeInputException.class, () -> this.parser.processInput("delete a"));
    }

    @Test
    public void testDoneCommand_invalidFormat_dukeExceptionThrown() {
        Assertions.assertThrows(DukeInputException.class, () -> this.parser.processInput("done"));
        Assertions.assertThrows(DukeInputException.class, () -> this.parser.processInput("done a"));
    }

    @Test
    public void testGetCommand_invalidFormat_dukeExceptionThrown() {
        Assertions.assertThrows(DukeInputException.class, () -> this.parser.processInput("get"));
        Assertions.assertThrows(DukeInputException.class, () -> this.parser.processInput("get a"));
        Assertions.assertThrows(DukeInputException.class, () -> this.parser.processInput("get 01/09/2020"));
    }

    @Test
    public void testTodoCommand_invalidFormat_dukeExceptionThrown() {
        Assertions.assertThrows(DukeInputException.class, () -> this.parser.processInput("todo"));
    }

    @Test
    public void testEventCommand_invalidFormat_dukeExceptionThrown() {
        Assertions.assertThrows(DukeInputException.class, () -> this.parser.processInput("event project meeting"));
        Assertions.assertThrows(DukeInputException.class, () -> this.parser.processInput("event project meeting"));
        Assertions.assertThrows(DukeInputException.class, () -> this.parser.processInput("event project meeting /at"));
        Assertions.assertThrows(DukeInputException.class, () -> this.parser.processInput("event project meeting /at 01/09/2020"));
        Assertions.assertThrows(DukeInputException.class, () -> this.parser.processInput("event project meeting /by 01/09/2020 0800"));
    }

    @Test
    public void testDeadlineCommand_invalidFormat_dukeExceptionThrown() {
        Assertions.assertThrows(DukeInputException.class, () -> this.parser.processInput("return book"));
        Assertions.assertThrows(DukeInputException.class, () -> this.parser.processInput("return book"));
        Assertions.assertThrows(DukeInputException.class, () -> this.parser.processInput("return book /by"));
        Assertions.assertThrows(DukeInputException.class, () -> this.parser.processInput("return book /by 01/09/2020"));
        Assertions.assertThrows(DukeInputException.class, () -> this.parser.processInput("return book /at 01/09/2020 1800"));
    }
}
