import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DukeTest {

    @Test
    void runDuke() {
        Duke duke = new Duke();
        assertEquals(duke.getResponse("bye"), "Bye. Hope to see you again soon!");
    }
}
