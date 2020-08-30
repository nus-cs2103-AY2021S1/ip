package seedu.duke;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void loadTest() throws IOException {
        assertEquals(new ArrayList<Task>(), new Storage().load());
    }
}
