package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import duke.task.DeadlineStub;
import duke.task.EventStub;
import duke.task.Task;
import duke.task.TodoStub;

public class StorageTest {

    public void save() throws DukeException {
        String filePath = "data/duke.txt";
        Storage storage = Storage.createStorage(filePath);
        assertTrue(storage.isNew());

        String home = System.getProperty("user.dir");
        Path currDir = Paths.get(home);
        Path targetPath = Paths.get(currDir.toString(), filePath);
        File file = new File(targetPath.toString());

        Task dTask = DeadlineStub.createTask();
        Task eTask = EventStub.createTask();
        Task tTask = TodoStub.createTask();

        List<Task> listOfTask = new ArrayList<>();
        listOfTask.add(dTask);
        listOfTask.add(eTask);
        listOfTask.add(tTask);

        StorageStub.save(listOfTask, file);

        Storage storageDuplicate = Storage.createStorage(filePath);
        assertFalse(storageDuplicate.isNew());
    }

    public List<Task> load() throws DukeException {
        String filePath = "data/duke.txt";
        Storage storage = Storage.createStorage(filePath);
        return storage.load();
    }

    @Test
    public void saveAndLoadTest() throws DukeException {
        Task dTask = DeadlineStub.createTask();
        Task eTask = EventStub.createTask();
        Task tTask = TodoStub.createTask();
        save();
        List<Task> listOfTask = load();
        Task[] arr = new Task[]{dTask, eTask, tTask};
        for (Task t : listOfTask) {
            String expected = arr[listOfTask.indexOf(t)].toString();
            String actual = t.toString();
            assertEquals(expected, actual);
        }
    }

}
