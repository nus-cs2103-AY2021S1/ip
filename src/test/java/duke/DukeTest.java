package duke;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DukeTest {
    @Test
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    public void createDukeTest() {
        try {
            Duke duke = new Duke("dukeTest");
            assertTrue(Files.exists(Paths.get("dukeTest/duke.txt")));
            Files.deleteIfExists(Paths.get("dukeTest/duke.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
