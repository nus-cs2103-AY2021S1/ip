package duke;

import org.junit.jupiter.api.Test;
import task.Deadline;
import task.Event;
import task.Todo;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class StorageTest {
    @Test
    void testStorage_sampleTaskList_LoadsCorrectly() {
        Storage storage = new Storage(
                System.getProperty("user.dir")
                + "\\src\\test\\data\\testTaskListToLoad.txt"
        );

        try {
            assertEquals(5, storage.load().size());
        } catch (DukeException e) {
            System.out.println(e);
            fail();
        }
    }

    @Test
    void testStorage_sampleTaskList_StoresCorrectly() {
        TaskList sampleList = new TaskList();
        sampleList.addTask(new Todo("Make tea tonight!"));
        sampleList.addTask(new Event("Midterm Test", "Sep 30th 2020"));
        sampleList.addTask(new Deadline("Submit tutorial", "tonight!", true));

        Storage storage = new Storage(
                System.getProperty("user.dir") +
                        "/src/test/data/testTaskListToStore.txt"
        );

        try {
            assertTrue(storage.store(sampleList));
        } catch (DukeException e) {
            System.out.println(e);
            fail();
        }
    }

    @Test
    void testStorage_fakeFilePath_willThrowExceptionOnStore() {
        Storage storage = new Storage("fakeFilePath!");
        try {
            storage.store(new TaskList());
            fail(); // Should not reach here.
        } catch (DukeException e) {
            assertTrue(e.toString().contains("Exception occurred while storing into file: "));
        }
    }

}
