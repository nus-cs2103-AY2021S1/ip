package godfather;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import godfather.enums.Message;
import godfather.exception.VitoException;


public class ParserTest {
    @ParameterizedTest
    @ValueSource(strings = {"deadline return book /by 19/02/2020 0900", "event project meeting /at Mon 2-4pm", "todo "
            + "join sports club"})
    public void parsingCommand_parameterizedTest_validCommandsCreated(String input) throws VitoException {
        Parser p = new Parser();
        assert (p.parseCommand(input) != null); // successful creation
    }
    @Test
    public void exceptionHandling_insufficientTodoDescription_dukeExceptionReturned() {
        String input = "todo";
        String expectedMsg = Message.ERROR_TODO_DESC.getMsg();
        Parser p = new Parser();
        VitoException de = assertThrows(VitoException.class, () -> {
            p.parseCommand(input);
        });
        assertEquals(expectedMsg, de.getMessage());
    }
    @ParameterizedTest
    @ValueSource(strings = {"tod", "blah"})
    public void exceptionHanlding_unknownCommand_dukeExceptionReturned(String s) {
        String expectedMsg = Message.ERROR_UNKNOWN_CMD.getMsg();
        Parser p = new Parser();
        VitoException de = assertThrows(VitoException.class, () -> p.parseCommand(s));
        assertEquals(de.getMessage(), expectedMsg);
    }
    @ParameterizedTest
    @ValueSource(strings = {"done 4 5 6", "delete 4 5 6"})
    public void exceptionHandling_multipleDoneDeleteArguments_dukeExceptionReturned(String s) {
        String expectedMsg = Message.ERROR_DONEDELETE_ARGS.getMsg();
        Parser p = new Parser();
        VitoException de = assertThrows(VitoException.class, () -> p.parseCommand(s));
        assertEquals(de.getMessage(), expectedMsg);
    }
    @ParameterizedTest
    @ValueSource(strings = {"deadline", "deadline a"})
    public void exceptionHandling_badDeadlineFormat_dukeExceptionReturned(String s) {
        String expectedMsg = Message.ERROR_DEADLINE_FORMAT.getMsg();
        Parser p = new Parser();
        VitoException de = assertThrows(VitoException.class, () -> p.parseCommand(s));
        assertEquals(de.getMessage(), expectedMsg);
    }
    @ParameterizedTest
    @ValueSource(strings = {"deadline /by xxx", "deadline /by Sunday"})
    public void exceptionHandling_deadlineNoDateDescription_dukeExceptionReturned(String s) {
        String expectedMsg = Message.ERROR_DEADLINE_DESC.getMsg();
        Parser p = new Parser();
        VitoException de = assertThrows(VitoException.class, () -> p.parseCommand(s));
        assertEquals(de.getMessage(), expectedMsg);
    }
    @ParameterizedTest
    @ValueSource(strings = {"event /by Sunday"})
    public void exceptionHandling_badEventFormat_dukeExceptionReturned(String s) {
        String expectedMsg = Message.ERROR_EVENT_FORMAT.getMsg();
        Parser p = new Parser();
        VitoException de = assertThrows(VitoException.class, () -> p.parseCommand(s));
        assertEquals(de.getMessage(), expectedMsg);
    }
}
