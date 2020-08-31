package duke;

import org.junit.jupiter.api.Test;
import task.Deadline;
import task.Event;
import task.Todo;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * JUnit test class to test the Storage class.
 */
class StorageTest {

    /**
     * Tests if a sample TaskList loads correctly.
     */
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

    /**
     * Tests if a sample TaskList stores correctly..
     */
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

    /**
     * Tests if a invalid file path will trigger an exception.
     */
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
