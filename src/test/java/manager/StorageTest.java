package manager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import tasks.Task;

public class StorageTest {

    @Test
    public void testCreateSavedTask() {
        Storage database = Storage.initializeDatabase();
        Task event = database.createSavedTask("E | true | run in a stream | fun play | 2020-08-29 1000");
        Task todo = database.createSavedTask("T | false | watch ball fondlers | bestshowever");
        Task deadline = database.createSavedTask("D | true | fix the car | crazy terrible | 2020-08-27 1800");
        assertEquals("[E][✓] run in a stream (#fun #play) (at: Aug 29 2020, 10:00 AM)",
                event.toString());
        assertEquals("[T][✘] watch ball fondlers (#bestshowever)",
                todo.toString());
        assertEquals("[D][✓] fix the car (#crazy #terrible) (by: Aug 27 2020, 06:00 PM)",
                deadline.toString());
    }
}
