package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

public class AddCommandTest {
    @Test
    public void executeToDoTest() {
        TaskList list = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("./src/test/java/duke/Data/data.txt", "./src/test/java/duke/Data");
        try {
            Command addCommand = new AddCommand("todo", "pass test 1");
            addCommand.execute(list, ui, storage);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            assertEquals(1, list.getListOfTasks().size());
            assertTrue(list.getListOfTasks().get(0) instanceof ToDo);
        }
    }

    @Test
    public void executeDeadlineTest() {
        TaskList list = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("./src/test/java/duke/Data/data.txt", "./src/test/java/duke/Data");
        try {
            Command addCommand = new AddCommand("deadline", "pass test 2",
                    LocalDate.parse("2020-12-01"), LocalTime.parse("12:00"));
            addCommand.execute(list, ui, storage);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            assertEquals(1, list.getListOfTasks().size());
            assertTrue(list.getListOfTasks().get(0) instanceof Deadline);
        }
    }

    @Test
    public void executeEventTest() {
        TaskList list = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("./src/test/java/duke/Data/data.txt", "./src/test/java/duke/Data");
        try {
            Command addCommand = new AddCommand("event", "pass test 3",
                    LocalDate.parse("2029-12-01"), LocalTime.parse("17:00"));
            addCommand.execute(list, ui, storage);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            assertEquals(1, list.getListOfTasks().size());
            assertTrue(list.getListOfTasks().get(0) instanceof Event);
        }
    }
}
