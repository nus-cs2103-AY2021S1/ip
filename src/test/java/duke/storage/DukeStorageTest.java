package duke.storage;

import duke.DukeStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DukeStorageTest {

    private final DukeStub dukeStub = new DukeStub();

    private TaskStorageSideEffects taskStorageSideEffects = TaskStorageSideEffects.getInstance();

    @Test
    public void constructorTest() {
        try {
            new DukeStorage(dukeStub);
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @Test
    public void loadSavedTasksTest() {
        try {
            new DukeStorage(dukeStub).loadSavedTasks();

            assertEquals(true, taskStorageSideEffects.getSavedTasks);

            taskStorageSideEffects.reset();
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }

    @Test
    public void saveCurrentTasksTest() {
        try {
            new DukeStorage(dukeStub).saveCurrentTasks();

            assertEquals(true, taskStorageSideEffects.saveTasks);

            taskStorageSideEffects.reset();
        } catch (Exception exception) {
            fail(exception.getMessage());
        }
    }
}
