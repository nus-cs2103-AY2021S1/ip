package duke.storage;

import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;
import duke.exception.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.fail;

public class StorageTest {
	@Test
	public void invalidStorageFile() {
		try {
			Storage storage = new Storage("data/invalidDuke.txt");
			storage.load();
			fail("Should have thrown an exception due to invalid storage text file");
		} catch (DukeException e) {
			return;
		}
	}

	@Test
	public void validStorageFile() {
		try {
			Storage storage = new Storage("data/duke.txt");
			storage.load();
		} catch (DukeException e) {
			fail("Should not have thrown an exception due to valid storage text file");
		}
	}

	@Test
	public void readAndWriteToFile() {
		try {
			ArrayList<Task> tasks = new ArrayList<>();
			tasks.add(new ToDo("description"));
			tasks.add(new Deadline("description",
					LocalDate.parse("2012-02-12"), LocalTime.parse("16:00:00")));
			TaskList taskList = new TaskList(tasks);
			Storage storage = new Storage("data/duke.txt");
			storage.updateStorage(taskList);
			ArrayList<Task> returnedTaskList = storage.load();
		} catch (DukeException e) {
			fail(e.getMessage());
			//fail("Should not throw exception when updating or reading from file");
		}
	}
}
