package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {
    @Test
    public void createFileTest() {
        try {
            Storage storage = new Storage("storageTest");
            assertTrue(Files.exists(Paths.get("storageTest/duke.txt")));
            Files.deleteIfExists(Paths.get("storageTest/duke.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void saveLoadTasksTest() {
        ArrayList<Task> list = new ArrayList<>();

        list.add(new ToDo("sleep"));
        list.add(new Event("bfast", LocalDateTime.of(LocalDate.now(),
                LocalTime.parse("10:00"))));
        list.add(new Deadline("project",
                LocalDateTime.of(LocalDate.parse("2020-08-27"), LocalTime.parse("23:59"))));

        Storage storage = new Storage("storageTest");
        storage.saveTasks(list);

        try {
            assertEquals(list, storage.load());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
