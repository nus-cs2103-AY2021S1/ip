package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.*;
import duke.ui.Ui;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.fail;


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
