package duke.storage;

import duke.exceptions.InvalidFilePathException;
import duke.exceptions.TaskListTranslatorException;
import duke.parsers.DukeDateTimeParser;
import duke.tasklist.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {

    @TempDir
    public static Path testFolder;

    private static final String TEST_DATA_FOLDER = "data/StorageTest";
    private static final String NON_EXISTENT_FILE_NAME = "ThisFileDoesNotExist.txt";


    @Test
    public void constructor_noTxtExtension_exceptionThrown() throws Exception {
        assertThrows(InvalidFilePathException.class, () ->
                new Storage(TEST_DATA_FOLDER + "/" + "InvalidFileName"));
    }

    @Test
    public void load_invalidFormat_exceptionThrown() throws TaskListTranslatorException {
        Storage storage = new Storage(TEST_DATA_FOLDER + "/" + "invalidData.txt");
        assertThrows(TaskListTranslatorException.class, storage::load);
    }

    @Test
    public void load_validFormat_success() {
        Storage storage = new Storage(TEST_DATA_FOLDER + "/" + "validData.txt");
        TaskList taskList = storage.load();
        String[] expected = {
                "[T][\u2718] todo",
                "[D][\u2718] deadline (by: 23 Aug 2020 9:00 PM)",
                "[E][\u2713] event (at: 23 Aug 2020)"
        };
        ArrayList<Task> tasks = taskList.getTasks();
        for (int i = 0; i < 3; i++) {
            assertEquals(expected[i], tasks.get(i).toString());
        }
    }

    @Test
    public void load_nonExistentFile_returnsEmptyTaskList() {
        Storage storage = new Storage(TEST_DATA_FOLDER + "/" + NON_EXISTENT_FILE_NAME);
        TaskList taskList = storage.load();
        assertEquals(0, taskList.numOfTasks());
    }

    @Test
    public void save_taskList_success() throws IOException {
        TaskList taskList = new TaskList();
        Todo todo = new Todo("todo");
        Deadline deadline = new Deadline("deadline",
                DukeDateTimeParser.parse("23/8/2020 9:00 PM"));
        Event event = new Event("event",
                DukeDateTimeParser.parse("23/8/2020"));
        event.markAsDone();
        taskList.addTask(todo);
        taskList.addTask(deadline);
        taskList.addTask(event);
        Storage storage = new Storage(testFolder.resolve("temp.txt").toString());
        storage.save(taskList);
        List<String> expected = Files.readAllLines(Paths.get(TEST_DATA_FOLDER + "/validData.txt"));
        List<String> actual = Files.readAllLines(testFolder.resolve("temp.txt"));
        assertIterableEquals(expected, actual);
    }
}
