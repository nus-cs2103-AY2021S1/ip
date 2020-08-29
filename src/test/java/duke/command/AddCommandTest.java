package duke.command;

import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;




public class AddCommandTest {
    @Test
    public void addTodo() {
        try {
            Task todo = new ToDo("description");
            Command command = new AddCommand(todo);
            command.execute(new TaskList(), new Ui(), new Storage("data/duke.txt"));
        } catch (DukeException e) {
            fail("Should not have failed at adding todo task");
        }
    }

    @Test
    public void addEvent() {
        try {
            Task event = new Event("description",
                    LocalDate.parse("2012-12-02"), LocalTime.parse("16:00:00"));
            Command command = new AddCommand(event);
            command.execute(new TaskList(), new Ui(), new Storage("data/duke.txt"));
        } catch (DukeException e) {
            fail("Should not have failed at adding event task");
        }
    }

    @Test
    public void addDeadline() {
        try {
            Task deadline = new Deadline("description",
                    LocalDate.parse("2012-12-02"), LocalTime.parse("16:00:00"));
            Command command = new AddCommand(deadline);
            command.execute(new TaskList(), new Ui(), new Storage("data/duke.txt"));
        } catch (DukeException e) {
            fail("Should not have failed at adding deadline task");
        }
    }


}
