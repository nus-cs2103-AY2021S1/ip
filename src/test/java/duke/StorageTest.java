package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class StorageTest {
    @Test
    public void createFile_inputFilePath_fileExists() {
        try {
            Storage storage = new Storage("storageTest");
            assertTrue(Files.exists(Paths.get("storageTest/duke.txt")));
            Files.deleteIfExists(Paths.get("storageTest/duke.txt"));
            Files.deleteIfExists(Paths.get("storageTest"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void saveLoadTasksTest() {
        ArrayList<Task> tasks = new ArrayList<>();

        tasks.add(new ToDo("sleep"));
        tasks.add(new Event("bfast", LocalDateTime.of(LocalDate.now(),
            LocalTime.parse("10:00"))));
        tasks.add(new Deadline("project",
            LocalDateTime.of(LocalDate.parse("2020-08-27"), LocalTime.parse("23:59"))));

        Storage storage = new Storage("storageTest");
        storage.saveTasks(tasks);

        try {
            assertEquals(tasks, storage.load());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
