package classes;

import duke.classes.TaskList;
import duke.exceptions.DukeInvalidTimeException;
import duke.exceptions.EmptyDukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TasklistTest {

    public TaskList tasklist = new TaskList();
    Todo todo = new Todo("buy books", 1, false);

    @Test
    public void todo_addition_success() throws EmptyDukeException {
        assertEquals(todo.toString(), tasklist.storeTodo("todo buy books").toString());
    }

    @Test
    public void todo_addition_failure() throws EmptyDukeException {
        try {
            assertEquals(todo.toString(), tasklist.storeTodo("todo").toString());
        } catch (EmptyDukeException e) {
            assertEquals("The description of your todo is empty.", e.getMessage());
        }
    }

    @Test
    public void deadline_addition_success() throws EmptyDukeException, DukeInvalidTimeException {
        Deadline deadline =  new Deadline("return book /by 2017-10-10 05:00", 2, false);
        assertEquals(deadline.toString(), tasklist.storeDeadline("deadline return book /by 2017-10-10 05:00").toString());
    }

    @Test
    public void deadline_invalidDate_exceptionThrown() throws DukeInvalidTimeException {
        Deadline deadline =  new Deadline("return book /by 2017-10-10 05:00", 2, false);
        try {
            assertEquals(deadline.toString(), tasklist.storeDeadline("deadline return book /by 2017-10-10 1234:00").toString());
        } catch (Exception e) {
            assertEquals("Your command has to be in a proper format like the one here \n" +
                    "\t <command> <activity> <by/at> <Year-Month-Day Hour:Minute>.", e.getMessage());
        }
    }

    @Test
    public void deadline_invalidCommand_exceptionThrown() throws DukeInvalidTimeException {
        Deadline deadline =  new Deadline("return book /by 2017-10-10 05:00", 2, false);
        try {
            assertEquals(deadline.toString(), tasklist.storeDeadline("deadline").toString());
        } catch (Exception e) {
            assertEquals("The description of your deadline is empty.", e.getMessage());
        }
    }

    @Test
    public void event_addition_success() throws EmptyDukeException, DukeInvalidTimeException {
        Event event =  new Event("attend interview /by 2020-11-06 18:00", 3, false);
        assertEquals(event.toString(), tasklist.storeEvent("event attend interview /by 2020-11-06 18:00").toString());
    }

    @Test
    public void event_invalidDate_exceptionThrown() throws DukeInvalidTimeException {
        Deadline deadline =  new Deadline("attend interview /by 2020-11-06 05:00", 3, false);
        try {
            assertEquals(deadline.toString(), tasklist.storeDeadline("event attend interview /by 2020-11-06 13245").toString());
        } catch (Exception e) {
            assertEquals("Your command has to be in a proper format like the one here \n" +
                    "\t <command> <activity> <by/at> <Year-Month-Day Hour:Minute>.", e.getMessage());
        }
    }

    @Test
    public void event_invalidCommand_exceptionThrown() throws DukeInvalidTimeException {
        Event event =  new Event("attend interview /by 2020-11-06 05:00", 4, false);
        try {
            assertEquals(event.toString(), tasklist.storeEvent("event").toString());
        } catch (Exception e) {
            assertEquals("The description of your event is empty.", e.getMessage());
        }
    }

}
