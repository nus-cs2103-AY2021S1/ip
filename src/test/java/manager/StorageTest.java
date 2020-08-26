package manager;

import main.java.manager.Storage;
import main.java.tasks.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    @Test
    public void testCreateSavedTask() {
        Storage database = Storage.initializeDatabase();
        Task event = database.createSavedTask("E | true | run in a stream | 2020-08-29 1000");
        Task todo = database.createSavedTask("T | false | watch ball fondlers");
        Task deadline = database.createSavedTask("D | true | fix the car | 2020-08-27 1800");
        assertEquals("[E][✓] run in a stream (at: Aug 29 2020, 10:00 AM)",
                event.toString());
        assertEquals("[T][✘] watch ball fondlers",
                todo.toString());
        assertEquals("[D][✓] fix the car (by: Aug 27 2020, 06:00 PM)",
                deadline.toString());
    }
}
