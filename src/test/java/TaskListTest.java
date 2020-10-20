import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.TaskList;
import exception.InvalidDeadlineException;
import exception.InvalidEventException;
import exception.InvalidTodoException;
import ui.Ui;

public class TaskListTest {

    private Ui ui = new Ui();

    @Test
    public void addDeadline_missingDate_exceptionThrown() {
        String input = "deadline finish level 6 /by";
        try {
            new TaskList().addDeadline(ui, input.split(" ", 2)[1], true, false);
        } catch (Exception ex) {
            assertTrue(ex instanceof InvalidDeadlineException);
        }
    }

    @Test
    public void addDeadline_wrongDateSeparator_exceptionThrown() {
        String input = "deadline finish level 6 /at";
        try {
            new TaskList().addDeadline(ui, input.split(" ", 2)[1], true, false);
        } catch (Exception ex) {
            assertTrue(ex instanceof InvalidDeadlineException);
        }
    }

    @Test
    public void addDeadline_wrongDateFormat_exceptionThrown() {
        String input = "deadline finish level 6 /at 19/08/2020";
        try {
            new TaskList().addDeadline(ui, input.split(" ", 2)[1], true, false);
        } catch (Exception ex) {
            assertTrue(ex instanceof InvalidDeadlineException);
        }
    }

    @Test
    public void addEvent_wrongDateFormat_exceptionThrown() {
        String input = "event my birthday /at 25/08/2020";
        try {
            new TaskList().addEvent(ui, input.split(" ", 2)[1], true, false);
        } catch (Exception ex) {
            assertTrue(ex instanceof InvalidEventException);
        }
    }

    @Test
    public void addEvent_wrongDateSeparator_exceptionThrown() {
        String input = "event finish level 6 /by 2020-08-25";
        try {
            new TaskList().addEvent(ui, input.split(" ", 2)[1], true, false);
        } catch (Exception ex) {
            assertTrue(ex instanceof InvalidEventException);
        }
    }

    @Test
    public void addEvent_missingDate_exceptionThrown() {
        String input = "event TEST /at";
        try {
            new TaskList().addEvent(ui, input.split(" ", 2)[1], true, false);
        } catch (Exception ex) {
            assertTrue(ex instanceof InvalidEventException);
        }
    }

    @Test
    public void parseTodo_missingDescription_exceptionThrown() {
        String input = "todo";
        try {
            new TaskList().addTodo(ui, input.split(" ", 2)[0], false, false);
        } catch (Exception ex) {
            assertTrue(ex instanceof InvalidTodoException);
        }
    }
}
