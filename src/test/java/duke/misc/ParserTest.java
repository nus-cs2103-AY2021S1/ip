package duke.misc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.exception.InvalidDateException;
import duke.exception.InvalidDescriptionException;
import duke.exception.InvalidTypeException;

public class ParserTest {
    private TaskList t = new TaskList();

    @Test
    public void handleInput_invalidType_invalidTypeException() {
        try {
            Parser.handleInput("example hello");
        } catch (InvalidTypeException e) {
            assertEquals("OOPS! Command was not executed! "
                            + "Please use a valid command. Type 'help' for more information.",
                   e.getMessage());
        } catch (InvalidDescriptionException e) {
            fail();
        } catch (InvalidDateException e) {
            fail();
        }
    }

    @Test
    public void handleInput_invalidDescription_invalidDescriptionException() {
        try {
            Parser.handleInput("event /at");
        } catch (InvalidTypeException e) {
            fail();
        } catch (InvalidDescriptionException e) {
            assertEquals("OOPS! Task was not added! Please start your description exactly 1 space after the task type."
                    + " Type 'help' to see the appropriate format.",
                    e.getMessage());
        } catch (InvalidDateException e) {
            fail();
        }
    }

    @Test
    public void handleInput_erroneousSpacing_invalidDescriptionException() {
        try {
            Parser.handleInput("deadline  test /by 2020-12-12");
        } catch (InvalidTypeException e) {
            fail();
        } catch (InvalidDescriptionException e) {
            assertEquals("OOPS! Task was not added! Please start your description exactly 1 space after the task type."
                            + " Type 'help' to see the appropriate format.",
                    e.getMessage());
        } catch (InvalidDateException e) {
            fail();
        }
    }

    @Test
    public void handleInput_invalidDate_invalidDateException() {
        try {
            Parser.handleInput("event test event /at tonight 6pm");
        } catch (InvalidTypeException e) {
            fail();
        } catch (InvalidDescriptionException e) {
            fail();
        } catch (InvalidDateException e) {
            assertEquals("OOPS! Task was not added! Please use the yyyy-MM-dd format for your date.",
                    e.getMessage());
        }
    }

    @Test
    public void allocate_validTodo_todoAdded() {
        String result = Parser.allocate("todo haha", t);
        assertEquals(String.format(Ui.ADD_TASK_MESSAGE, "[T][X] haha ", 1), result);
    }

    @Test
    public void allocate_validEvent_eventAdded() {
        String result = Parser.allocate("event #work /at 2020-12-12", t);
        assertEquals(String.format(Ui.ADD_TASK_MESSAGE, "[E][X] work (at: Dec 12 2020) #work", 1), result);
    }

    @Test
    public void allocate_validDeadline_deadlineAdded() {
        String result = Parser.allocate("deadline do #stuff /by 2020-12-12", t);
        assertEquals(String.format(Ui.ADD_TASK_MESSAGE, "[D][X] do stuff (by: Dec 12 2020) #stuff", 1), result);
    }
}
