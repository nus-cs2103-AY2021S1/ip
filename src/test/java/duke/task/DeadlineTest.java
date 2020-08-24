package duke.task;

import duke.exception.WrongFormatException;
import org.junit.jupiter.api.Test;

import java.time.DateTimeException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {

    @Test
    public void newDeadline_validInput_deadlineObject() {
        try {
            new Deadline("homework", "2020-09-10 2359");
            assertTrue(true);
        } catch (WrongFormatException e) {
            fail();
        }
    }

    @Test
    public void newDeadline_invalidInputWrongDateFormat_dateTimeExceptionThrown() {
        try {
            new Deadline("homework", "10-09-2020 2359");
            fail();
        } catch (WrongFormatException | DateTimeException e) {
            assertTrue(e instanceof DateTimeException);
        }
    }

    @Test
    public void newDeadline_invalidInputWrongTimeFormat_numberFormatExceptionThrown() {
        try {
            new Deadline("homework", "2020-09-10 11:59pm");
            fail();
        } catch (WrongFormatException | NumberFormatException e) {
            assertTrue(e instanceof NumberFormatException);
        }
    }

    @Test
    public void newDeadline_invalidInputWrongDateTimeFormat_dateTimeExceptionThrown() {
        try {
            new Deadline("homework", "tomorrow 9pm");
            fail();
        } catch (WrongFormatException | DateTimeException e) {
            assertTrue(e instanceof DateTimeException);
        }
    }

    @Test
    public void newDeadline_invalidInputNoDateTimeSpecified_arrayIndexOutOfBoundsExceptionThrown() {
        try {
            new Deadline("homework", "then");
            fail();
        } catch (WrongFormatException | ArrayIndexOutOfBoundsException e) {
            assertTrue(e instanceof ArrayIndexOutOfBoundsException);
        }
    }

    @Test
    public void newDeadline_invalidInputNoDescription_wrongFormatExceptionThrown() {
        try {
            new Deadline("", "2020-09-10 11:59pm");
            fail();
        } catch (WrongFormatException e) {
            assertTrue(true);
        }
    }

    @Test
    public void newDeadline_invalidInputNoDateTime_arrayIndexOutOfBoundsExceptionThrown() {
        try {
            new Deadline("homework", "");
            fail();
        } catch (WrongFormatException | ArrayIndexOutOfBoundsException e) {
            assertTrue(e instanceof ArrayIndexOutOfBoundsException);
        }
    }

    @Test
    public void newDeadline_invalidInputNoDescriptionNorDateTime_wrongFormatExceptionThrown() {
        try {
            new Deadline("", "");
            fail();
        } catch (WrongFormatException e) {
            assertTrue(true);
        }
    }
}