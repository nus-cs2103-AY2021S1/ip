import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DukeTest {

    public DukeException testEventMethod(String command) {
        try {
            new EventCommand(command).execute(null, null, null);
        } catch (DukeException e) {
            return e;
        }
        return new DukeException();
    }

    public DukeException testDeadlineMethod(String command) {
        try {
            new DeadlineCommand(command).execute(null, null, null);
        } catch (DukeException e) {
            return e;
        }
        return new DukeException();
    }

    public DukeException testToDoMethod(String command) {
        try {
            new ToDoCommand(command).execute(null, null, null);
        } catch (DukeException e) {
            return e;
        }
        return new DukeException();
    }

    @Test
    public void ParserTest1(){
        assertTrue(Parser.interpret("/at") instanceof UnknownCommand);
        assertTrue(Parser.interpret("/by") instanceof UnknownCommand);
        assertTrue(Parser.interpret("123") instanceof UnknownCommand);
        assertTrue(Parser.interpret("abc") instanceof UnknownCommand);
        assertTrue(Parser.interpret("list") instanceof ListCommand);
        assertTrue(Parser.interpret("bye") instanceof ByeCommand);
        assertTrue(Parser.interpret("event") instanceof EventCommand);
        assertTrue(Parser.interpret("deadline") instanceof DeadlineCommand);
        assertTrue(Parser.interpret("todo") instanceof ToDoCommand);
    }

    @Test
    public void EventTest() {
        assertTrue(testEventMethod("event") instanceof MissingDescriptionException);
        assertTrue(testEventMethod("event /at") instanceof MissingDescriptionException);
        assertTrue(testEventMethod("event e /at") instanceof MissingDateTimeException);
        assertTrue(testEventMethod("event at") instanceof MissingTagException);
        assertTrue(testEventMethod("event e /at 12/12/2020") instanceof MissingDateTimeException);
        assertTrue(testEventMethod("event e /at") instanceof MissingDateTimeException);
        assertTrue(testEventMethod("event e /at 1230") instanceof MissingDateTimeException);
    }

    @Test
    public void DeadlineTest() {
        assertTrue(testDeadlineMethod("deadline") instanceof MissingDescriptionException);
        assertTrue(testDeadlineMethod("deadline /by") instanceof MissingDescriptionException);
        assertTrue(testDeadlineMethod("deadline d /by") instanceof MissingDateTimeException);
        assertTrue(testDeadlineMethod("deadline by") instanceof MissingTagException);
        assertTrue(testDeadlineMethod("deadline d /by 12/12/2020") instanceof MissingDateTimeException);
        assertTrue(testDeadlineMethod("deadline d /by") instanceof MissingDateTimeException);
        assertTrue(testDeadlineMethod("deadline d /by 1230") instanceof MissingDateTimeException);
    }

    @Test
    public void ToDoTest() {
        assertTrue(testToDoMethod("todo") instanceof MissingDescriptionException);
    }

}
