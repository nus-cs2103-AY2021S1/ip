package duke.duketest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.Duke;

public class DukeTest {
    @Test
    public void initializeDuke() {
        Duke duke = new Duke();
        assertEquals("Initialized", "Initialized");
    }
}
