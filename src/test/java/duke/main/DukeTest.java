package duke.main;

import duke.task.*;
import duke.storage.*;
import duke.exception.*;
import duke.ui.*;
import duke.command.*;
import duke.parser.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    @Test
    public void datePrintTest() throws DukeException {
        Deadline deadline = new Deadline("Test the printDate() method", "2020-08-23");
        assertEquals("SUNDAY, AUGUST 23 2020", deadline.printDate());
    }

    @Test
    public void toStringTest() throws DukeException {
        Event event = new Event("Test the toString() method", "2020-08-23");
        assertEquals("E > ✘ > Test the toString() method > at: SUNDAY, AUGUST 23 2020",
                event.toString());
    }

    @Test
    public void commandBooleanTest() {
        ListCommand testCommand = new ListCommand();
        assertEquals(false, testCommand.isDone());
    }

    @Test
    public void commandBooleanTest2() throws DukeException {
        Ui ui = new Ui();
        Parser parser = new Parser(ui);

        assertEquals(true, parser.parse("bye").isDone());
    }

    @Test
    public void taskListTest() {
        TaskList taskList = new TaskList();
        ToDo todo = new ToDo("Test the TaskList class for the Duke project");

        taskList.addTodo(todo);
        assertEquals("T > ✘ > Test the TaskList class for the Duke project",
                taskList.getTaskList().get(0).toString());
    }

}
