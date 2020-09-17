package duke.main;

import duke.exception.InvalidTaskTypeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    @Test
    public void testConvertFromHardisk() throws InvalidTaskTypeException {
        Storage storage = new Storage();
        assertEquals("E / 1 / read manga / 2021-01-03", storage.convertToHardDisk(new EventStub()));
        assertEquals("T / 1 / wash dishes", storage.convertToHardDisk(new TodoStub()));
        assertEquals("D / 0 / finish assignment / 2021-02-03", storage.convertToHardDisk(new DeadlineStub()));
    }
}
