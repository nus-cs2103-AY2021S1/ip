import org.junit.jupiter.api.Test;
import src.main.java.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void checkDeadlineAndEventValidity_validCommand_success () {
        Command deadlineCommand = new Command(CommandType.DEADLINE,
                "deadline hw /by 12/10/2020 1330");
        Command eventCommand = new Command(CommandType.EVENT,
                "event concert /at 12/12/2020 1445");
        assertEquals(deadlineCommand, new Parser().checkDeadlineAndEventValidity(deadlineCommand));
        assertEquals(deadlineCommand, new Parser().checkDeadlineAndEventValidity(deadlineCommand));
    }

    @Test
    public void checkDeadlineAndEventValidity_invalidDeadlineMissingKeyword_exceptionThrown() {
        Command deadlineCommand = new Command(CommandType.DEADLINE,
                "deadline hw 12/10/2020 1330");
        try {
            assertEquals(deadlineCommand, new Parser().checkDeadlineAndEventValidity(deadlineCommand));
            fail();
        } catch (InvalidInputException e) {
            assertEquals("Command is missing \"by\" keyword", e.getMessage());
        }
    }

    @Test
    public void checkDeadlineAndEventValidity_invalidEventMissingKeyword_exceptionThrown() {
        Command eventCommand = new Command(CommandType.EVENT,
                "event concert 12/10/2020 1330");
        try {
            assertEquals(eventCommand, new Parser().checkDeadlineAndEventValidity(eventCommand));
            fail();
        } catch (InvalidInputException e) {
            assertEquals("Command is missing \"at\" keyword", e.getMessage());
        }
    }

}