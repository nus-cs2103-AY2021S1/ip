package duke.dukeTest;

import duke.Duke;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void initialiseDuke(){
        Duke duke = new Duke();
        assertEquals("Initialised", "Initialised");
    }
}
